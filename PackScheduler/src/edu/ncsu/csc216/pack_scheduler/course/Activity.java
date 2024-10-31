package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Parent class of event and course and contains activity information
 * @author Hinano Turner
 */
public abstract class Activity implements Conflict {

	/** Hour bound for military time */
	public static final int UPPER_HOUR = 24;
	
	/** Minute bound for military time */
	public static final int UPPER_MINUTE = 60;
	
	/** Course's title */
	private String title;
	
	/** Course's meeting days */
	private String meetingDays;
	
	/** Course's starting time */
	private int startTime;
	
	/** Course's ending time */
	private int endTime;

    /**
     * Constructs a Activity object with values for all fields. i
     * 
     * @param title of course
     * @param meetingDays of course
     * @param startTime of course 
     * @param endTime of course
     */
    public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }
    
	/**
	 * Returns the Course's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is invalid
	 */
	public void setTitle(String title) {
	
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
	
		}
	
		this.title = title;
	}

	/**
	 * Return the Course's meetingDays
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's startTime
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's endTIme
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Checks course validity of meeting days and times
	 * @param meetingDays of course 
	 * @param startTime of course
	 * @param endTime of course 
	 * @throws IllegalArgumentException if invalid meeting days and times
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
	
		// Throw exception if meetingDays is null or an empty string
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		// Handles the "Arranged" case
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
			// Sets meetingDays, startTime and endTime
			this.meetingDays = "A";
			this.startTime = 0;
			this.endTime = 0;
			
		} else {
	
		
	
		// Break apart startTime and endTime into hours and minutes
		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;
	
		// Checks if start time is valid, within bounds
		if (startHour < 0 || startHour >= UPPER_HOUR || startMin < 0 || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		// Checks if end time is valid, within bounds
		if (endHour < 0 || endHour >= UPPER_HOUR || endMin < 0 || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
	
		// sets fields
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	
		}
	
	}

	/**
	 * Strings together meeting day and time in standard format
	 * @return String of meeting day and time 
	 */
	public String getMeetingString() {
		String mString = "";
	
		if ("A".contains(meetingDays)) {
			mString = "Arranged";
		} else {
			mString = meetingDays + " " + getTimeString(startTime) + "-" + getTimeString(endTime);
		}
	
		return mString;
	
	}

	/**
	 * Converts military time format to standard time format.
	 * 
	 * @param time military time in HHMM format
	 * @return a string representing the time in standard format
	 */
	private String getTimeString(int time) {
	
		String finalMin = "";
		String period = "AM";
	
		// Break apart military time to standard and period
		int hour = time / 100;
		int min = time % 100;
	
		// convert standard format and determine am/pm
		if (hour > 12) {
			hour -= 12;
			period = "PM";
		} else if (hour == 12) {
			period = "PM";
		}
	
		// minutes less than 10
		if (min < 10) {
			finalMin = "0" + min;
		} else {
			finalMin = finalMin + min;
		}
	
		// final time string
		String finalTime = hour + ":" + finalMin + period;
	
		return finalTime;
	
	}

	/**short version of the array of information,
	 * populate the rows of the course catalog and student schedule
	 * @return String[] of short display
	 */
	public abstract String[] getShortDisplayArray();

	/**short version of the array of information,
	 * used to display the final schedule
	 * @return String[] of long display
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Checks if fields are duplicate
	 * @param activity compares to find duplicate
	 * @return boolean if true or not
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Checks for any conflicts between events and courses
	 * @throws ConflictException if course/event has conflicting event
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		boolean bothArranged = false;

		if ("A".equals(meetingDays) && "A".equals(possibleConflictingActivity.meetingDays)) {
			bothArranged = true;
		}
		
		String possibleDay = possibleConflictingActivity.getMeetingDays();
		for (char m1 : meetingDays.toCharArray()) {
			for (char m2 : possibleDay.toCharArray()) {
				
				if(m1 == m2 && startTime <= possibleConflictingActivity.getEndTime() && possibleConflictingActivity.startTime <= endTime && !bothArranged){
					
						throw new ConflictException("Schedule conflict.");
				}
			}
		}
		}

	

	/**
	 * Generates hash code for activity class
	 * @return result of hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares an inputed object to a given activity object.
	 * @return boolean, true if equivalent, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}