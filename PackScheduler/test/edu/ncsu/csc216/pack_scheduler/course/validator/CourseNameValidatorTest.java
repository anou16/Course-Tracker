package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Class for CourseNameValidatorFSM Class
 * 
 * Tests the finite state machine (FSM) for validating course names 
 * based on specific transitions and state handling.
 * 
 * Validates correct and incorrect course name patterns.
 * 
 * @author Neha Pothireddy 
 * @author Michael Wang
 */
class CourseNameValidatorTest {
    
    /** Object of the CourseNameValidatorFSM class */
    private CourseNameValidator name;
    
    /** 
     * Initializes the CourseNameValidator object before each test 
     */
    @BeforeEach
    public void setUp() {
        name = new CourseNameValidator();
    }
    
    /**
     * Tests when the first character is neither a letter nor a digit (invalid start).
     */
    @Test
    void testCourseNameStartsWithInvalidCharacter() {
        assertThrows(InvalidTransitionException.class, () -> name.isValid("!ABC223"));
    }
    
    /**
     * Tests when the first character is a digit (invalid start).
     */
    @Test
    void testCourseNameStartsWithDigit() {
        assertThrows(InvalidTransitionException.class, () -> name.isValid("2ABC223"));
    }
    
    /**
     * Tests a valid course name with one letter and three digits.
     */
    @Test
    void testValidOneLetterAndThreeDigits() {
        try {
            assertTrue(name.isValid("A234"));
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * Tests a valid course name with two letters and three digits.
     */
    @Test
    void testValidTwoLettersAndThreeDigits() {
        try {
            assertTrue(name.isValid("AA234"));
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * Tests a valid course name with three letters and three digits.
     */
    @Test
    void testValidThreeLettersAndThreeDigits() {
        try {
            assertTrue(name.isValid("AAA234"));
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * Tests a valid course name with four letters and three digits.
     */
    @Test
    void testValidFourLettersAndThreeDigits() {
        try {
            assertTrue(name.isValid("AAAA234"));
        } catch (Exception e) {
            fail();
        }
    }
    
    /**
     * Tests an invalid course name with five letters and three digits.
     */
    @Test
    void testInvalidFiveLettersAndThreeDigits() {
        assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCDE223"));
    }
    
    /**
     * Tests an invalid course name with letters interspersed between digits.
     */
    @Test
    void testInvalidLetterInSecondDigitPosition() {
        assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCE2L3"));
    }
    
    /**
     * Tests an invalid course name with letters in the third digit position.
     */
    @Test
    void testInvalidLetterInThirdDigitPosition() {
    	try {
			assertFalse(name.isValid("ABCE22"));
		} catch (InvalidTransitionException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("not valid");
		}
       // assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCE22"));
    }
    
    /**
     * Tests an invalid course name with four letters and four digits.
     */
    @Test
    void testInvalidFourLettersAndFourDigits() {
        assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCE2233"));
    }
    
    /** 
     * Tests an invalid course name with two-letter suffix.
     */
    @Test
    void testInvalidTwoLetterSuffix() {
        assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCE223CC"));
    }
    
    /** 
     * Tests an invalid course name with a digit after the suffix letter.
     */
    @Test
    void testInvalidLetterAndDigitSuffix() {
        assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCE223C9"));
    }
    
    
//    /**
//     * Tests the SuffixState when a digit is used after a valid suffix, which should throw an InvalidTransitionException.
//     */
//    @Test
//    void testInvalidDigitAfterSuffix() {
//        // Test a valid course name with letters and digits, followed by an invalid digit after the suffix
//        assertThrows(InvalidTransitionException.class, () -> name.isValid("CSC216A1"), 
//                "Course name cannot have digits after the suffix.");
//    }
//    
}

