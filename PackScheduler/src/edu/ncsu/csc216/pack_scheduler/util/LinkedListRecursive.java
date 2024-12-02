package edu.ncsu.csc216.pack_scheduler.util;

/**
 * LinkedListRecursive uses recursion to iterate through LinkedList methods.
 * 
 * @author Anoushka Piduru
 * @param <E> The generic type of the list.
 */
public class LinkedListRecursive<E> {
	/** The ListNode at the front of the LinkedList. */
	private ListNode front;
	/** The size of the list. */
	public int size;

	/**
	 * Constructor for LinkedListRecursive.
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}

	/**
	 * Returns the size of the list.
	 * 
	 * @return the size of the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Determines if the list has no elements.
	 * 
	 * @return true if the list is empty, false if not.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Determines if the list contains a given element.
	 * 
	 * @param element the element to check the list for.
	 * @return true if the list contains the element, false if not.
	 */
	public boolean contains(E element) {
		if (isEmpty()) {
			return false;
		}
		return front.contains(element);
	}

	/**
	 * Base case for adding element to list.
	 * 
	 * @param element the element to be added.
	 * @return true if element is added, false if not.
	 * @throws NullPointerException     if the element is null.
	 * @throws IllegalArgumentException if list already contains element.
	 */
	public boolean add(E element) {
		if (isEmpty()) {
			front = new ListNode(element, null);
			size++;
			return true;
		}
		if (element == null) {
			throw new NullPointerException("Null element");
		}
		if (contains(element)) {
			throw new IllegalArgumentException("Element already exists");

		}
		return front.add(element);
	}

	/**
	 * Adds a given element to the list at a given index.
	 * 
	 * @param idx     the index to be added at.
	 * @param element the element to be added.
	 * @return true if the element was added, false if not.
	 * @throws IndexOutOfBoundsException if the index is invalid.
	 * @throws NullPointerException      if the element is null.
	 */
	public boolean add(int idx, E element) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		if (element == null) {
			throw new NullPointerException("Null element");
		}
		if (contains(element)) {
			throw new IllegalArgumentException("Element already exists");
		}
		if (idx == 0) {
			ListNode newNode = new ListNode(element, front);
			front = newNode;
			size++;
			return true;
		}
		return front.add(idx, element);
	}

	/**
	 * Gets the element at the specified index.
	 * 
	 * @param idx the index to get.
	 * @return the value at the given index.
	 * @throws IndexOutOfBoundsException if the index is invalid.
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		if (idx == 0) {
			return front.data;
		}
		return front.next.get(idx - 1);
	}

	/**
	 * Removes element at the given index.
	 * 
	 * @param idx the index to be removed.
	 * @return the element which was removed.
	 * @throws IndexOutOfBoundsException if the index is invalid.
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		if (idx == 0) {
			E remove = front.data;
			front = front.next;
			size--;
			return remove;
		}
		return front.remove(idx);
	}

	/**
	 * Removes given element in list.
	 * 
	 * @param element the element to be removed.
	 * @return true if the element was removed, false if not.
	 */
	public boolean remove(E element) {
		if (isEmpty()) {
			return false;
		}
		if (front.data == element) {
			front = front.next;
			size--;
			return true;
		}
		return front.remove(element);
	}

	/**
	 * Sets element at the given index.
	 * 
	 * @param idx     the index to be set at.
	 * @param element the element to be set.
	 * @return the value of the original element.
	 * @throws IndexOutOfBoundsException if index is invalid.
	 * @throws NullPointerException      if the element is null.
	 * @throws IllegalArgumentException  if element is already in the list.
	 */
	public E set(int idx, E element) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		if (element == null) {
			throw new NullPointerException("Null element");
		}
		if (contains(element)) {
			throw new IllegalArgumentException("Element already exists");
		}
		if (idx == 0) {
			E set = front.data;
			front.data = element;
			return set;
		}
		return front.set(idx, element);
	}

	/**
	 * ListNode represents a node that holds data as well as the next node in the
	 * list.
	 */
	private class ListNode {
		/** The data contained by the node. */
		public E data;
		/** The next node in the list. */
		public ListNode next;

		/**
		 * Constructor for ListNode.
		 * 
		 * @param data the data held by the node.
		 * @param next the next node in the list.
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}

		/**
		 * Determines if the given element is represented by the current node.
		 * 
		 * @param element the element to be checked for.
		 * @return true if the next node contains the element, false if not.
		 */
		public boolean contains(E element) {
			if (data == element) {
				return true;
			}
			if (next == null) {
				return false;
			}
			return next.contains(element);
		}

		/**
		 * Adds the element to the last node.
		 * 
		 * @param element the element to be added.
		 * @return true if the node was added, false if not.
		 */
		public boolean add(E element) {
			if (next == null) {
				next = new ListNode(element, null);
				size++;
				return true;
			}
			return next.add(element);
		}

		/**
		 * Checks if the node is the accurate node to add an element to.
		 * 
		 * @param idx     the distance between the index and where the node will be
		 *                added.
		 * @param element the element to be added.
		 * @return true if it is the node to be added to, false if not.
		 */
		public boolean add(int idx, E element) {
			if (idx == 1) {
				ListNode newNode = new ListNode(element, next);
				next = newNode;
				size++;
				return true;
			}
			return next.add(idx - 1, element);
		}

		/**
		 * Determines if the element gets this node.
		 * 
		 * @param idx the distance from the returned index.
		 * @return the data of the node if the index is 0, if not, check the next node.
		 */
		public E get(int idx) {
			if (idx == 0) {
				return data;
			}
			return next.get(idx - 1);
		}

		/**
		 * Determines if this is the node that element is removed at.
		 * 
		 * @param idx distance between the index and the node to be removed.
		 * @return the value to be removed if this is the correct node, if not, check
		 *         the next node.
		 */
		public E remove(int idx) {
			if (idx == 1) {
				E remove = next.data;
				next = next.next;
				size--;
				return remove;
			}
			return next.remove(idx - 1);
		}

		/**
		 * Removes next element if its data matches data of the given element.
		 * 
		 * @param element the element to check for.
		 * @return true if the next element matches, false if not. Checks the next node
		 *         otherwise.
		 */
		public boolean remove(E element) {
			if (next == null) {
				return false;
			}
			if (next.data == element) {
				next = next.next;
				size--;
				return true;
			}
			return next.remove(element);
		}

		/**
		 * Sets data of node to given element at the given index.
		 * 
		 * @param idx     distance from this recursion to desired node.
		 * @param element the element to replace the node data.
		 * @return data if this is the correct node, check next node otherwise.
		 */
		public E set(int idx, E element) {
			if (idx == 0) {
				E set = data;
				data = element;
				return set;
			}
			return next.set(idx - 1, element);
		}
	}
}
