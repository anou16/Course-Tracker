/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Extends Exception and provides specific information for conflicting schedule 
 */
public class ConflictException extends Exception {
	
	
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a new ConflictException with specific detail message.
	 * @param message for conflict 
	 */
	public ConflictException(String message) {
		super(message);
	}

	/**
	 * Constructs a new ConflictException with default detail message.
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}

}
