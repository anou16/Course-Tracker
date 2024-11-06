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
	private E[] stack;
	/** The size of the stack. */
	private int size;
	/** The capacity of the stack. */
	private int capacity;

	/**
	 * Constructor for ArrayStack.
	 * 
	 * @param capacity the capacity of the stack.
	 */
	public LinkedStack(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
		this.size = 0;

		stack = (E[]) new Object[capacity];
	}

	/**
	 * Adds element to the top of the stack.
	 * 
	 * @param element the element being added.
	 * @throws IllegalArgumentException if capacity has been reached.
	 */
	public void push(E element) {
		if (size == capacity) {
			throw new IllegalArgumentException("Stack has reached capacity.");
		}
		stack[size++] = element;
	}

	/**
	 * Removes and returns the element at the top of the stack.
	 * 
	 * @return the element at the top of the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		E pop = stack[--size];
		stack[size] = null;
		return pop;
	}

	/**
	 * Returns true if the stack is empty.
	 * 
	 * @return true if the stack is empty, false if not.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of elements in the stack.
	 * 
	 * @return the number of elements in the stack.
	 */
	public int size() {
		return size;
	}

	/**
	 * Sets the stack's capacity.
	 * 
	 * @param capacity the capacity of the stack.
	 * @throws IllegalArgumentException if the capacity is invalid.
	 */
	public void setCapacity(int capacity) {
		if (capacity <= 0 || capacity < size) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
	}
}
