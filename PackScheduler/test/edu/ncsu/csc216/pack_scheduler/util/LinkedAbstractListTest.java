package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests the LinkedAbstractList
 * 
 * @author Neha Pothireddy 
 */
class LinkedAbstractListTest {
	
	/** Linked list instance used for testing */
    private LinkedAbstractList<String> lList;
     

    /**
     * Sets up the test by initializing the list with a capacity of 4.
     */
    @BeforeEach
    void setUp() {
        lList = new LinkedAbstractList<>(4);
    }
    
    /**
     * Tests the constructor.
     */
    @Test
    void testLinkedList() {
        assertEquals(0, lList.size(), "Initial size should be 0.");
        assertThrows(IllegalArgumentException.class, () -> new LinkedAbstractList<String>(-1), "Constructor should throw exception for negative capacity.");
    }

    /**
     * Tests the add method with valid inputs.
     */
    @Test
    void testAddLinkedList() {
        lList.add(0, "N");
        assertEquals(1, lList.size(), "Size should be 1 after adding one element.");
        lList.add(1, "O");
        assertEquals(2, lList.size(), "Size should be 2 after adding another element.");
        assertEquals("N", lList.get(0), "Element at index 0 should be 'N'.");
        assertEquals("O", lList.get(1), "Element at index 1 should be 'O'.");
    }
    
    /**
     * Tests the get method from the LinkedAbstractList class. 
     */
    @Test
    void testGetLinkedList() {
    	
        lList.add(0, "N");
        lList.add(1, "O");
        assertEquals("N", lList.get(0), "Element = N.");
        assertEquals("O", lList.get(1), "Element = O.");
        assertThrows(IndexOutOfBoundsException.class, () -> lList.get(-5), "Index < 0 = Out of range.");
        assertThrows(IndexOutOfBoundsException.class, () -> lList.get(-1), "Should throw IndexOutOfBoundsException for index < 0.");
    }
    
    /**
     * Tests the remove method from the LinkedAbstractList class. 
     */
    @Test
    void testRemoveLinkedList() {
    	
        lList.add(0, "N");
        lList.add(1, "O");
        lList.remove(0);
        assertEquals(1, lList.size(), "Size = 1?");
        assertEquals("O", lList.get(0), "Element = O?");
    }
    
    /**
     * Tests the set method from the LinkedAbstractList class. 
     */
    @Test
    void testSetLinkedList() {
    
        lList.add(0, "N");
        lList.add(1, "O");
        assertEquals("N", lList.set(0, "!"), "Original = N.");
        assertEquals("!", lList.get(0), "New = !.");
    }
    
    /**
     * Tests the invalid inputs for set()
     */
    @Test
    void testSetInvalidLinkedList() {
    	
    	lList.add(0, "N");
        lList.add(1, "O");
       
        assertThrows(IndexOutOfBoundsException.class, () -> lList.set(-1, "P"));
        assertThrows(NullPointerException.class, () -> lList.set(1, null));
    }
    
    /**
     * Tests the remove method with invalid inputs.
     */
    @Test
    void testRemoveInvalidLinkedList() {
        assertThrows(IndexOutOfBoundsException.class, () -> lList.remove(0), "Should throw IndexOutOfBoundsException for removing from empty list.");
        lList.add(0, "N");
        assertThrows(IndexOutOfBoundsException.class, () -> lList.remove(-1), "Should throw IndexOutOfBoundsException for negative index.");
        assertThrows(IndexOutOfBoundsException.class, () -> lList.remove(1), "Should throw IndexOutOfBoundsException for index >= size.");
    }
    
    /**
     * Tests the invalid inputs for add()
     */
    @Test
    void testAddInvalidLinkedList() {
    	
    	lList.add(0, "N");
        lList.add(1, "O");
       
        assertThrows(IndexOutOfBoundsException.class, () -> lList.add(-1, "P"));
        assertThrows(NullPointerException.class, () -> lList.add(1, null));
    }
    /**
     * Tests the size method.
     */
    @Test
    void testSize() {
        assertEquals(0, lList.size(), "Initial size should be 0.");
        lList.add(0, "N");
        assertEquals(1, lList.size(), "Size should be 1 after adding one element.");
        lList.add(1, "O");
        assertEquals(2, lList.size(), "Size should be 2 after adding another element.");
        lList.remove(0);
        assertEquals(1, lList.size(), "Size should be 1 after removing one element.");
    }
    /**
     *  Tests for add and remove
     */
    @Test
    void testAddRemoveAtBoundary() {
        lList.add(0, "X");
        lList.add(1, "Y");
        lList.add(2, "Z");
        lList.remove(2);  // Removing at last index
        assertEquals(2, lList.size(), "Size should be 2 after removing element at the end.");
        assertEquals("Y", lList.get(1), "Last element should be Y after removal.");
    }
    /**
     * Test for Edge Cases in get and set
     */
    @Test
    void testGetSetBoundaryElements() {
        lList.add(0, "First");
        lList.add(1, "Middle");
        lList.add(2, "Last");
        assertEquals("First", lList.get(0), "First element should be 'First'.");
        assertEquals("Last", lList.get(2), "Last element should be 'Last'.");
        
        lList.set(0, "NewFirst");
        lList.set(2, "NewLast");
        assertEquals("NewFirst", lList.get(0), "Updated first element should be 'NewFirst'.");
        assertEquals("NewLast", lList.get(2), "Updated last element should be 'NewLast'.");
    }

    /**
     * Test LinkedNode constructor 
     */
    @Test
    void testLinkedNodeCon() {
    	lList.add(0, "First");
    	
    	assertEquals(1, lList.size());
    	
    	lList.add(1, "Second");
        assertEquals(2, lList.size());
        
        lList.remove(0);
        assertEquals(1, lList.size());
    	
    }
    
    
    
}
