package implementations;

import java.util.NoSuchElementException;
import utilities.*;

// A dynamic array-based implementation of the ListADT interface
@SuppressWarnings("serial")
public class MyArrayList<E> implements ListADT<E> {

    // Default capacity for the internal array
    private static final int DEFAULT_CAPACITY = 10;

    // Internal array to hold the elements
    private Object[] array;

    // Current number of elements in the list
    private int size;

    // Default constructor that initializes the array with the default capacity and sets the size to 0
    public MyArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Returns the current size of the list
    @Override
    public int size() {
        return size;
    }

    // Clears the list by resetting the size to 0
    @Override
    public void clear() {
        size = 0;
    }

    // Adds an element at the specified index, shifting subsequent elements to the right
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        // Ensure the element to add is not null
        if (toAdd == null) {
            throw new NullPointerException("Element to add cannot be null");
        }

        // Ensure the index is within the valid range
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Chosen index is out of range");
        }

        // Ensure sufficient capacity for the new element
        ensureCapacity(size + 1);

        // Shift elements to the right to make space for the new element
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }

        // Insert the new element at the specified index
        array[index] = toAdd;
        size++;
        return true;
    }

    // Adds an element to the end of the list
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        // Ensure the element to add is not null
        if (toAdd == null) {
            throw new NullPointerException("The element you want to add can't be null");
        }

        // Ensure sufficient capacity for the new element
        ensureCapacity(size + 1);

        // Add the element to the end of the list
        array[size] = toAdd;
        size++;
        return true;
    }

    // Adds all elements from the specified list to the end of this list
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        // Ensure the list to add is not null
        if (toAdd == null) {
            throw new NullPointerException("The list can't be null");
        }

        // Ensure sufficient capacity for the new elements
        ensureCapacity(size + toAdd.size());

        // Add each element from the specified list to the current list
        Iterator<? extends E> iterator = toAdd.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }

        return true;
    }

    // Returns the element at the specified index
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) throws IndexOutOfBoundsException {
        // Ensure the index is within the valid range
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Entered index out of range");
        }

        // Return the element at the specified index
        return (E) array[index];
    }

    // Removes the element at the specified index, shifting subsequent elements to the left
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) throws IndexOutOfBoundsException {
        // Ensure the index is within the valid range
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Entered index is out of range");
        }

        // Retrieve the element to be removed
        E removedElement = (E) array[index];

        // Shift elements to the left to fill the gap
        System.arraycopy(array, index + 1, array, index, size - index - 1);

        // Set the last element to null and decrement the size
        array[size - 1] = null;
        size--;

        return removedElement;
    }

    // Removes the first occurrence of the specified element from the list
    @Override
    public E remove(E toRemove) throws NullPointerException {
        // Ensure the element to remove is not null
        if (toRemove == null) {
            throw new NullPointerException("The element can't be null");
        }

        // Iterate through the list to find and remove the specified element
        for (int i = 0; i < size; i++) {
            if (toRemove.equals(array[i])) {
                return remove(i);
            }
        }

        // Return null if the element was not found
        return null;
    }

    // Replaces the element at the specified index with the specified element
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        // Ensure the element to change is not null
        if (toChange == null) {
            throw new NullPointerException("The element can't be null");
        }

        // Ensure the index is within the valid range
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        // Retrieve the previous element, replace it, and return the previous element
        E previousElement = get(index);
        array[index] = toChange;
        return previousElement;
    }

    // Checks if the list is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Checks if the list contains the specified element
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        // Ensure the element to find is not null
        if (toFind == null) {
            throw new NullPointerException("Element to find cannot be null");
        }

        // Iterate through the list to find the specified element
        for (int i = 0; i < size; i++) {
            if (toFind.equals(array[i])) {
                return true;
            }
        }

        // Return false if the element was not found
        return false;
    }

    // Copies the elements of the list to the specified array
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException();
        }

        // If the provided array is too small, create a new one of appropriate size
        if (toHold.length < size) {
            return (E[]) java.util.Arrays.copyOf(array, size, toHold.getClass());
        }

        // Copy elements from the list to the provided array
        System.arraycopy(array, 0, toHold, 0, size);

        // Set any remaining elements to null
        if (toHold.length > size) {
            toHold[size] = null;
        }

        return toHold;
    }

    // Copies the elements of the list to a new array
    @Override
    public Object[] toArray() {
        // Create a new array and copy elements from the list to it
        Object[] result = new Object[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }

    // Returns an iterator over the elements in the list
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            // Index of the current element in the iteration
            private int currentIndex = 0;

            // Checks if there is a next element in the iteration
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            // Returns the next element in the iteration
            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                // Throw an exception if there is no next element
                if (!hasNext()) {
                    throw new NoSuchElementException("No elements in the list");
                }

                // Retrieve the next element and update the index
                return (E) array[currentIndex++];
            }
        };
    }

    // Ensures that the internal array has sufficient capacity to accommodate the specified minimum capacity
    private void ensureCapacity(int minCapacity) {
        int currentCapacity = array.length;

        // If the current capacity is insufficient, double it or use the specified minimum capacity
        if (minCapacity > currentCapacity) {
            int newCapacity = Math.max(currentCapacity * 2, minCapacity);
            Object[] newArray = new Object[newCapacity];
            copyArray(array, newArray, size);
            array = newArray;
        }
    }

    // Copies elements from the source array to the destination array up to the specified length
    private static void copyArray(Object[] src, Object[] dest, int length) {
        for (int i = 0; i < length; i++) {
            dest[i] = src[i];
        }
    }
}
