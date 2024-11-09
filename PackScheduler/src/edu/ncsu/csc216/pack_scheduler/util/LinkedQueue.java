package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Uses a LinkedList for queue functionality.
 * 
 * @author Anoushka Piduru
 * @param <E> the generic type of the queue.
 */
public class LinkedQueue<E> implements Queue<E> {
	/** An instance of LinkedAbstractList for queue. */
	private LinkedAbstractList<E> queue;

	/**
	 * Constructor for the LinkedQueue.
	 * 
	 * @param capacity the maximum number of elements the queue can hold.
	 */
	public LinkedQueue(int capacity) {
		queue = new LinkedAbstractList<E>(capacity);
	}

	/**
	 * Adds an element to the back of the queue.
	 * 
	 * @param element the element to be added.
	 * @throws IllegalArgumentException if capacity is reached.
	 */
	public void enqueue(E element) {
		if (size() == queue.capacity) {
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
		queue.setCapacity(capacity);
	}

	/**
	 * Checks if the element is contained in the queue.
	 * 
	 * @param element the element being checked for.
	 * @return true if the element exists, false if not.
	 */
	public boolean contains(E element) {
		for (int i = 0; i < queue.size(); i++) {
			if (element.equals(queue.get(i))) {
				return true;
			}
		}
		return false;
	}
}
