package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

/**
 * The Faculty class represents a faculty member.
 * It extends the User class and adds a maximum number of courses the faculty member can teach.
 * 
 * Faculty members can teach between MIN_COURSES and MAX_COURSES per semester.
 * @author wangc
 * 
 */
public class Faculty extends User {
    /** The maximum number of courses the faculty member can teach */
    private int maxCourses;
    /** The minimum number of courses a faculty member can teach */
    public static final int MIN_COURSES = 1;
    /** The maximum number of courses a faculty member can teach */
    public static final int MAX_COURSES = 3;

    /**
     * Constructs a Faculty object with the given details.
     * 
     * @param firstName the first name of the faculty
     * @param lastName the last name of the faculty
     * @param id the unique ID of the faculty
     * @param email the email address of the faculty
     * @param password the hashed password of the faculty
     * @param maxCourses the maximum number of courses the faculty can teach
     * @throws IllegalArgumentException if maxCourses is not between MIN_COURSES and MAX_COURSES
     */
    public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
        super(firstName, lastName, id, email, password);
        setMaxCourses(maxCourses);
    }

    /**
	 * Sets the maximum number of courses the faculty member can teach.
	 * 
	 * @param maxCourses the maxCourses to set
	 * @throws IllegalArgumentException if maxCourses is not between MIN_COURSES and MAX_COURSES
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
}
