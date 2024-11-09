package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface for a new Queue, with type E elements.
 * 
 * @author Anoushka Piduru
 * @param <E> the generic type of the Queue.
 */
public interface Queue<E> {
	/**
	 * Adds an element to the back of the queue.
	 * 
	 * @param element the element being added.
	 * @throws IllegalArgumentException if the capacity is equal to size.
	 */
	void enqueue(E element);

	/**
	 * Removes an element from the front of the queue and returns its value.
	 * 
	 * @return the element being removed.
	 */
	E dequeue();

	/**
	 * Checks if the queue is empty.
	 * 
	 * @return true if empty, false if not.
	 */
	boolean isEmpty();

	/**
	 * Returns how many elements are in the queue.
	 * 
	 * @return the number of elements in the queue.
	 */
	int size();

	/**
	 * Sets the queue's capacity.
	 * 
	 * @param capacity the max size the queue can reach.
	 */
	void setCapacity(int capacity);
}
