package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Faculty class using specific test data.
 */
public class FacultyTest {

    /** Test data */
    private static final String FIRST_NAME = "Michael";
    private static final String LAST_NAME = "Wang";
    private static final String ID = "cwang69";
    private static final String EMAIL = "cwang69@ncsu.edu";
    private static final String PASSWORD = "hashedpassword";
    private static final int MAX_COURSES = 3;

    /** Tests the Faculty constructor and getters */
    @Test
    public void testFacultyConstructor() {
        Faculty f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        assertEquals(FIRST_NAME, f.getFirstName());
        assertEquals(LAST_NAME, f.getLastName());
        assertEquals(ID, f.getId());
        assertEquals(EMAIL, f.getEmail());
        assertEquals(PASSWORD, f.getPassword());
        assertEquals(MAX_COURSES, f.getMaxCourses());
    }

    /** Tests setMaxCourses with valid and invalid values */
    @Test
    public void testSetMaxCourses() {
        Faculty f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        f.setMaxCourses(2);
        assertEquals(2, f.getMaxCourses());

        f.setMaxCourses(1);
        assertEquals(1, f.getMaxCourses());

        try {
            f.setMaxCourses(0);
            fail("Should throw IllegalArgumentException for maxCourses < MIN_COURSES");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid maxCourses", e.getMessage());
        }

        try {
            f.setMaxCourses(4);
            fail("Should throw IllegalArgumentException for maxCourses > MAX_COURSES");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid maxCourses", e.getMessage());
        }
    }

    /** Tests the toString method */
    @Test
    public void testToString() {
        Faculty f = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        String expected = "Michael,Wang,cwang69,cwang69@ncsu.edu,hashedpassword,3";
        assertEquals(expected, f.toString());
    }

    /** Tests equals and hashCode methods */
    @Test
    public void testEqualsAndHashCode() {
        Faculty f1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        Faculty f2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES);
        Faculty f3 = new Faculty("Jane", "Doe", "jdoe", "jdoe@ncsu.edu", PASSWORD, 2);

        assertTrue(f1.equals(f2));
        assertFalse(f1.equals(f3));
        assertEquals(f1.hashCode(), f2.hashCode());
        assertNotEquals(f1.hashCode(), f3.hashCode());
    }
}
