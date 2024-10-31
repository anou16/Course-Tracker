
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

//import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Unit tests for Student Class
 * @author Christopher Gilbert
 * @author Samir Naseri 
 */
class StudentTest {

	/**
	 * Test method for invalid credits (one off upper/lower limit)
	 */
	@Test
	void testStudentConstructorInvalidCredits() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", "email@mail.com", "password", 19));
					assertEquals("Invalid max credits", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", "email@mail.com", "password", 2));
					assertEquals("Invalid max credits", e2.getMessage());
	} 

	/**
	 * Test method for valid credits, upper limit
	 */
	@Test
	void testStudentConstructorValidCreditsOne() {
		Student s = assertDoesNotThrow(
				() -> new Student("first", "last", "id", "email@mail.com", "password", 18),
				"Should not throw exception");
		assertAll("Student",
				() -> assertEquals("first", s.getFirstName(), "incorrect name"),
				() -> assertEquals("last", s.getLastName(), "incorrect name"),
				() -> assertEquals("id", s.getId(), "incorrect id"),
				() -> assertEquals("email@mail.com", s.getEmail(), "email"),
				() -> assertEquals("password", s.getPassword(), "incorrect password"),
				() -> assertEquals(18, s.getMaxCredits(), "incorrect credits"));
	}

	/**
	 * Test method for valid credits, lower limit
	 */
	@Test
	void testStudentConstructorValidCreditsTwo() {
		Student s = assertDoesNotThrow(
				() -> new Student("first", "last", "id", "email@mail.com", "password", 3),
				"Should not throw exception");
		assertAll("Student",
				() -> assertEquals("first", s.getFirstName(), "incorrect name"),
				() -> assertEquals("last", s.getLastName(), "incorrect name"),
				() -> assertEquals("id", s.getId(), "incorrect id"),
				() -> assertEquals("email@mail.com", s.getEmail(), "email"),
				() -> assertEquals("password", s.getPassword(), "incorrect password"),
				() -> assertEquals(3, s.getMaxCredits(), "incorrect credits"));
	}

	/**
	 * Test method for valid credits, Equivalence Class
	 */
	@Test
	void testStudentConstructorValidCreditsThree() {
		Student s = assertDoesNotThrow(
				() -> new Student("first", "last", "id", "email@mail.com", "password", 10),
				"Should not throw exception");
		assertAll("Student",
				() -> assertEquals("first", s.getFirstName(), "incorrect name"),
				() -> assertEquals("last", s.getLastName(), "incorrect name"),
				() -> assertEquals("id", s.getId(), "incorrect id"),
				() -> assertEquals("email@mail.com", s.getEmail(), "email"),
				() -> assertEquals("password", s.getPassword(), "incorrect password"),
				() -> assertEquals(10, s.getMaxCredits(), "incorrect credits"));
	}

	/**
	 * Test method for valid credits, None provided
	 */
	@Test
	void testStudentConstructorValidCreditsFour() {
		Student s = assertDoesNotThrow(
				() -> new Student("first", "last", "id", "email@mail.com", "password"),
				"Should not throw exception");
		assertAll("Student",
				() -> assertEquals("first", s.getFirstName(), "incorrect name"),
				() -> assertEquals("last", s.getLastName(), "incorrect name"),
				() -> assertEquals("id", s.getId(), "incorrect id"),
				() -> assertEquals("email@mail.com", s.getEmail(), "email"),
				() -> assertEquals("password", s.getPassword(), "incorrect password"),
				() -> assertEquals(18, s.getMaxCredits(), "incorrect credits"));
	}


