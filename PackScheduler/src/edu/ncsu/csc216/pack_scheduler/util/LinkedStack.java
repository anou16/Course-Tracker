package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * LinkedStack implements Stack methods for linked list functionality.
 * 
 * @param <E> the generic type for linked list.
 * @author Anoushka Piduru
 */
public class LinkedStack<E> implements Stack<E> {
	/** A stack of elements. */
	private LinkedAbstractList<E> stack;

	/**
	 * Constructor for ArrayStack.
	 * 
	 * @param capacity the capacity of the stack.
	 */
	public LinkedStack(int capacity) {
		stack = new LinkedAbstractList<E>(capacity);
	}

	/**
	 * Adds element to the top of the stack.
	 * 
	 * @param element the element being added.
	 * @throws IllegalArgumentException if capacity has been reached.
	 */
	public void push(E element) {
		if (size() == stack.capacity) {
			throw new IllegalArgumentException("Stack has reached capacity.");
		}
		stack.add(element);
	}

	/**
	 * Removes and returns the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public E pop() {
		if (size() == 0) {
			throw new EmptyStackException();
		}
		return stack.remove(stack.size() - 1);
	}

	/**
	 * Returns true if the stack is empty.
	 * 
	 * @return true if the stack is empty, false if not.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return the number of elements in the stack.
	 */
	public int size() {
		return stack.size();
	}

	/**
	 * Sets the stack's capacity.
	 * 
	 * @param capacity the capacity of the stack.
	 * @throws IllegalArgumentException if the capacity is invalid.
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		stack.setCapacity(capacity);
	}
}
