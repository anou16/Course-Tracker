package edu.ncsu.csc216.pack_scheduler.course.roll;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * Tests the CourseRoll Class
 * 
 * @author Neha Pothireddy
 * @author Michael Wang
 */
class CourseRollTest {
	
	 /** Course instance for CourseRoll creation */
    private Course course;

	/** LinkedList of students*/
	LinkedAbstractList<Student> student;
	
	/**
	 * Done before each method, creates a student linkedList
	 */
	@BeforeEach
	void setUp (){
		
		course = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 30, "MW", 1330, 1445);
		student = new LinkedAbstractList<Student>(20);
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Student s1 = new Student("rose", "smith", "id1", "rose@ncsu.edu", "hashedpassword");
		Student s2 = new Student("rome", "john", "id2", "rome@ncsu.edu", "hashedpassword");
		Student s3 = new Student("ral", "earl", "id3", "ral@ncsu.edu", "hashedpassword");
		student.add(s);
		student.add(s1);
		student.add(s2);
		student.add(s3);
	}
	
	
	/**
	 * Test the constructor
	 */
	@Test
	void testConstructor() {
		
		//assertEquals(4, student.size());
		
		CourseRoll c = course.getCourseRoll();
		
		 assertEquals(30, c.getEnrollmentCap());
	     assertEquals(30, c.getOpenSeats());
		
	}
	 /**
     * Tests the setEnrollmentCap method to ensure that the enrollment cap
     * can be updated within valid bounds and throws exceptions for invalid values.
     */
    @Test
    void testSetEnrollmentCap() {
    	CourseRoll c = course.getCourseRoll();

        c.setEnrollmentCap(35);
        assertEquals(35, c.getEnrollmentCap());
        assertEquals(35, c.getOpenSeats());

        assertThrows(IllegalArgumentException.class, () -> c.setEnrollmentCap(7));

        assertThrows(IllegalArgumentException.class, () -> c.setEnrollmentCap(255));

        assertThrows(IllegalArgumentException.class, () -> c.setEnrollmentCap(3));
    }

   
    /**
     * Tests the enroll method to ensure students are added to the roll and
     * exceptions are thrown when the conditions are not met.
     */
    @Test
    void testEnroll() {
    	CourseRoll c = course.getCourseRoll();
    	c.setEnrollmentCap(10);
        Student newStudent = new Student("new", "student", "id4", "new@ncsu.edu", "hashedpassword");

        c.enroll(newStudent);
        //assertEquals(5, student.size());
        assertEquals(9, c.getOpenSeats());
        //assertTrue(student.contains(newStudent));

        assertThrows(IllegalArgumentException.class, () -> c.enroll(null));

        for (int i = 0; i < 9; i++) {
            c.enroll(new Student("test" + i, "student", "id" + i, "test" + i + "@ncsu.edu", "hashedpassword"));
        }
        assertEquals(0, c.getOpenSeats());
        assertThrows(IllegalArgumentException.class, () -> c.enroll(new Student("extra", "student", "id5", "extra@ncsu.edu", "hashedpassword")));
    }
    
    
    
    /**
     * Tests the drop method to ensure that a student can be removed from the
     * CourseRoll and that exceptions are thrown when conditions are not met
     * (e.g., null student, student not enrolled).
     */
    @Test
    void testDrop() {
       
    	CourseRoll c = course.getCourseRoll();
    	c.setEnrollmentCap(10);
        Student sToDrop = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
        c.enroll(sToDrop);
        
        assertEquals(9, c.getOpenSeats());
        
        c.drop(sToDrop);
        //assertEquals(3, student.size());
        assertEquals(10, c.getOpenSeats());
    }
    /**
     * Tests the getOpenSeats method to ensure it returns the correct number of
     * available seats based on the current enrollment.
     */
    @Test
    void testGetOpenSeats() {
    	CourseRoll c = course.getCourseRoll();
    	c.setEnrollmentCap(10);
        assertEquals(10, c.getOpenSeats());

        Student newStudent = new Student("new", "student", "id6", "new@ncsu.edu", "hashedpassword");
        c.enroll(newStudent);
        assertEquals(9, c.getOpenSeats());
    }

    
    /**
     * Tests the canEnroll method to check if a student can enroll based on
     * availability and if the student is already enrolled.
     */
    @Test
    void testCanEnroll() {
    	CourseRoll c = course.getCourseRoll();
    	c.setEnrollmentCap(10);
        Student newStudent = new Student("new", "student", "id7", "new@ncsu.edu", "hashedpassword");
        assertEquals(10, c.getOpenSeats());
        
        assertTrue(c.canEnroll(newStudent));

        c.enroll(newStudent);
        assertFalse(c.canEnroll(newStudent));
    }
   
    /**
     * Test drop method
     */
    @Test
    void testDropError() {
    	CourseRoll c = course.getCourseRoll();
    	c.setEnrollmentCap(10);
    	 Student newStudent = new Student("new", "student", "id7", "new@ncsu.edu", "hashedpassword");
         assertEquals(10, c.getOpenSeats());
         
         assertTrue(c.canEnroll(newStudent));

         c.enroll(newStudent);
         assertEquals(9, c.getOpenSeats());
         
     
         assertThrows(IllegalArgumentException.class, () -> c.drop(null));
         
         
         
    }
    @Test
    public void testSetEnrollmentCapValid() {
    	CourseRoll c = course.getCourseRoll();
    	c.setEnrollmentCap(10);
    	c.setEnrollmentCap(20);
    	assertEquals(20, c.getEnrollmentCap());
    }

    @Test
    public void testSetEnrollmentCapInvalid() {
    	CourseRoll c = course.getCourseRoll();
    	c.setEnrollmentCap(20);
        Student s = new Student("John", "Doe", "jdoe", "jdoe@ncsu.edu", "password", 15);
        c.enroll(s);
        assertThrows(IllegalArgumentException.class, () -> c.setEnrollmentCap(1));
    }
    }




