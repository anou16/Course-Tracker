package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Queues elements utilizing an ArrayList.
 * 
 * @author Anoushka Piduru
 * @param <E> the generic type of the ArrayQueue.
 */
public class ArrayQueue<E> implements Queue<E> {
	/** The queue storing elements. */
	private ArrayList<E> queue;
	/** Maximum number of elements the queue can hold. */
	private int capacity;

	/**
	 * Constructor for the ArrayQueue.
	 * 
	 * @param capacity the maximum number of elements the queue can hold.
	 */
	public ArrayQueue(int capacity) {
		queue = new ArrayList<E>();
		setCapacity(capacity);
	}

	/**
	 * Adds an element to the back of the queue.
	 * 
	 * @param element the element to be added.
	 * @throws IllegalArgumentException if capacity is reached.
	 */
	public void enqueue(E element) {
		if (size() == capacity) {
			throw new IllegalArgumentException();
		}
		queue.add(queue.size(), element);
	}

	/**
	 * Removes an element from the front of the queue.
	 * 
	 * @return the element being removed.
	 */
	public E dequeue() {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		return queue.remove(0);
	}

	/**
	 * Determines whether the queue is empty.
	 * 
	 * @return true if empty, false if not.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Returns the size of the queue.
	 * 
	 * @return the size of the queue.
	 */
	public int size() {
		return queue.size();
	}

	/**
	 * Sets the maximum holding value of the queue.
	 * 
	 * @param capacity the maximum number of elements that can be held in the queue.
	 * @throws IllegalArgumentException if the capacity is invalid.
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
}
