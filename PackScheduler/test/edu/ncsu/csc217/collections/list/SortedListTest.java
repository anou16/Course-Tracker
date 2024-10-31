package edu.ncsu.csc217.collections.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import edu.ncsu.csc217.collections.list.SortedList;

import org.junit.Test;


/**
 * Test for Sorted List 
 * 
 * @author Hinano Turner
 * @author Chris Gilbert
 * @author Samir Naseri
 */
public class SortedListTest {

	/**
	 * Test for sorted list 
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		// Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		
		list.add("one");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		list.add("11");
		assertEquals(11, list.size());
	}

	/**
	 * Test for adding elements to list 
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		// Test adding to the front
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		// Test adding to the middle 
		list.add("for");
		assertEquals(2, list.size());
		assertEquals("banana", list.get(0));
		assertEquals("for", list.get(1));

		
		// Test adding the the end 
		list.add("everyone");
		assertEquals("banana", list.get(0));
		assertEquals("everyone", list.get(1));
		assertEquals("for", list.get(2));

		
		// Test adding a null element
		assertThrows(NullPointerException.class, 
				() -> list.add(null)); 
		
	
		
		// Test adding a duplicate element
		
		assertThrows(IllegalArgumentException.class, () -> list.add("banana"), "Element already in list");
		assertEquals(list.size(), 3);
		assertEquals("banana", list.get(0));
		assertEquals("everyone", list.get(1));
		assertEquals("for", list.get(2));

		
	}
	
	/**
	 * Test to find element in array
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		//Test getting an element from an empty list
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(0));

		
		//Add some elements to the list
		list.add("apple");
		list.add("banana");
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));

	
		
		//Test getting an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(-1));
		
		
		//Test getting an element at size
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(2));
		
	}
	
	/**
	 * Test to remove elements from array 
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		// Test removing from an empty list
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(0));
		
		// Add some elements to the list - at least 4
		list.add("element");
		list.add("door");
		list.add("cat");
		list.add("for");
		list.add("dog");

		
		//Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-1));
		
		
		//Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(list.size()));
		
		//Test removing a middle element
		list.remove(2);
		assertEquals(4, list.size());
		assertEquals("for", list.get(3));
		assertEquals("element", list.get(2));
		//["cat", "dog", "door", "element", "for"]
		
		// Test removing the last element
		list.remove(list.size() - 1);
		assertEquals(3, list.size());
		assertEquals("element", list.get(2));
		assertEquals("dog", list.get(1));
		
		//Test removing the first element
		list.remove(0);
		assertEquals(2, list.size());
		assertEquals("dog", list.get(0));
		assertEquals("element", list.get(1));

		
		
		//Test removing the last element
		list.remove(1);
		list.remove(0);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Test to find index of array 
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("banana"), "empty list");

		
		//Add some elements
		list.add("banana");
		list.add("carrot");
		list.add("apple");
		
		//Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("carrot"));
		assertEquals(-1, list.indexOf("potato"));


		
		//Test checking the index of null
		assertThrows(NullPointerException.class, () -> list.indexOf(null));
		

		
	}
	
	/**
	 * Test to clear array 
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("everyone");
		list.add("loves");
		list.add("banana's");
		
		// Clear the list
		list.clear();
		
		// Test that the list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());

	}

	/**
	 * Test to check for empty array 
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		// Test that the list starts empty
		assertTrue(list.isEmpty());
		
		// Add at least one element
		list.add("one");
		list.add("two");
		
		// Check that the list is no longer empty
		assertEquals(2, list.size());
		assertFalse(list.isEmpty());
	}

	/**
	 * Test to check what array contains 
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		// Test the empty list case
		assertFalse(list.contains("jazz"));
		
		// Add some elements
		list.add("rock");
		list.add("rnb");
		list.add("pop");
		
		// Test some true and false cases
		assertTrue(list.contains("rock"));
		assertTrue(list.contains("pop"));
		assertFalse(list.contains("country"));
		assertFalse(list.contains("kpop"));


	}
	
	/**
	 * Test to check array equivalence  
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("the");
		list1.add("only");
		list1.add("way"); 

		list2.add("the");
		list2.add("only");
		list2.add("way");
		
		list3.add("go");
		list3.add("pack");
		list3.add("1");
		
		// Test for equality and non-equality
		
		assertEquals(list2, list1);
		assertEquals(list1, list2);
		assertNotEquals(list3, list2);
		assertNotEquals(list1, list3);
	}
	
	/**
	 * Test hashCode equivalence 
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make two lists the same and one list different
		list1.add("the");
		list1.add("only");
		list1.add("way");

		list2.add("the");
		list2.add("only");
		list2.add("way");
		
		list3.add("go");
		list3.add("pack");
		list3.add("1");
		
		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list2.hashCode(), list1.hashCode());
		assertNotEquals(list3.hashCode(), list2.hashCode());

		
	}

}
 