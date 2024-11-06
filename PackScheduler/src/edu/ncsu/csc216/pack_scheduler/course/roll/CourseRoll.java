package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * Class that allows a student to enroll in a course
 * 
 * @author Neha Pothireddy 
 * @author Michael Wanng
 */
public class CourseRoll {

	/** the roll’s enrollment capacity*/
	private int enrollmentCap; 
	
	/** LinkedList of students for the roll */
	LinkedAbstractList<Student> roll;
	
	/** the smallest class size */
	private final static int MIN_ENROLLMENT = 10;
	 
	/** the largest class size is */
	private final static int MAX_ENROLLMENT = 250;

	
	
	
	/**
	 * Constructor which sets the CourseRoll object
	 * 
	 * @param enrollmentCap enrollment cap to set
	 */
	public CourseRoll(int enrollmentCap) {
		setEnrollmentCap(enrollmentCap);
		this.roll = new LinkedAbstractList<Student>(enrollmentCap);
	}


	/**
	 * Sets the enrollmentCap to the parameter
	 * 
	 * @param enrollmentCap the enrollmentCap to set
	 * @throws IllegalArgumentException if the enrollmentCap is less than the min or greater than the max
	 * @throws IllegalArgumentException if the enrollmentCap is invalid
	 */
	public void setEnrollmentCap(int enrollmentCap) {
        if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
            throw new IllegalArgumentException("Invalid enrollmentCap");
        }
        if (roll != null && enrollmentCap < roll.size()) {
            throw new IllegalArgumentException("Enrollment cap less than current roll size");
        }
        
        roll.setCapacity(enrollmentCap);
        
        this.enrollmentCap = enrollmentCap;
    }


	/**
	 * Returns the enrollmentCap value
	 * 
	 * @return the enrollmentCap
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/**
	 * Enrolls the student into the course roll
	 * 
	 * @param s the student to be added
	 * @throws IllegalArgumentException if the student is null
	 * @throws IllegalArgumentException if the class is full
	 * @throws IllegalArgumentException if the student is already enrolled
	 */
	public void enroll(Student s) {
		if(s == null) {
			throw new IllegalArgumentException("Student is null");
		}
		if(getOpenSeats() <= 0) {
			throw new IllegalArgumentException("Class is full");
		}
		if(!canEnroll(s)) {
			throw new IllegalArgumentException("Student is already enrolled");
		}
		
		roll.add(roll.size(), s);
	}
	
	/**
	 * Drops a student from the roll 
	 * 
	 * @param s student to be dropped
	 * @throws IllegalArgumentException if the student is null
	 * @throws IllegalArgumentException if the student is not enrolled
	 */
	public void drop(Student s) {
	    if (s == null) {
	        throw new IllegalArgumentException("Student is null");
	    }

	    int index = -1;
	    for (int i = 0; i < roll.size(); i++) {
	        if (roll.get(i).equals(s)) {
	            index = i;
	            break;
	        }
	    }

	    if (index == -1) {
	        throw new IllegalArgumentException("Student not enrolled");
	    }

	    roll.remove(index);
	}
	
	/**
	 * Gets the number of open seats by subtracting the roll size from the enrollmentCap
	 * 
	 * @return int of the number of open seats available 
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Checks if the student can be enrolled 
	 * 
	 * @param s the student to enroll
	 * @return true if the student can be enrolled 
	 */
	public boolean canEnroll(Student s) {
		 return getOpenSeats() > 0 && !roll.contains(s);
	}

}
