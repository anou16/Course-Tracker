
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

//child class of Activity 
/**
 * Course keeps track of its name, title, section, credit hours, instructor’s id, meeting days, 
 * start time and end time
 * 
 * @author Hinano Turner
 * @author Samir Naseri 
 */
public class Course extends Activity implements Comparable<Course> {
	/** CourseNameValidator object for the course name */
	private CourseNameValidator validator;

	/** Minimum length of courses name */
	public static final int MIN_NAME_LENGTH = 5;

	/** Max length of courses name */
	public static final int MAX_NAME_LENGTH = 8;

	/** Minimum letter count for course name */
	public static final int MIN_LETTER_COUNT = 1;

	/** Max letter count for course name */
	public static final int MAX_LETTER_COUNT = 4;

	/** Number of digits in course name. */
	public static final int DIGIT_COUNT = 3;

	/** Length of section number */
	public static final int SECTION_LENGTH = 3;

	/** Minimum credit available of courses */
	public static final int MIN_CREDITS = 1;

	/** Max credit available of courses */
	public static final int MAX_CREDITS = 5;

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Keeping track of a courses roll. */
    private CourseRoll roll;

	
	/**
	 * Constructs a Course object with values for all fields. 
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 * @param enrollmentCap - new parameter, follows id of instructor
	 */
	 public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
	            int startTime, int endTime) {
	        super(title, meetingDays, startTime, endTime);
	        this.validator = new CourseNameValidator();
	        setName(name);
	        setSection(section);
	        setCredits(credits);
	        setInstructorId(instructorId);
	        this.roll = new CourseRoll(enrollmentCap);
	    }

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param enrollmentCap - new paramater
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}
	
	/**
	 * Returns a CourseRoll Object
	 * 
	 * @return CourseRoll Object
	 */
    public CourseRoll getCourseRoll() {
        return roll;
    }

	/**
	 * Returns the Course's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if invalid course names
	 */
	private void setName(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
//
//		// Throw exception if the name is null or an empty string
//		if (name == null || name.length() == 0) {
//			throw new IllegalArgumentException("Invalid course name.");
//		}
//
//		// Throws exception if the name contains less than 5 character or greater than 8
//		// characters
//		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
//			throw new IllegalArgumentException("Invalid course name.");
//		}
//
//		// initializes counter for number of letters
//		int numLetter = 0;
//		
//		// initializes counter for number of digits
//		int numDigits = 0;
//		
//		// initializes boolean flag for finding a space to false
//		boolean spaceExists = false;
//
//		// Check for pattern of L[LLL] NNN
//		for (int i = 0; i < name.length(); i++) {
//			char c = name.charAt(i);
//			if (!spaceExists) {
//				if (Character.isLetter(c)) {
//					numLetter++;
//				} else if (c == ' ' || c == '\\') {
//					spaceExists = true;
//				} else {
//					throw new IllegalArgumentException("Invalid course name.");
//				}
//			} else {
//				if (Character.isDigit(c)) {
//					numDigits++;
//				} else {
//					throw new IllegalArgumentException("Invalid course name.");
//				}
//			}
//
//		}
//
//		// Check that the number of letters is correct
//		if (numLetter < MIN_LETTER_COUNT || numLetter > MAX_LETTER_COUNT) {
//			throw new IllegalArgumentException("Invalid course name.");
//		}
//
//		// Check that the number of digits is correct
//		if (numDigits != DIGIT_COUNT) {
//			throw new IllegalArgumentException("Invalid course name.");
//		}
//
		// set this.name (field) to name (parameter) 
		try {
			if(!validator.isValid(name)) {
				throw new IllegalArgumentException("Invalid course name.");
			}
		} catch (InvalidTransitionException e) {
			// TODO Auto-generated catch block
				throw new IllegalArgumentException("Invalid course name.");
			}
		

		this.name = name;

	}

	/**
	 * Returns the Course's section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if invalid section
	 */
	public void setSection(String section) {

		// checks if section is equal three or null
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");

		}

		// checks section is all digits
		for (int i = 0; i < section.length(); i++) {
			char c = section.charAt(i);
			if (!Character.isDigit(c)) {
				throw new IllegalArgumentException("Invalid section.");
			}

		}

		this.section = section;
	}

	/**
	 * Return the Course's credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if invalid credits
	 */
	public void setCredits(int credits) {

		// checks if credit is a number and within bounds
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");

		}
		this.credits = credits;
	}

	/**
	 * Return the Course's instructorID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructorID
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if invalid instructor id
	 */
	public void setInstructorId(String instructorId) {
		// checks if id is null or empty
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + roll.getEnrollmentCap() + "," + getMeetingDays() + ","
		+ getStartTime() + "," + getEndTime();
	}

	/**
	 * Generates hash code for course class
	 * @return result of hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Compares an inputed object to a given course object.
	 * @return boolean, true if equivalent, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	
	

	/**
	 * Array of length 5 containing the Course name, section, title, roll, and meeting string.
	 * 
	 * @return String[] of short display 
	 */
	public String[] getShortDisplayArray() {
		String[] shortDisplay = {name, section, getTitle(), getMeetingString(), String.valueOf(roll.getOpenSeats())};
		return shortDisplay;
	}
	
	/**
	 * Array of length 7 containing the Course name, section, title, credits, instructorId, meeting string, empty string.
	 * @return String[] of long display
	 */
	public String[] getLongDisplayArray() {
		String creditS = Integer.toString(credits);
		String[] longDisplay = {name, section, getTitle(), creditS, instructorId, getMeetingString(), "" };
		return longDisplay;
	}
	
	/**
	 * Checks course validity of meeting days and times
	 * @param meetingDays of course
	 * @param startTime of course
	 * @param endTime of course
	 * @throws IllegalArgumentException if invalid meeting day and time
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		// Throw exception if meetingDays is null or an empty string
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
			
		}
		
		if ("A".equals(meetingDays)) {
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		} else {
		
		// handles other cases
				// hold counts for each weekday letter
				int m = 0;
				int t = 0;
				int w = 0;
				int h = 0;
				int f = 0;
				// increments based on weekday inside in meetingDays
				for (int i = 0; i < meetingDays.length(); i++) {
					char c = meetingDays.charAt(i);
					if (c == 'M') {
						m++;
					} else if (c == 'T') {
						t++;
					} else if (c == 'W') {
						w++;
					} else if (c == 'H') {
						h++;
					} else if (c == 'F') {
						f++;
					} else {
						throw new IllegalArgumentException("Invalid meeting days and times.");
					}
				}
			
				if (m > 1 || t > 1 || w > 1 || h > 1 || f > 1) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
				super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}	
		
	}

	
	/**
	 * Checks if course title is duplicate 
	 * @param activity to check duplication
	 * @return boolean true if duplicate, false otherwise
	 * @throws NullPointerException if it is null 
	 */
	public boolean isDuplicate(Activity activity) {
		if(activity == null) {
			throw new NullPointerException("Course is invalid.");
		}
		//checks for instance of Course
		if(activity instanceof Course) {
			//cast activity to Course
			Course anotherCourse = (Course) activity;
			//compares fields
			if(this.name.equals(anotherCourse.getName())) {
				return true;
			}
			
		}
		return false;
	}
	
	/**
	 * Compares 
	 * @return digit of comparability 
	 */
	@Override
	public int compareTo(Course o) {
			if (this.equals(o)) {
				return 0;
			}
			int nameDiff = this.getName().compareTo(o.getName());
			if(nameDiff != 0) {
				return nameDiff;
			}
			
			int sectionDiff = this.getSection().compareTo(o.getSection());
			if(sectionDiff != 0) {
				return sectionDiff;
			}
		
		return 0;
	}
	


}