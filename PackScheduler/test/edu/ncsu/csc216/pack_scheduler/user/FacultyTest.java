package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the Faculty class using specific test data.
 */
public class FacultyTest {

	/**
	 * First name of the test user.
	 */
	private static final String FIRST_NAME = "Michael";
	/**
	 * Last name of the test user.
	 */
	private static final String LAST_NAME = "Wang";
	/**
	 * Unique identifier (ID) of the test user.
	 */
	private static final String ID = "cwang69";
	/**
	 * Email address of the test user.
	 */
	private static final String EMAIL = "cwang69@ncsu.edu";
	/**
	 * Hashed password of the test user. Note: This is a mock value and should
	 * represent the hashed form of the password.
	 */
	private static final String PASSWORD = "hashedpassword";
	/**
	 * Maximum number of courses the test user can teach.
	 */
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

		assertEquals(f1, f2);
		assertEquals(f1.hashCode(), f2.hashCode());
		assertNotEquals(f1.hashCode(), f3.hashCode());
	}

	/** Tests the compareTo method. */
	@Test
	public void testCompareTo() {
		Faculty s1 = new Faculty("john", "doe", "jdoe", "jdoe@ncsu.edu", "password");
		Faculty s2 = new Faculty("bob", "smith", "bsmith", "bsmith@ncsu.edu", "password");
		Faculty s3 = new Faculty("jeff", "zed", "jzed", "jzed@ncsu.edu", "password");
		Faculty s4 = new Faculty("may", "doe", "madoe", "madoe@ncsu.edu", "password");
		Faculty s5 = new Faculty("may", "doe", "mdoe", "mdoe@ncsu.edu", "password");

		// Test exception returned for comparing to null
		assertThrows(NullPointerException.class, () -> s1.compareTo(null));

		// Test object equals itself
		assertEquals(0, s1.compareTo(s1));

		// Test objects before
		assertEquals(-1, s1.compareTo(s2));
		assertEquals(-1, s2.compareTo(s3));
		assertEquals(-1, s1.compareTo(s4));
		assertEquals(-1, s4.compareTo(s5));

		// Test objects after
		assertEquals(1, s2.compareTo(s1));
		assertEquals(1, s3.compareTo(s2));
		assertEquals(1, s4.compareTo(s1));
		assertEquals(1, s5.compareTo(s4));
	}
}
