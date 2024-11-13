package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

/**
 * Class in which the user can alter their schedule by adding/removing courses from it and get access to it
 * 
 * @author Neha Pothireddy
 * @author Samir Naseri 
 */
public class Schedule {

	/** Schedule title. */
	private String title;
	
	/** An ArrayList of courses */
	private ArrayList<Course> schedule;

	/**
	 * Constructor of the Schedule class in which it creates a schedule object from the parameters
	 */
	public Schedule() {
		// get methods to test the constructors 
		
		 
		this.title = "My Schedule";
		this.schedule = new ArrayList<Course>();
		
	}
	/**
	 * Returns the title 
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	} 
	
	/**
	 * Sets the title of the Schedule
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException throws if the title is null
	 */
	public void setTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Adds the course parameter to the schedule and returns true if it was added 
	 * 
	 * @param c Course to be added to the schedule
	 * @return returns true if the course was added to the end of the schedule
	 * @throws IllegalArgumentException if the course is already in the schedule
	 * @throws IllegalArgumentException if the course can't be added due to a conflict 
	 */
	public boolean addCourseToSchedule(Course c) {
		if (c == null) {
	        throw new IllegalArgumentException("Course cannot be null.");
	    }
		for(int i = 0; i < schedule.size(); i++) { 
			if(c.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + schedule.get(i).getName()); 
			}
			
			try {
				c.checkConflict(schedule.get(i));
			} catch(ConflictException ce) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		
		//******************************************************************************
		//would this throw the NullPointer
		//need to have the throws in there? 
		schedule.add(schedule.size(), c);
		return true;
	}
	
	
	/**
	 * Removes the course from the schedule if it is inside of it 
	 * 
	 * @param c the course to be removed
	 * @return true if the course was removed from the schedule 
	 * @throws NullPointerException if the course is null
	 */
	public boolean removeCourseFromSchedule(Course c) {
		if(c == null) {
			return false;
		}
		for(int i = 0; i < schedule.size(); i++) { 
			if(c.isDuplicate(schedule.get(i))) {
				schedule.remove(i);
				return true;
			}
			
		}
		
		//returns false because there was no course to remove
		return false;
	}
	
	/**
	 * Resets the schedule to have an empty schedule and the title to the default title
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		title =  "My Schedule";
	}
	
	/**
	 * Gets the courses from the schedule and displays the information inside of a 2d Array
	 * 
	 * @return the courses in the schedule displayed in a 2d array
	 */
    public String[][] getScheduledCourses() {
        String[][] scheduleCourses = new String[schedule.size()][5];
        for (int i = 0; i < schedule.size(); i++) {
            scheduleCourses[i] = schedule.get(i).getShortDisplayArray();
        }
        return scheduleCourses;
    }
    
    /**
     * Is a cumulative sum that returns the total credits in the Schedule.
     * 
     * @return total
     */
    public int getScheduleCredits() {
        int total = 0;
        for (Course c : schedule) {
            total += c.getCredits();
        }
        return total;
    }
	
    /**
     * Returns true if the Course can be added to the schedule. 
     * If the Course is null, if the Course is already in the schedule, or if there is a conflict, canAdd() will return false.     
     * 
     * @param c - course to check for in the schedule 
     * @return boolean true or false 
     */
    public boolean canAdd(Course c) {
        if (c == null) {
            return false;
        }
        for (Course scheduledCourse : schedule) {
            if (c.isDuplicate(scheduledCourse)) {
                return false;
            }
            try {
                c.checkConflict(scheduledCourse);
            } catch (ConflictException e) {
                return false;
            }
        }
        return true;
    }
	
}
