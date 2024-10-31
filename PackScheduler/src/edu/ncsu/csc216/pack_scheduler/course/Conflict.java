/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 *  Interface for checking conflicting activities 
 */
public interface Conflict {
	
	/**
	 * Checks it activity or events conflicts 
	 * @param possibleConflictingActivity other possible conflicting event/course
	 * @throws ConflictException if there is conflict
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException; 
}
