package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.course.Course;
//import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Class containing students information
 *
 * @author Christopher Gilbert
 * @author Hinano Turner
 * @author Samir Naseri
 */
public class Student extends User implements Comparable<Student> {

	/**
	 * integer for the max amount of credits a student can take
	 */
	private int maxCredits;
	/** The schedule of the student. */ 
    private Schedule schedule;
	/**
	 * Constructor class for Student object, inputs name, ID, email,
	 * hashed password and maximum credits.
	 *
	 * @param firstName students first name
	 * @param lastName students last name
	 * @param id students ID number
	 * @param email students email address
	 * @param hashPW students password (hashed)
	 * @param maxCredits Maximum credit hours
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(hashPW);
		setMaxCredits(maxCredits);
		this.schedule = new Schedule();
	}

	/**
	 * Constructor for student object without inputed max credits,
	 * sets max credits to default and calls main constructor
	 *
	 * @param firstName students first name
	 * @param lastName students last name
	 * @param id students ID number
	 * @param email students email address
	 * @param hashPW students hashed password
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
        this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
        this.schedule = new Schedule();
    }

	/**
	 * Returns the max credits
	 *
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the max credits
	 *
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if the max credits are invalid 
	 */
	public void setMaxCredits(int maxCredits) {
		// checks if max credit is within range
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}

	/**
	 * Generates the hashCode
	 * @return result 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCredits);
		return result;
	}

	/**
	 * Compares two objects
	 * 
	 * @param obj - compare 
	 * @return maxCredits 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}

	/**
	 * returns comma separated list of student fields.
	 * @return String version of fields for student object
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail()
				+ "," + getPassword() + "," + maxCredits;
		}

	/**
	 * Comparing students to validate
	 * Checks if difference between two students labels is non zero, if non zero returns value
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	 * @throws IllegalArgumentException if ClassCastException is caught
	 */
	@Override
	public int compareTo(Student o) {
		try {
			Student a = (Student) o;
			if (this.equals(a)){
				return 0;
			}
			int lastNameDiff = this.getLastName().compareTo(a.getLastName());
			if (lastNameDiff != 0) {
				return lastNameDiff;
			}
			int firstNameDiff = this.getFirstName().compareTo(a.getFirstName());
			if (firstNameDiff != 0) {
				return firstNameDiff;
			}
			int idDiff = this.getId().compareTo(a.getId());
			if (idDiff != 0) {
				return idDiff;
			}

			
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Cannot cast to student.");
		}
		return 0;
	} 

	 /**
     * Gets the student's schedule.
     * 
     * @return the schedule
     */
    public Schedule getSchedule() {
        return this.schedule;
    }

    /**
     * Returns true if the Course can be added to the Student’s Schedule. 
     * If the Course is null, if the Course is already in the schedule, if there is a conflict, or if the Student has no more room in their schedule 
     * for the course (i.e., adding the Course would exceed their max allowed credits), canAdd() will return false. 
     * Note that Student.canAdd() can call Schedule.canAdd(), which handles the first three checks already.
     * 
     * @param c - Course we are trying to add into the students schedule 
     * @return boolean true or false 
     */
    public boolean canAdd(Course c) {
        return c != null && schedule.canAdd(c) && schedule.getScheduleCredits() + c.getCredits() <= maxCredits;
    }
}