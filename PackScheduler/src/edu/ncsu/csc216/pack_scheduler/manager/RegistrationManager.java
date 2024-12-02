package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Class that manages the registration of a user, the courses, and student
 * directory
 * 
 * @author Sarah Heckman
 * @author Neha Pothireddy
 * @author Zhonghai pu
 */
public class RegistrationManager {

	/** instance of the registrationManager class */
	private static RegistrationManager instance;

	/** course catalog of the courses a student can take */
	private CourseCatalog courseCatalog;

	/** A student directory of the students */
	private StudentDirectory studentDirectory;

	/** User object of the registrar */
	private User registrar;

	/** User object of the current person logged in */
	private User currentUser;

	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	/** string that contains the properties of the registrar file */
	private static final String PROP_FILE = "registrar.properties";
	/**
	 * The directory of faculty members maintained by the system.
	 */
	private FacultyDirectory facultyDirectory;

	/**
	 * Creates a RegistrationManager object
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
		// currentUser = new User();
	}

	/**
	 * Creates a Registrar from the properties file
	 * 
	 * @throws IllegalArgumentException if the registrar can't be created
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Converts the password into a Hash password
	 * 
	 * @return String hash version of the password
	 * @param pw String password
	 * @throws IllegalArgumentException if the password cannot be hashed
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Returns instance
	 * 
	 * @return the instance of the registration manager
	 */
	public static synchronized RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Retrieves the faculty directory containing the list of faculty members.
	 * 
	 * @return the faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Returns the courseCatalog
	 * 
	 * @return the course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Returns the studentDirectory
	 * 
	 * @return the studentDirectory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * If the registrar user is the same as the parameters, the current user is the
	 * registrar. If the student can be found with the id and the passwords match,
	 * the student is the user, else the method returns false
	 * 
	 * @param id       of the student
	 * @param password of the student
	 * @throws IllegalArgumentException if the user doesn't exist
	 * @return true if the student could login, false if they could not
	 */
	public boolean login(String id, String password) {

		String localHashPW = hashPW(password);
		if (currentUser != null) {
			return false;
		}

		if (registrar.getId().equals(id) && registrar.getPassword().equals(localHashPW)) {
			currentUser = registrar;
			return true;

		}

		if (studentDirectory != null) {
			Student student = studentDirectory.getStudentById(id);
			if (student != null && student.getPassword().equals(localHashPW)) {
				currentUser = student;
				return true;
			}
		}

		if (facultyDirectory != null) {
			Faculty faculty = facultyDirectory.getFacultyById(id);
			if (faculty != null && faculty.getPassword().equals(localHashPW)) {
				currentUser = faculty;
				return true;
			}
		}
		throw new IllegalArgumentException("User doesn't exist.");
	}

	/**
	 * logs the currentUser out of the system
	 */
	public void logout() {

		currentUser = null;
	}

	/**
	 * Returns the current user
	 * 
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * clears the courseCatalog and studentDirectory and makes it empty
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}

	/**
	 * Adds a Faculty to a Course.
	 * 
	 * @param c the Course to be added to.
	 * @param f the Faculty to be added.
	 * @return true if the Faculty is able to add the course to their schedule.
	 * @throws IllegalArgumentException if a non registrar user tries to add Faculty
	 *                                  to course.
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
		if (!getCurrentUser().equals(registrar)) {
			throw new IllegalArgumentException("Non Registrar user cannot add faculty to the course");
		}
		if (getCurrentUser() == null) {
			return false;
		}
		f.getSchedule().addCourseToSchedule(c);
		return true;
	}

	/**
	 * Removes a Faculty from a Course.
	 * 
	 * @param c the Course to be removed from.
	 * @param f the Faculty to be removed.
	 * @return true if the Faculty is able to remove the course from their schedule.
	 * @throws IllegalArgumentException if a non registrar user tries to remove
	 *                                  Faculty from course.
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
		if (!getCurrentUser().equals(registrar)) {
			throw new IllegalArgumentException("Non Registrar user cannot add faculty to the course");
		}
		if (getCurrentUser() == null) {
			return false;
		}
		f.getSchedule().removeCourseFromSchedule(c);
		return true;
	}

	/**
	 * Resets a Faculty's schedule.
	 * 
	 * @param f the Faculty that will have a reset schedule.
	 * @throws IllegalArgumentException if a non registrar user tries to reset the
	 *                                  schedule.
	 */
	public void resetFacultySchedule(Faculty f) {
		if (getCurrentUser() != null && getCurrentUser().getId().equals(registrar.getId())) {
			f.getSchedule().resetSchedule();
		} else {
			throw new IllegalArgumentException("Non Registrar user cannot reset schedule");
		}
	}

	/**
	 * Class that creates a registar user
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user.
		 * 
		 * @param firstName first name of the user
		 * @param lastName  last name of the user
		 * @param id        user's id
		 * @param email     user's email
		 * @param hashPW    the password of the user
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();

			for (String[] courseInfo : scheduleArray) {
				Course c = courseCatalog.getCourseFromCatalog(courseInfo[0], courseInfo[1]);
				c.getCourseRoll().drop(s);
			}

			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// not good
		}
	}
}