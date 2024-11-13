package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;

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
	
	 /** List of students on the waitlist for the course */
	private LinkedAbstractList<Student> waitlist;


	private Course course;
	
	/** the smallest class size */
	private final static int MIN_ENROLLMENT = 10;
	 
	/** the largest class size is */
	private final static int MAX_ENROLLMENT = 250;
	 /** Maximum capacity of the waitlist */
	private static final int WAITLIST_CAPACITY = 10;

	
	
	
	/**
	 * Constructor which sets the CourseRoll object
	 * @param course the Course associated with this CourseRoll
	 * @param enrollmentCap enrollment cap to set
	 */
	 public CourseRoll(Course course, int enrollmentCap) {
	        if (course == null) {
	            throw new IllegalArgumentException("Course cannot be null.");
	        }
	       
	        setEnrollmentCap(enrollmentCap);
	        this.roll = new LinkedAbstractList<>(enrollmentCap);
	        this.waitlist = new LinkedAbstractList<>(WAITLIST_CAPACITY); 
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
            throw new IllegalArgumentException("Invalid enrollment cap.");
        }
        
        if (roll != null && enrollmentCap < roll.size()) {
            throw new IllegalArgumentException("Enrollment cap cannot be less than the number of enrolled students.");
        }
        
        if (roll != null) {
            roll.setCapacity(enrollmentCap);  
        } else {
            roll = new LinkedAbstractList<>(enrollmentCap);  
        }
        
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
	    if (s == null) {
	        throw new IllegalArgumentException("Student is null");
	    }

	    if (!canEnroll(s)) {
	        throw new IllegalArgumentException("Student is already enrolled");
	    }

	    if (getOpenSeats() > 0) {

	        roll.add(s);
	    } else if (waitlist.size() < WAITLIST_CAPACITY) {
	        
	        waitlist.add(s);
	    } else {
	       
	        throw new IllegalArgumentException("Class and waitlist are full");
	    }
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

	    if (roll.contains(s)) {
	        
	        roll.remove(s);

	       
	        if (!waitlist.isEmpty() && getOpenSeats() > 0) {
	            Student nextInLine = waitlist.remove(0); 
	            roll.add(nextInLine);  
	        }
	    } else if (waitlist.contains(s)) {
	        
	        waitlist.remove(s);
	    } 
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
		return s != null && !roll.contains(s) && !waitlist.contains(s) &&
	               (getOpenSeats() > 0 || waitlist.size() < WAITLIST_CAPACITY);
	}
	
	 /**
     * Gets the number of students on the waitlist.
     * 
     * @return the number of students on the waitlist
     */
    public int getNumberOnWaitlist() {
        return waitlist.size();
    }

}
