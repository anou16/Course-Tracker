package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Custom Linked Abstract List to fulfill requirements.
 * 
 * @author Neha Pothireddy
 * @author Micheal
 * @param <E> Generic Type Parameter
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** Front node of the list */
	private ListNode front;
	/** Size of the list */
	private int size;
	/** Capacity of the list */
	int capacity;

	/**
	 * Constructs a LinkedAbstractList with a specified capacity.
	 * 
	 * @param capacity the maximum number of elements the list can hold
	 * @throws IllegalArgumentException if the capacity is less than 0
	 * @throws IllegalArgumentException if the capacity is less than the size
	 */
	public LinkedAbstractList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity cannot be negative.");
		}
		this.front = null;
		this.size = 0;
		this.capacity = capacity;
	}

	/**
	 * Sets the capacity of the list.
	 * 
	 * @param capacity the new capacity to set
	 * @throws IllegalArgumentException if the new capacity is less than the current
	 *                                  size
	 */
	public void setCapacity(int capacity) {
		if (capacity < size) {
			throw new IllegalArgumentException("Capacity cannot be less than the current list size.");
		}
		this.capacity = capacity;
	}

	/**
	 * Removes the element from that index and returns the data
	 * 
	 * @return E the data at the index
	 * @param index the index to remove from
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("The index is out of bounds.");
		}

		E value = null;
		if (index == 0) { // Special Case: front of list
			// sets the data
			value = front.data;
			// the node in the list
			front = front.next;

		} else {
			// removing from elsewhere in the list
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			value = current.next.data;
			current.next = current.next.next;
		}

		size--;
		return value;

	}

	/**
	 * Adds an element into the linked list
	 * 
	 * @param index   the index to add into
	 * @param element the element to add
	 * @throws NullPointerException      if the element is null
	 * @throws IllegalArgumentException  if there is a duplicate element
	 * @throws IllegalArgumentException  size is greater than the capacity
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		if (contains(element)) {
			throw new IllegalArgumentException("Duplicate elements are not allowed.");
		}
		if (size >= capacity) {
			throw new IllegalArgumentException("List has reached its capacity.");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}

		if (front == null) {
			front = new ListNode(element);
		}

		if (index == 0) {
			front = new ListNode(element, front);
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		size++;
	}

	/**
	 * Returns the data at the index
	 * 
	 * @return E the data at the index
	 * @param index the index to get from
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("The index is out of bounds.");
		}
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		return current.data;
	}

	/**
	 * Sets an element in the linked list
	 * 
	 * @param index   the index to add into
	 * @param element the element to add
	 * @throws NullPointerException      if the element is null
	 * @throws IllegalArgumentException  if there is a duplicate element
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		if (contains(element)) {
			throw new IllegalArgumentException("Duplicate elements are not allowed.");
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}

		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		E oldData = current.data;
		current.data = element;
		return oldData;
	}

	/**
	 * The size of the list.
	 * 
	 * @return size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Inner Class that assigns the node in the list
	 * 
	 * @author Neha Pothireddy
	 */
	private class ListNode {
		/** Data in the node */
		E data;
		/** Next node in the list */
		ListNode next;

		/**
		 * Constructs a ListNode with the specified data.
		 * 
		 * @param data the data for the node
		 */
		ListNode(E data) {
			this(data, null);
		}

		/**
		 * Constructs a ListNode with the specified data and next node.
		 * 
		 * @param data the data for the node
		 * @param next the next node
		 */
		ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
