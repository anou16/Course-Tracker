package edu.ncsu.csc216.pack_scheduler.util;

/**
 * ArrayStack implements Stack methods to fit Array functionality.
 * 
 * @param <E> the generic type for ArrayStack.
 * @author Anoushka Piduru
 */
public class ArrayStack<E> implements Stack<E> {
	/**
	 * Constructor for ArrayStack.
	 * 
	 * @param capacity the capacity of the stack.
	 */
	public ArrayStack(int capacity) {
		// Implement
	}

	/**
	 * Adds element to the top of the stack.
	 * 
	 * @param element the element being added.
	 * @throws IllegalArgumentException if capacity has been reached.
	 */
	public void push(E element) {
		// Implement
	}

	/**
	 * Removes and returns the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public E pop() {
		return null;
	}

	/**
	 * Returns true if the stack is empty.
	 * 
	 * @return true if the stack is empty, false if not.
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return the number of elements in the stack.
	 */
	public int size() {
		return 0;
	}

	/**
	 * Sets the stack's capacity.
	 * 
	 * @param capacity the capacity of the stack.
	 * @throws IllegalArgumentException if the capacity is invalid.
	 */
	public void setCapacity(int capacity) {
		// Implement
	}
}
