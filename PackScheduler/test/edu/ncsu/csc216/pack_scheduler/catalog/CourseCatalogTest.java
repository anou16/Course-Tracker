package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * This class Tests the Course Catalog Class
 * 
 * @author Samir Naseri 
 * @author Chris
 * @author Hinano Turner 
 */
public class CourseCatalogTest {
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	 
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
	/** New catalog*/
	private CourseCatalog catalog = new CourseCatalog(); 
    /** Course Enrollment Cap */
	private static final int ENROLLMENT_CAP = 10;

	
	/**
	 * Test CourseCatalog.addCourseToCatalog()
	 */
    @Test
    public void testAddCourseToCatalog() {
        assertTrue(catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "hlturne3", 10, "MWF", 1300, 1430));
        assertTrue(catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
        
        assertFalse(catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "hlturne3", 10, "MWF", 1300, 1430));
    }
	
	/**
	 * Test CourseCatalog.addCourseToCatalog()
	 */
	@Test
	public void testAddCourseToCatalogTEST() {
        // Verify adding a new course returns true
        assertTrue(catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "hlturne3", 10, "MWF", 1300, 1430));
        assertTrue(catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));
        assertTrue(catalog.addCourseToCatalog("CSC333", TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));

        // Verify adding an existing course returns false
        assertFalse(catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "hlturne3", 10, "MWF", 1300, 1430));
    }
	
	/**
	 * Test CourseCatalog.removeCourseToCatalog()
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		//add courses to catalog 
		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "hlturne3", 10, "MWF", 1300, 1430);
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        
		//Verify removing a course returns true
		assertTrue(catalog.removeCourseFromCatalog("CSC116", "002"));
        // Verify removing a non-existing course returns false
        assertFalse(catalog.removeCourseFromCatalog("CSC116", "002"));
        
    }
	
	/**
	 * Test CourseCatalog.getCourseFromCatalog()
	 */
	@Test
	public void testGetCourseFromCatalog() {
	
		//Attempt to get a course that doesn't exist
		assertNull(catalog.getCourseFromCatalog("CSC492", "001"));
		
		//Add course to catalog
		catalog.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 3, "hlturne3", 10, "MWF", 1300, 1430);
		Course c = catalog.getCourseFromCatalog("CSC216", "001");
		assertEquals(c, catalog.getCourseFromCatalog("CSC216", "001"));
	}
	

	/**
	 * Test CourseCatalog.getCourseCatalog()()
	 */
	@Test 
	public void testGetCourseCatalog() {
		//create new catalog
		CourseCatalog getCatalog = new CourseCatalog();
		//add course to catalog
		getCatalog.addCourseToCatalog("CSC216", "Software Development Fundamentals", "003", 3, "hlturne3", 10, "MWF", 1300, 1430);
        getCatalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "hlturne3", 10, "MWF", 1300, 1430);

        String [][] catalogArray = getCatalog.getCourseCatalog();
        
       	//make sure there is 2 courses 
      	assertEquals(2, catalogArray.length);
       
      	//Row 0
      	assertEquals("CSC116", catalogArray[0][0]);
      	assertEquals("002", catalogArray[0][1]);
      	assertEquals("Intro to Programming - Java", catalogArray[0][2]);
        //Row 1
      	assertEquals("CSC216", catalogArray[1][0]);
      	assertEquals("003", catalogArray[1][1]);
      	assertEquals("Software Development Fundamentals", catalogArray[1][2]);
   
	
	}
	

	/**
	 * Test CourseCatalog.saveCourseCatalog()
	 */
	@Test 
	public void testsaveCourseCatalog() {
		catalog.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "spbalik", 10, "MW", 1250, 1440);
		catalog.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		assertEquals(2, catalog.getCourseCatalog().length);
		catalog.saveCourseCatalog("test-files/actual_course_records.txt");
		//checks to see there is no exception thrown
		assertDoesNotThrow(() -> catalog.saveCourseCatalog("test-files/actual_course_records.txt"));


	}
	
	/**
	 * Test CourseCatalog.loadCoursesFromFile()
	 */
	@Test 
	public void testLoadCoursesFromFile() {
		
		catalog.loadCoursesFromFile(validTestFile);
        String [][] catalogArray = catalog.getCourseCatalog();
        assertEquals(13, catalogArray.length);
        
        //Row 0
      	assertEquals("CSC116", catalogArray[0][0]);
      	assertEquals("001", catalogArray[0][1]);
      	assertEquals("Intro to Programming - Java", catalogArray[0][2]);
        //Row 13
      	assertEquals("CSC316", catalogArray[12][0]);
      	assertEquals("001", catalogArray[12][1]);
      	assertEquals("Data Structures and Algorithms", catalogArray[12][2]);

	}
	
	
	

}
