/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Test class for the InvalidTransitionException Class
 * 
 * @author Neha Pothireddy
 */
class InvalidTransitionExceptionTest {

	/**
	 * Test method for InvalidTransitionException method with a string parameter
	 */
	@Test
	void testInvalidTransitionExceptionString() {
		InvalidTransitionException ie = new InvalidTransitionException("Custom exception message");
		assertEquals("Custom exception message", ie.getMessage());
	}

	/**
	 *  Test method for InvalidTransitionException method
	 */
	@Test
	void testInvalidTransitionException() {
		InvalidTransitionException ie = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ie.getMessage());
	}

}
