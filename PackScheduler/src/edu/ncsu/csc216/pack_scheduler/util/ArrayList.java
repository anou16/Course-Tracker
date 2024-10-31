package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Array list that doesn’t allow for null elements or duplicate elements as defined by the equals() method.
 * 
 * @author Samir Naseri 
 * @param <E> Generic Type Parameter 
 */
public class ArrayList<E> extends AbstractList<E> {

    /** A constant value for initializing the list size. The value should be 10. */
    private static final int INIT_SIZE = 10;
    
    /** An array of type E. */
    private E[] list;
    
    /** The size of the list. */
    private int size;

    /**
     * Constructor for ArrayList 
     * 
     * The constructor of ArrayList should create an empty ArrayList (e.g., size is zero) with a list of capacity INIT_SIZE. 
     * Remember, you cannot directly construct an array of type E. Instead you have to create an array of Objects and cast to an array of type E. 
     * You may add the annotation @SuppressWarnings("unchecked") above the constructor to remove the warning about an unchecked cast.
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        list = (E[]) new Object[INIT_SIZE];
        size = 0;
    }
    
    /**
     * The size of the list. 
     * 
     * @return size 
     */
    @Override
    public int size() {
        return size;
    }
    
    /**
     * Method that will add the element to the given index.
     * If the element is inserted at the front or the middle of the list, the other elements are shifted to make room. 
     * If the size is equal to the capacity, the list should automatically double in capacity to make room for the new element.
     * 
     * @param idx - index where we are adding to.
     * @param e - the element we are adding. 
     * @throws NullPointerException - If the element to add is null, the method should throw a NullPointerException.
     * @throws IllegalArgumentException - If the element to add is a duplicate of an element already in the list as determined by the equals() method, 
     *                                    the method should throw an IllegalArgumentException.
     * @throws IndexOutOfBoundsException - If the index is out of range .
     */
    @Override
    public void add(int idx, E e) {
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }
    	if (e == null) {
            throw new NullPointerException("Element is null.");
        }
        for (int i = 0; i < size; i++) {
            if (list[i].equals(e)) {
                throw new IllegalArgumentException("Duplicate element found.");
            }
        }
        if (size == list.length) {
            growArray();
        }
        for (int i = size; i > idx; i--) {
            list[i] = list[i - 1];
        }
        list[idx] = e;
        size++;
    }

    /**
     * If the size is equal to the capacity, the list should automatically double in capacity to make room for the new element. 
     * The private method growArray() is used to double the capacity.
     */
    @SuppressWarnings("unchecked")
    private void growArray() {
        E[] doubleArray = (E[]) new Object[list.length * 2];
        for (int i = 0; i < list.length; i++) {
            doubleArray[i] = list[i];
        }
        list = doubleArray;
    }

    /**
     * Returns the element at the index and the return type is E.
     * 
     * @param idx - Getting element at this index.
     * @return - The element we want. 
     * @throws IndexOutOfBoundsException - If the index is out of range. 
     */
    @Override
    public E get(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }
        return list[idx];
    }

    /**
     * Should return the element removed and the return type should be E.
     * If an element at the front or middle of the list is removed, the elements are left shifted to close the gap. 
     * Don’t forget to set the element at size-1 to null after the left shift to remove the extra reference.
     * 
     * @param idx - Index of the element we are trying to remove. 
     * @return - The element we removed. 
     * @throws IndexOutOfBoundsException - If the index is out of range. 
     */
    @Override
    public E remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }
        E remove = list[idx];
        for (int i = idx; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null; 
        size--;
        return remove;
    }
    
    /**
     * Should return the original element at the location and the return type should be E.
     * The element at the specified index is replaced with the given element.
     *
     * @param idx - Index of the element we are trying to replace. 
     * @param e - Element we are using to replace original element. 
     * @return - The original element. 
     * @throws NullPointerException - If the element to set is null.
     * @throws IllegalArgumentException - If the element to set is a duplicate of an element already in the list as determined by the equals() method.
     * @throws IndexOutOfBoundsException - If the index is out of range.
     */
    @Override
    public E set(int idx, E e) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }
        if (e == null) {
            throw new NullPointerException("Element is null.");
        }
        for (int i = 0; i < size; i++) {
            if (list[i].equals(e)) {
                throw new IllegalArgumentException("Duplicate element found.");
            }
        }
        E original = list[idx];
        list[idx] = e;
        return original;
    }
}