	/**
	 * Test method for empty password inputed
	 */
	@Test
	void testStudentConstructorEmptyPassword() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", "email@mail.com", ""));
					assertEquals("Invalid password", e1.getMessage());
	}

	/**
	 * Test method for null password inputed
	 */
	@Test
	void testStudentConstructorNullPassword() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", "email@mail.com", null));
					assertEquals("Invalid password", e1.getMessage());
	}

	/**
	 * Test method for empty email inputed
	 */
	@Test
	void testStudentConstructorEmptyEmail() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", "", "password"));
					assertEquals("Invalid email", e1.getMessage());
	}

	/**
	 * Test method for null email inputed
	 */
	@Test
	void testStudentConstructorNullEmail() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", null, "password"));
					assertEquals("Invalid email", e1.getMessage());
	}

	/**
	 * Test method for invalid email inputed (last period index before @ symbol)
	 */
	@Test
	void testStudentConstructorInvalidEmailOne() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", "mail.gmail@com", "password"));
					assertEquals("Invalid email", e1.getMessage());
	}

	/**
	 * Test method for invalid email inputed (No period symbol)
	 */
	@Test
	void testStudentConstructorInvalidEmailTwo() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", "mail@gmailcom", "password"));
					assertEquals("Invalid email", e1.getMessage());
	}

	/**
	 * Test method for invalid email inputed (No @ symbol)
	 */
	@Test
	void testStudentConstructorInvalidEmailThree() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "id", "mailgmail.com", "password"));
					assertEquals("Invalid email", e1.getMessage());
	}

	/**
	 * Test method for empty id inputed
	 */
	@Test
	void testStudentConstructorEmptyId() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", "", "mail@gmail.com", "password"));
					assertEquals("Invalid id", e1.getMessage());
	}

	/**
	 * Test method for null email inputed
	 */
	@Test
	void testStudentConstructorNullId() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "last", null, "mail@gmail.com", "password"));
					assertEquals("Invalid id", e1.getMessage());
	}

	/**
	 * Test method for empty id inputed
	 */
	@Test
	void testStudentConstructorEmptyLastName() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", "", "id", "mail@gmail.com", "password"));
					assertEquals("Invalid last name", e1.getMessage());
	}

	/**
	 * Test method for null email inputed
	 */
	@Test
	void testStudentConstructorNullLastName() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("first", null, "id", "mail@gmail.com", "password"));
					assertEquals("Invalid last name", e1.getMessage());
	}

	/**
	 * Test method for empty id inputed
	 */
	@Test
	void testStudentConstructorEmptyFirstName() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", "last", "id", "mail@gmail.com", "password"));
					assertEquals("Invalid first name", e1.getMessage());
	}

	/**
	 * Test method for null email inputed
	 */
	@Test
	void testStudentConstructorNullFirstName() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, "last", "id", "mail@gmail.com", "password"));
					assertEquals("Invalid first name", e1.getMessage());
	}

	/**
	 * Test method for Constructor without credits provided
	 */
	@Test
	void testStudentConstructorNoCredits() {
		Student s = assertDoesNotThrow(
				() -> new Student("first", "last", "id", "email@mail.com", "password"),
				"Should not throw exception");
		assertAll("Student",
				() -> assertEquals("first", s.getFirstName(), "incorrect name"),
				() -> assertEquals("last", s.getLastName(), "incorrect name"),
				() -> assertEquals("id", s.getId(), "incorrect id"),
				() -> assertEquals("email@mail.com", s.getEmail(), "email"),
				() -> assertEquals("password", s.getPassword(), "incorrect password"),
				() -> assertEquals(18, s.getMaxCredits(), "incorrect credits"));
	}

	/**
	 * Test method for First Name setter
	 */
	@Test
	void testSetFirstName() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setFirstName(null));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setFirstName(""));
		assertEquals("Invalid first name", e1.getMessage()); //Check correct exception message
		assertEquals("Invalid first name", e2.getMessage()); //Check correct exception message
		assertEquals("first", s.getFirstName()); //Check that first name didn't change
	}

	/**
	 * Test method for Last Name Setter
	 */
	@Test
	void testSetLastName() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setLastName(null));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setLastName(""));
		assertEquals("Invalid last name", e1.getMessage());
		assertEquals("Invalid last name", e2.getMessage());
		assertEquals("last", s.getLastName());
		}

	/**
	 * Test method for Email setter
	 */
	@Test
	void testSetEmail() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setEmail(null));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail(""));
		assertEquals("Invalid email", e1.getMessage());
		assertEquals("Invalid email", e2.getMessage());
		assertEquals("email@ncsu.edu", s.getEmail());
		}

	/**
	 * Test method for password setter
	 */
	@Test
	void testSetPassword() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setPassword(null));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setPassword(""));
		assertEquals("Invalid password", e1.getMessage());
		assertEquals("Invalid password", e2.getMessage());
		assertEquals("hashedpassword", s.getPassword());
		}

	/**
	 * Test method for max credits setter, student object created without credits
	 */
	@Test
	void testSetMaxCredits() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setMaxCredits(19));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCredits(2));
		assertEquals("Invalid max credits", e1.getMessage());
		assertEquals("Invalid max credits", e2.getMessage());
		assertEquals(18, s.getMaxCredits());
	}

	/**
	 * Test method for max credits setter, student object given credits
	 */
	@Test
	void testSetMaxCreditsTwo() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 10);
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setMaxCredits(19));
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setMaxCredits(2));
		assertEquals("Invalid max credits", e1.getMessage());
		assertEquals("Invalid max credits", e2.getMessage());
		assertEquals(10, s.getMaxCredits());
	}

	/**
	 * Test method for hash code
	 */
	@Test
	void testHashCode() {
		Student s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 10);
		Student s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 10);
		Student s3 = new Student("CHANGED", "last", "id", "email@ncsu.edu", "hashedpassword", 10);
		Student s4 = new Student("first", "CHANGED", "id", "email@ncsu.edu", "hashedpassword", 10);
		Student s5 = new Student("first", "last", "CHANGED", "email@ncsu.edu", "hashedpassword", 10);
		Student s6 = new Student("first", "last", "id", "CHANGED@ncsu.edu", "hashedpassword", 10);
		Student s7 = new Student("first", "last", "id", "email@ncsu.edu", "CHANGED", 10);
		Student s8 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);


		// Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());

		// Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());



	}


	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Student#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		Student s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 10);
		Student s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 10);
		Student s3 = new Student("CHANGED", "last", "id", "email@ncsu.edu", "hashedpassword", 10);
		Student s4 = new Student("first", "CHANGED", "id", "email@ncsu.edu", "hashedpassword", 10);
		Student s5 = new Student("first", "last", "CHANGED", "email@ncsu.edu", "hashedpassword", 10);
		Student s6 = new Student("first", "last", "id", "CHANGED@ncsu.edu", "hashedpassword", 10);
		Student s7 = new Student("first", "last", "id", "email@ncsu.edu", "CHANGED", 10);
		Student s8 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 3);

		// Test for equality in both directions
		assertEquals(s1, s2);
		assertEquals(s2, s1);

		// Test for each of the fields
		assertNotEquals(s1, s3);
		assertNotEquals(s1, s4);
		assertNotEquals(s1, s5);
		assertNotEquals(s1, s6);
		assertNotEquals(s1, s7);
		assertNotEquals(s1, s8);

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Student#toString()}.
	 */
	@Test
	void testToString() {
		Student s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashPW");
		assertEquals("first,last,id,email@ncsu.edu," + "hashPW" + ",18", s1.toString());
		Student s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashPW", 10);
		assertEquals("first,last,id,email@ncsu.edu," + "hashPW" + ",10", s2.toString());
		}
	
	/**
	 * Test for CompareTo method Equal
	 */
	@Test
	void testCompareToEqual() {
	Student s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashPW");
	Student s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashPW", 10); 
	assertEquals(0, s1.compareTo(s2));
	}
	
	/**
	 * Test for CompareTo method different last names
	 */
	@Test
	void testCompareToMoreLN() {
	Student s1 = new Student("first", "A", "id", "email@ncsu.edu", "hashPW");
	Student s2 = new Student("first", "B", "id", "email@ncsu.edu", "hashPW", 10); 
	assertTrue(0 > s1.compareTo(s2));
	assertFalse(0 > s2.compareTo(s1));
	Student s3 = new Student("first", "B", "id", "email@ncsu.edu", "hashPW");
	Student s4 = new Student("first", "A", "id", "email@ncsu.edu", "hashPW", 10); 
	assertTrue(0 < s3.compareTo(s4));
	assertFalse(0 < s4.compareTo(s3));
	Student s5 = new Student("first", "B", "A", "email@ncsu.edu", "hashPW");
	Student s6 = new Student("first", "A", "B", "email@ncsu.edu", "hashPW", 10); 
	assertTrue(0 < s5.compareTo(s6));
	assertFalse(0 < s6.compareTo(s5));
	}
	
	/**
	 * Test for CompareTo method different first names
	 */
	@Test
	void testCompareToMoreFN() {
	Student s1 = new Student("A", "last", "id", "email@ncsu.edu", "hashPW");
	Student s2 = new Student("B", "last", "id", "email@ncsu.edu", "hashPW", 10); 
	assertTrue(0 > s1.compareTo(s2));
	assertFalse(0 > s2.compareTo(s1));
	Student s3 = new Student("B", "last", "id", "email@ncsu.edu", "hashPW");
	Student s4 = new Student("A", "last", "id", "email@ncsu.edu", "hashPW", 10); 
	assertTrue(0 < s3.compareTo(s4));
	assertFalse(0 < s4.compareTo(s3));
	}
	
	/**
	 * Test for CompareTo method different id's
	 */
	@Test 
	void testCompareToMoreId() {
	Student s1 = new Student("first", "last", "A", "email@ncsu.edu", "hashPW");
	Student s2 = new Student("first", "last", "B", "email@ncsu.edu", "hashPW", 10); 
	assertTrue(0 > s1.compareTo(s2));
	assertFalse(0 > s2.compareTo(s1));
	Student s3 = new Student("first", "last", "B", "email@ncsu.edu", "hashPW"); 
	Student s4 = new Student("first", "last", "A", "email@ncsu.edu", "hashPW", 10); 
	assertTrue(0 < s3.compareTo(s4));
	assertFalse(0 < s4.compareTo(s3));
	
	}
	/**
	 * Test method for getSchedule().
	 */
	@Test
	void testGetSchedule() {
	    Student student = new Student("John", "Doe", "jdoe", "jdoe@example.com", "hashed_pw", 15);
	    assertNotNull(student.getSchedule(), "Schedule should be initialized and not null");
	    assertEquals(0, student.getSchedule().getScheduledCourses().length, "Schedule should be empty initially");
	}

	/**
	 * Test the can add method in the student class.
	 */
	@Test
	void testCanAdd() {
	    Student s = new Student("John", "Doe", "jdoe", "jdoe@example.com", "hashed_pw", 15);
	    Course course = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    s.getSchedule().addCourseToSchedule(course); 
	    assertFalse(s.canAdd(course), "Duplicate course.");
	}
	

}
