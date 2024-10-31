package edu.ncsu.csc216.pack_scheduler.user.schedule;



import edu.ncsu.csc216.pack_scheduler.course.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ScheduleTest {
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	/** Enrollment course roll capacity */
	private static final int ENROLLMENT_CAP = 10;

	/**
	 * Test addCourseToSchedule method
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c1 = new Course("CSC216", TITLE, "004", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		
		//Attempt to add a course
		assertTrue(s.addCourseToSchedule(c));
		assertThrows(IllegalArgumentException.class, () -> s.addCourseToSchedule(c));
		assertThrows(IllegalArgumentException.class, () -> s.addCourseToSchedule(c1));
	
	}
	
	
	@Test
	void testResetSchedule() {
		Schedule s = new Schedule();
		s.setTitle("title");
		assertEquals("title", s.getTitle());
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertTrue(s.addCourseToSchedule(c));
		
		s.resetSchedule();
		assertEquals("My Schedule", s.getTitle());
	}
	
	/**
	 * Test removeCourseFromSchedule method
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule s = new Schedule();
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c1 = new Course("HESF110", TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 0000, 0030);
		
		assertTrue(s.addCourseToSchedule(c));
		assertTrue(s.addCourseToSchedule(c1));
		
		String [][] scheduleArray = s.getScheduledCourses();
		
		assertEquals("001", scheduleArray[0][1]);
		
		assertTrue(s.removeCourseFromSchedule(c));
		
		scheduleArray = s.getScheduledCourses();
		assertEquals(1, scheduleArray.length);		
	
	}
	
	/**
	 * Tests setTitle method
	 */
	@Test
	void testSetTitle() {
		Schedule s = new Schedule();
		s.setTitle("Neha's Schedule");
		
		assertEquals("Neha's Schedule", s.getTitle());
	}
	
	/**
	 * Test getScheduledCourses method
	 */
	@Test
	public void testGetScheduledCourses(){
		Schedule s = new Schedule();
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		//String [][] scheduleArray = s.getScheduledCourses();
		
		//Attempt to add a course
		assertTrue(s.addCourseToSchedule(c));
		
		String [][] scheduleArray = s.getScheduledCourses();
		
		assertEquals("CSC216", scheduleArray[0][0]);
		assertEquals("001", scheduleArray[0][1]);
		assertEquals("Software Development Fundamentals", scheduleArray[0][2]);

        Course c1 = new Course("CSC226", "Discrete Math", "001", 3, "professor", 10, "MW", 1200, 1315);
        s.addCourseToSchedule(c1);
        
        scheduleArray = s.getScheduledCourses();
        assertEquals(2, scheduleArray.length);
        assertEquals("CSC226", scheduleArray[1][0]);
	
	
	}
	
	/**
	 * Testing the can add method in schedule.
	 */
    @Test
    public void testCanAdd() {
        Schedule s = new Schedule();
        Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        assertTrue(s.canAdd(c));
    }
    
    /**
     * Testing the get schedule credits in the schedule class.
     */
    @Test
    public void testGetScheduleCredits() {
        Schedule s = new Schedule();
        Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        assertEquals(0, s.getScheduleCredits());
        s.addCourseToSchedule(c);
        assertEquals(3, s.getScheduleCredits());
    }
}
