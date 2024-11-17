package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedList class.
 * 
 * @author Anoushka Piduru
 */
class LinkedListTest {

	/**
	 * Tests the Constructor from the linkedList class.
	 */
	@Test
	void testLinkedList() {
		LinkedList<String> linkedList = new LinkedList<>();
		assertEquals(0, linkedList.size(), "Initial size is 0.");
	}

	/**
	 * Tests the add method from the linkedList class.
	 */
	@Test
	void testAdd() {
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add(0, "N");
		assertEquals(1, linkedList.size(), "Size should equal 1.");
	}

	/**
	 * Tests the get method from the linkedList class.
	 */
	@Test
	void testGet() {
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add(0, "N");
		linkedList.add(1, "O");
		assertEquals("N", linkedList.get(0), "Element = N.");
		assertEquals("O", linkedList.get(1), "Element = O.");
		assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(-5), "Index < 0 = Out of range.");
	}

	/**
	 * Tests the remove method from the linkedList class.
	 */
	@Test
	void testRemove() {
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add(0, "N");
		linkedList.add(1, "O");
		linkedList.remove(0);
		assertEquals(1, linkedList.size(), "Size = 1?");
		assertEquals("O", linkedList.get(0), "Element = O?");
	}

	/**
	 * Tests the set method from the linkedList class.
	 */
	@Test
	void testSet() {
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add(0, "N");
		linkedList.add(1, "O");
		assertEquals("N", linkedList.set(0, "!"), "Original = N.");
		assertEquals("!", linkedList.get(0), "New = !.");
	}

	/**
	 * Tests the invalid inputs for set()
	 */
	@Test
	void testSetInvalid() {
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add(0, "N");
		linkedList.add(1, "O");

		assertThrows(IndexOutOfBoundsException.class, () -> linkedList.set(-1, "G"));
		assertThrows(IllegalArgumentException.class, () -> linkedList.set(0, "N"));
		assertThrows(NullPointerException.class, () -> linkedList.set(1, null));
	}

	/**
	 * Tests the invalid inputs for remove()
	 */
	@Test
	void testRemoveInvalid() {
		LinkedList<String> linkedList = new LinkedList<>();

		assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(-1));
	}

	/**
	 * Tests the invalid inputs for add()
	 */
	@Test
	void testAddInvalid() {
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add(0, "N");
		linkedList.add(1, "O");

		assertThrows(IndexOutOfBoundsException.class, () -> linkedList.add(-1, "G"));
		assertThrows(IllegalArgumentException.class, () -> linkedList.add(0, "N"));
		assertThrows(NullPointerException.class, () -> linkedList.add(1, null));
	}

	/**
	 * Test Add Remove
	 */
	@Test
	void testAddRemoveAddInteraction() {
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add(0, "A");
		linkedList.add(1, "B");
		linkedList.remove(0);
		linkedList.add(0, "C");
		assertEquals(2, linkedList.size(), "Size should be 2 after adding, removing, and adding again.");
		assertEquals("C", linkedList.get(0), "First element should be C.");
		assertEquals("B", linkedList.get(1), "Second element should be B.");
	}

	/**
	 * Test set
	 */
	@Test
	void testSetAfterGrowth() {
		LinkedList<String> linkedList = new LinkedList<>();
		for (int i = 0; i < 15; i++) {
			linkedList.add(i, "Element-" + i);
		}
		assertEquals(15, linkedList.size(), "Size should be 15 after adding elements beyond the initial capacity.");

		linkedList.set(14, "Updated-14");
		assertEquals("Updated-14", linkedList.get(14), "Last element should be updated.");
	}

}
