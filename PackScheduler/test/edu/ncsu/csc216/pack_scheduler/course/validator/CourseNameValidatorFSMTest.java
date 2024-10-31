package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

/**
 * Test Class for CourseNameValidatorFSM Class.
 * Tests various transitions and edge cases for the finite state machine.
 * 
 * @author Neha Pothireddy
 * @author Michael Wang
 */
class CourseNameValidatorFSMTest {

    /** Instance of the CourseNameValidatorFSM class */
    private CourseNameValidatorFSM name;

    /** 
     * Tests when the first character is neither a letter nor a digit (invalid start).
     */
    @Test
    void testStateStart() {
        name = new CourseNameValidatorFSM();
        assertThrows(InvalidTransitionException.class, () -> name.isValid("!ABC223"));
    }

    /** 
     * Tests when the first character is a digit (invalid start).
     */
    @Test
    void testStateInitialInvalid() {
        name = new CourseNameValidatorFSM();
        assertThrows(InvalidTransitionException.class, () -> name.isValid("2ABC223"));
    }

    /** 
     * Tests a valid course name with one letter and three digits.
     */
    @Test
    void testStateOneLetters() {
        name = new CourseNameValidatorFSM();
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
    void testStateTwoLetters() {
        name = new CourseNameValidatorFSM();
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
    void testStateThreeLetters() {
        name = new CourseNameValidatorFSM();
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
    void testStateFourLetters() {
        name = new CourseNameValidatorFSM();
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
    void testStateLLLLInvalid() {
        name = new CourseNameValidatorFSM();
        assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCDE223"));
    }

    /** 
     * Tests an invalid course name with a letter in the second digit position.
     */
    @Test
    void testStateDInvalid() {
        name = new CourseNameValidatorFSM();
        assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCE2L3"));
    }

    /** 
     * Tests an invalid course name with a letter in the third digit position.
     */
    @Test
    void testStateDDInvalid() {
        name = new CourseNameValidatorFSM();
        assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCE22L"));
    }

    /** 
     * Tests an invalid course name with four letters and four digits.
     */
    @Test
    void testStateDDDInvalid() {
        name = new CourseNameValidatorFSM();
        assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCE2233"));
    }

    /** 
     * Tests an invalid course name with two-letter suffix.
     */
    @Test
    void testStateSuffixInvalid() {
        name = new CourseNameValidatorFSM();
        assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCE223CC"));
    }

    /** 
     * Tests an invalid course name with a letter and digit suffix.
     */
    @Test
    void testStateSuffixInvalidDigit() {
        name = new CourseNameValidatorFSM();
        assertThrows(InvalidTransitionException.class, () -> name.isValid("ABCE223C9"));
    }

    /** 
     * Tests an invalid course name with more than three digits.
     */
    @Test
    void testStateMoreThanThreeDigits() {
        name = new CourseNameValidatorFSM();
        assertThrows(InvalidTransitionException.class, () -> name.isValid("CS12345"));
    }

    /** 
     * Tests a valid course name with a single letter suffix.
     */
    @Test
    void testValidWithSuffix() {
        name = new CourseNameValidatorFSM();
        try {
            assertTrue(name.isValid("CSC216A"));
        } catch (InvalidTransitionException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /** 
     * Tests an invalid course name with multiple suffix letters.
     */
    @Test
    void testInvalidMultipleSuffixLetters() {
        name = new CourseNameValidatorFSM();
        assertThrows(InvalidTransitionException.class, () -> name.isValid("CSC216AB"));
    }

    /** 
     * Tests an invalid course name with special characters.
     */
    @Test
    void testInvalidSpecialCharacters() {
        name = new CourseNameValidatorFSM();
        assertThrows(InvalidTransitionException.class, () -> name.isValid("CS@216"));
    }
}
