package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Class that extends the Exception class, creates a exception message if a conflict is found in creating a course
 * 
 * @author Neha Pothireddy
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that creates a custom message if there is a conflict
	 * @param message message for when a conflict is thrown
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}

	/**
	 * Constructor that creates a standard message if there is a conflict
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}

	
}
