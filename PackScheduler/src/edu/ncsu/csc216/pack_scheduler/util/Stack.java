package edu.ncsu.csc216.pack_scheduler.util;

/**
 * The Stack interface has a generic type and five methods to be implemented by
 * ArrayStack and LinkedStack.
 * 
 * @param <E> the generic type for the stack.
 * @author Anoushka Piduru
 */
public interface Stack<E> {
	/**
	 * Adds element to the top of the stack.
	 * 
	 * @param element the element being added.
	 * @throws IllegalArgumentException if capacity has been reached.
	 */
	void push(E element);

	/**
	 * Removes and returns the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack.
	 */
	E pop();

	/**
	 * Returns true if the stack is empty.
	 * 
	 * @return true if the stack is empty, false if not.
	 */
	boolean isEmpty();

	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return the number of elements in the stack.
	 */
	int size();

	/**
	 * Sets the stack's capacity.
	 * 
	 * @param capacity the capacity of the stack.
	 * @throws IllegalArgumentException if the capacity is invalid.
	 */
	void setCapacity(int capacity);
}
