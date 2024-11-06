package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedStack class.
 * 
 * @author Anoushka Piduru
 */
class LinkedStackTest {

	/** LinkedStack for testing. */
	private LinkedStack<String> stack;

	/**
	 * Creates a new LinkedStack object which capacity of five.
	 */
	@BeforeEach
	void setUp() {
		stack = new LinkedStack<>(5);
	}

	/**
	 * Tests the push method which adds an element to the top.
	 */
	@Test
	void testPush() {
		stack.push("A");
		stack.push("B");
		stack.push("C");

		assertEquals(3, stack.size());
		assertFalse(stack.isEmpty());
	}

	/**
	 * Tests the pop method which removes the top element.
	 */
	@Test
	void testPop() {
		stack.push("A");
		stack.push("B");
		stack.push("C");

		assertEquals("C", stack.pop());
		assertEquals(2, stack.size());

		assertEquals("B", stack.pop());
		assertEquals(1, stack.size());

		assertEquals("B", stack.pop());
		assertEquals(1, stack.size());

		stack.pop();
		assertThrows(EmptyStackException.class, () -> stack.pop());
	}

	/**
	 * Tests the isEmpty method.
	 */
	@Test
	void testIsEmpty() {
		assertTrue(stack.isEmpty());

		stack.push("A");
		assertFalse(stack.isEmpty());

		stack.pop();
		assertTrue(stack.isEmpty());
	}

	/**
	 * Tests the size method.
	 */
	@Test
	void testSize() {
		stack.push("A");
		stack.push("B");
		stack.push("C");

		assertEquals(3, stack.size());
		stack.pop();
		assertEquals(2, stack.size());
	}

	/**
	 * Tests the capacity method.
	 */
	@Test
	void testCapacity() {
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");

		assertEquals(5, stack.size());
		assertThrows(IllegalArgumentException.class, () -> stack.push("F"));

		stack.setCapacity(6);
		stack.push("F");
		assertEquals(6, stack.size());
	}

}
