package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;

/**
 * Class to test ArrayList.java
 * 
 * @author Samir Naseri
 */
class ArrayListTest {

	/**
	 * Tests the Constructor from the ArrayList class. 
	 */
    @Test
    void testArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        assertEquals(0, arrayList.size(), "Initial size is 0.");
    }

    /**
     * Tests the add method from the ArrayList class. 
     */
    @Test
    void testAdd() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(0, "N");
        assertEquals(1, arrayList.size(), "Size should equal 1.");
    }

    /**
     * Tests the get method from the ArrayList class. 
     */
    @Test
    void testGet() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(0, "N");
        arrayList.add(1, "O");
        assertEquals("N", arrayList.get(0), "Element = N.");
        assertEquals("O", arrayList.get(1), "Element = O.");
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(-5), "Index < 0 = Out of range.");
    }
    
    /**
     * Tests the remove method from the ArrayList class. 
     */
    @Test
    void testRemove() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(0, "N");
        arrayList.add(1, "O");
        arrayList.remove(0);
        assertEquals(1, arrayList.size(), "Size = 1?");
        assertEquals("O", arrayList.get(0), "Element = O?");
    }
    
    /**
     * Tests the set method from the ArrayList class. 
     */
    @Test
    void testSet() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(0, "N");
        arrayList.add(1, "O");
        assertEquals("N", arrayList.set(0, "!"), "Original = N.");
        assertEquals("!", arrayList.get(0), "New = !.");
    }
    
    /**
     * Tests the invalid inputs for set()
     */
    @Test
    void testSetInvalid() {
    	ArrayList<String> arrayList = new ArrayList<>();
    	 arrayList.add(0, "N");
         arrayList.add(1, "O");
       
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(-1, "N"));
        assertThrows(NullPointerException.class, () -> arrayList.set(1, null));
    }
    
    /**
     * Tests the invalid inputs for remove()
     */
    @Test
    void testRemoveInvalid() {
    	ArrayList<String> arrayList = new ArrayList<>();
       
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(-1));
    }
    
    /**
     * Tests the invalid inputs for add()
     */
    @Test
    void testAddInvalid() {
    	ArrayList<String> arrayList = new ArrayList<>();
    	arrayList.add(0, "N");
        arrayList.add(1, "O");
       
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(-1, "N"));
        assertThrows(NullPointerException.class, () -> arrayList.add(1, null));
    }
    /**
     * Test Add Remove
     */
    @Test
    void testAddRemoveAddInteraction() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(0, "A");
        arrayList.add(1, "B");
        arrayList.remove(0);
        arrayList.add(0, "C");
        assertEquals(2, arrayList.size(), "Size should be 2 after adding, removing, and adding again.");
        assertEquals("C", arrayList.get(0), "First element should be C.");
        assertEquals("B", arrayList.get(1), "Second element should be B.");
    }
    /**
     * Test set
     */
    @Test
    void testSetAfterGrowth() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            arrayList.add(i, "Element-" + i);
        }
        assertEquals(15, arrayList.size(), "Size should be 15 after adding elements beyond the initial capacity.");

        arrayList.set(14, "Updated-14");
        assertEquals("Updated-14", arrayList.get(14), "Last element should be updated.");
    }
}
