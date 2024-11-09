package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedQueue class.
 * 
 * @author Anoushka Piduru
 */
class LinkedQueueTest {
	/** LinkedQueue for testing. */
	private LinkedQueue<String> queue;

	/**
	 * Creates a new LinkedQueue object with capacity of ten.
	 */
	@BeforeEach
	void setUp() {
		queue = new LinkedQueue<>(10);
	}

	/** Tests the enqueue and dequeue methods. */
	@Test
	void testEnqueueAndDequeue() {
		assertTrue(queue.isEmpty());
		assertEquals(0, queue.size());

		queue.enqueue("A");
		assertEquals(1, queue.size());
		queue.enqueue("B");
		assertEquals(2, queue.size());
		queue.enqueue("C");
		assertEquals(3, queue.size());
		queue.enqueue("D");
		assertEquals(4, queue.size());
		queue.enqueue("E");
		assertEquals(5, queue.size());

		assertEquals("A", queue.dequeue());
		assertEquals(4, queue.size());
		assertEquals("B", queue.dequeue());
		assertEquals(3, queue.size());
		assertEquals("C", queue.dequeue());
		assertEquals(2, queue.size());
		assertEquals("D", queue.dequeue());
		assertEquals(1, queue.size());
		assertEquals("E", queue.dequeue());
		assertEquals(0, queue.size());

		assertThrows(NoSuchElementException.class, () -> queue.dequeue());
	}

	@Test
	void testSetCapacity() {
		assertThrows(IllegalArgumentException.class, () -> queue.setCapacity(-5));

		queue.setCapacity(1);
		queue.enqueue("A");
		assertThrows(IllegalArgumentException.class, () -> queue.enqueue("B"));
	}
}
