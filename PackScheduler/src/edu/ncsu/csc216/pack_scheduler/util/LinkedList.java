package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * The LinkedList class extends AbstractSequentialList and provides linked list
 * functionality.
 * 
 * @param <E> the generic type of the linked list.
 * @author Anoushka Piduru
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** The first node of the list. */
	private ListNode front;
	/** The last node of the list. */
	private ListNode back;
	/** The size of the list. */
	private int size;

	/**
	 * Constructor for an empty LinkedList object.
	 */
	public LinkedList() {
		this.front = new ListNode(null);
		this.back = new ListNode(null);
		this.front.next = back;
		this.back.prev = front;
		this.size = 0;
	}

	/**
	 * Returns the size of the linked list.
	 * 
	 * @return the number of elements in the list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds an element to the list, not allowing duplicate elements.
	 * 
	 * @param index   the index the element will be added to.
	 * @param element the element to be added at the specified index.
	 * @throws IllegalArgumentException if the element already exists in the list.
	 */
	@Override
	public void add(int index, E element) {
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}
		super.add(index, element);
	}

	/**
	 * Sets the element at the specified index to a specific element, not allowing
	 * duplicate elements.
	 * 
	 * @param index   the index to be set at.
	 * @param element the element to be set at the specified index.
	 * @throws IllegalArgumentException if the element already exists in the list.
	 * @return the original element at the index.
	 */
	@Override
	public E set(int index, E element) {
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
	}

	/**
	 * Returns a ListIterator with positioning such that a call to
	 * ListIterator.next() returns the element at the parameter index.
	 * 
	 * @param index the index of the element returned after the first call to next.
	 * @return the ListIterator starting at index.
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}

	/**
	 * The ListNode class is an inner class handling nodes in the linked list.
	 */
	private class ListNode {
		/** Data stored in the node. */
		public E data;
		/** The next list node in the list. */
		public ListNode next;
		/** The previous list node in the list. */
		public ListNode prev;

		/**
		 * First constructor for ListNode taking a data parameter only.
		 * 
		 * @param data the data to be stored in the node.
		 */
		public ListNode(E data) {
			this.data = data;
		}

		/**
		 * Second constructor for a ListNode, with parameters data, previous, and next.
		 * 
		 * @param data the data to be stored in the node.
		 * @param prev the previous listNode in the linked list.
		 * @param next the next list node in the list.
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	/**
	 * LinkedListIterator is an inner class that provides a concrete implementation
	 * of AbstractSequentialList.
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** The previous list node in list. */
		public ListNode previous;
		/** The next list node in list. */
		public ListNode next;
		/** The index of the previous node. */
		public int previousIndex;
		/** The index of the next node. */
		public int nextIndex;
		/** The ListNode that was returned on the last called to previous or next. */
		public ListNode lastRetrieved;

		/**
		 * Constructor for LinkedListIterator.
		 * 
		 * @param index the starting point of the iterator.
		 * @throws IndexOutOfBoundsException if the index is invalid.
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size()) {
				throw new IndexOutOfBoundsException();
			}

			this.previous = front;
			this.next = front.next;
			this.previousIndex = -1;
			this.nextIndex = 0;

			for (int i = 0; i < index; i++) {
				this.previous = this.next;
				this.next = this.previous.next;
				this.previousIndex++;
				this.nextIndex++;
			}
			this.lastRetrieved = null;
		}

		/**
		 * Returns true if the list iterator finds a next node in the list.
		 * 
		 * @return true if there is a next node, false if not.
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * Returns the next element in the list.
		 * 
		 * @return the next element in the list.
		 * @throws NoSuchElementException if there is no next node.
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E data = next.data;
			lastRetrieved = next;

			previous = next;
			next = next.next;

			nextIndex++;
			previousIndex++;

			return data;
		}

		/**
		 * Returns true if the list iterator finds a previous node in the list.
		 * 
		 * @return true if there is a previous node, false if not.
		 */
		@Override
		public boolean hasPrevious() {
			return previous.data != null;
		}

		/**
		 * Returns the previous element in the list.
		 * 
		 * @return the previous element in the list.
		 * @throws NoSuchElementException if there is no previous node.
		 */
		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			E data = previous.data;
			lastRetrieved = previous;

			next = previous;
			previous = previous.prev;

			nextIndex--;
			previousIndex--;

			return data;
		}

		/**
		 * Returns the index of the next element.
		 * 
		 * @return the index of the element after a call to next().
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Returns the index of the previous element.
		 * 
		 * @return the index of the element after a call to prev().
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * Removes the most recent element that was returned by next() or previous().
		 * 
		 * @throws IllegalArgumentException if there was no recently retrieved element.
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			lastRetrieved.prev.next = lastRetrieved.next;
			lastRetrieved.next.prev = lastRetrieved.prev;

			if (lastRetrieved.equals(previous)) {
				previous = lastRetrieved.prev;
			} else {
				next = lastRetrieved.next;
			}
			size--;
			lastRetrieved = null;
		}

		/**
		 * Replaces the most recent element returned by next() or prev() with a
		 * specified element.
		 * 
		 * @param element the element to be set.
		 * @throws IllegalStateException if there was no last retrieved element.
		 * @throws NullPointerException  if element is null.
		 */
		@Override
		public void set(E element) {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if (element == null) {
				throw new NullPointerException();
			}
			lastRetrieved.data = element;
		}

		/**
		 * Adds a specific element to the list.
		 * 
		 * @param element the element to be added.
		 * @throws NullPointerException if the element is null.
		 */
		@Override
		public void add(E element) {
			if (element == null) {
				throw new NullPointerException();
			}
			ListNode newNode = new ListNode(element, previous, next);

			next.prev = newNode;
			previous.next = newNode;

			previous = newNode;

			previousIndex++;
			nextIndex++;
			size++;
			lastRetrieved = null;
		}
	}
}
