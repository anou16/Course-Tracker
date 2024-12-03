package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * The Faculty class represents a faculty member. It extends the User class and
 * adds a maximum number of courses the faculty member can teach.
 * 
 * Faculty members can teach between MIN_COURSES and MAX_COURSES per semester.
 * 
 * @author wangc
 * @author Anoushka Piduru
 * 
 */
public class Faculty extends User implements Comparable<Faculty> {
	/** The maximum number of courses the faculty member can teach */
	private int maxCourses;
	/** The minimum number of courses a faculty member can teach */
	public static final int MIN_COURSES = 1;
	/** The maximum number of courses a faculty member can teach */
	public static final int MAX_COURSES = 3;
	/** The schedule for this Faculty. */
	private FacultySchedule schedule;

	/**
	 * Constructs a Faculty object with the given details.
	 * 
	 * @param firstName  the first name of the faculty
	 * @param lastName   the last name of the faculty
	 * @param id         the unique ID of the faculty
	 * @param email      the email address of the faculty
	 * @param password   the hashed password of the faculty
	 * @param maxCourses the maximum number of courses the faculty can teach
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		schedule = new FacultySchedule(id);
	}

	/**
	 * Constructs a Faculty object with the given details.
	 * 
	 * @param firstName the first name of the faculty
	 * @param lastName  the last name of the faculty
	 * @param id        the unique ID of the faculty
	 * @param email     the email address of the faculty
	 * @param password  the hashed password of the faculty
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, 3);
	}

	/**
	 * Sets the maximum number of courses the faculty member can teach.
	 * 
	 * @param maxCourses the maxCourses to set
	 * @throws IllegalArgumentException if maxCourses is not between MIN_COURSES and
	 *                                  MAX_COURSES
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid maxCourses");
		}
		this.maxCourses = maxCourses;
	}

	/**
	 * Returns the maximum number of courses the faculty member can teach.
	 * 
	 * @return the maxCourses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Compares this Faculty to given Faculty object (last name, first name, then
	 * id).
	 * 
	 * @param s the Faculty to be compared to.
	 * @return -1 if object is less than given Faculty, 0 if equal, 1 if greater.
	 * @throws NullPointerException if the given Faculty object is null.
	 */
	public int compareTo(Faculty s) {
		if (s == null) {
			throw new NullPointerException();
		}
		if (super.getLastName().compareTo(s.getLastName()) != 0) {
			return 0 > super.getLastName().compareTo(s.getLastName()) ? -1 : 1;
		} else if (super.getFirstName().compareTo(s.getFirstName()) != 0) {
			return 0 > super.getFirstName().compareTo(s.getFirstName()) ? -1 : 1;
		} else if (super.getId().compareTo(s.getId()) != 0) {
			return 0 > super.getId().compareTo(s.getId()) ? -1 : 1;
		} else {
			return 0;
		}
	}

	/**
	 * Returns schedule of Faculty.
	 * 
	 * @return the schedule for this Faculty.
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}

	/**
	 * Generates a hash code for the Faculty object.
	 * 
	 * @return the hash code
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCourses);
		return result;
	}

	/**
	 * Compares this Faculty object to another object for equality.
	 * 
	 * @param obj the object to compare
	 * @return true if the objects are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return maxCourses == other.maxCourses;
	}

	/**
	 * Returns a comma-separated string representation of the Faculty object.
	 * 
	 * @return the string representation of the faculty
	 */
	@Override
	public String toString() {
		return super.toString() + "," + maxCourses;
	}

	/**
	 * Checks if the scheduled courses for instructor exceed the max course limit.
	 * 
	 * @return true if the number exceeds, false if not.
	 */
	public boolean isOverloaded() {
		return schedule.getNumScheduledCourses() > maxCourses;
	}
}
