package implementations;

import utilities.*;
import java.util.NoSuchElementException;

/**
 * A generic implementation of a doubly linked list (DLL).
 * @param <E> The type of elements stored in the list.
 */
@SuppressWarnings("serial")
public class MyDLL<E> implements ListADT<E> {

    // Head and tail pointers for managing the doubly linked list structure.
    public MyDLLNode<E> head;
    public MyDLLNode<E> tail;
    private int size; // Keeps track of the number of elements in the list.

    /**
     * Constructs an empty doubly linked list.
     */
    public MyDLL() {
        this.head = this.tail = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in the list.
     * @return The size of the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Clears the list by resetting the head, tail, and size to their initial state.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds an element at a specific index in the list.
     * @param index The position where the element should be inserted.
     * @param toAdd The element to be added.
     * @return True if the element is successfully added, false otherwise.
     * @throws NullPointerException if the element to be added is null.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);

        if (index == 0) {
            newNode.setNext(head);
            if (head != null) {
                head.setPrev(newNode);
            } else {
                tail = newNode;
            }
            head = newNode;
        } else {
            MyDLLNode<E> curr = head;

            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }

            newNode.setPrev(curr);
            newNode.setNext(curr.getNext());

            if (curr.getNext() != null) {
                curr.getNext().setPrev(newNode);
            } else {
                tail = newNode; // Add new node at the end.
            }

            curr.setNext(newNode);
        }

        size++;
        return true;
    }

    /**
     * Adds an element at the end of the list.
     * @param toAdd The element to be added.
     * @return True if the addition is successful, false otherwise.
     * @throws NullPointerException if the element to be added is null.
     */
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        return add(size, toAdd);
    }

    /**
     * Adds all elements from another list to the end of this list.
     * @param toAdd The list containing elements to be added.
     * @return True if the addition is successful, false otherwise.
     * @throws NullPointerException if any element in the list to be added is null.
     */
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        Iterator<? extends E> iterator = toAdd.iterator();
        while (iterator.hasNext()) {
            E next = (iterator.next());
            if (next == null) {
                throw new NullPointerException("Cannot add null element from collection");
            }
            add(next);
        }
        return true;
    }

    /**
     * Retrieves the element at a specific index in the list.
     * @param index The position of the element to retrieve.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        MyDLLNode<E> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }

        return curr.getItem();
    }

    /**
     * Removes the element at a specific index from the list.
     * @param index The position of the element to be removed.
     * @return The removed element.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        MyDLLNode<E> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }

        // Adjust the pointers to remove the node at the specified index.
        if (curr.getPrev() != null) {
            curr.getPrev().setNext(curr.getNext());
        } else {
            head = curr.getNext();
        }

        if (curr.getNext() != null) {
            curr.getNext().setPrev(curr.getPrev());
        } else {
            tail = curr.getPrev();
        }

        size--;
        return curr.getItem();
    }

    /**
     * Removes the first occurrence of a specific element from the list.
     * @param toRemove The element to be removed.
     * @return The removed element, or null if the element is not found.
     * @throws NullPointerException if the element to be removed is null.
     */
    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null)
            throw new NullPointerException();

        MyDLLNode<E> curr = head;

        while (curr != null && !curr.getItem().equals(toRemove)) {
            curr = curr.getNext();
        }

        if (curr != null) {
            if (curr.getPrev() != null) {
                curr.getPrev().setNext(curr.getNext());
            } else {
                head = curr.getNext();
            }

            if (curr.getNext() != null) {
                curr.getNext().setPrev(curr.getPrev());
            } else {
                tail = curr.getPrev();
            }

            size--;
            return curr.getItem();
        } else {
            return null; // Element not found.
        }
    }

    /**
     * Replaces the element at a specific index in the list with a new element.
     * @param index The position of the element to be replaced.
     * @param toChange The new element to set.
     * @return The previous element at the specified index.
     * @throws NullPointerException if the new element is null.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) {
            throw new NullPointerException("Cannot set null element");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        MyDLLNode<E> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }

        E previousData = curr.getItem();
        curr.setItem(toChange);

        return previousData;
    }

    /**
     * Checks whether the list is empty.
     * @return True if the list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the list contains a specific element.
     * @param toFind The element to search for.
     * @return True if the element is found, false otherwise.
     * @throws NullPointerException if the element to be searched for is null.
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null)
            throw new NullPointerException();

        MyDLLNode<E> curr = head;

        while (curr != null) {
            if (curr.getItem().equals(toFind)) {
                return true;
            }
            curr = curr.getNext();
        }

        return false; // Element not found.
    }

    /**
     * Converts the list into an array of the specified type.
     * @param objects The array to hold the elements from the list.
     * @return The array containing the elements of the list.
     * @throws NullPointerException if the array is null.
     */
    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(Object[] objects) throws NullPointerException {
        if (objects.length < size) {
            objects = (E[]) java.lang.reflect.Array.newInstance(
                    objects.getClass().getComponentType(), size);
        }

        MyDLLNode<E> curr = head;
        for (int i = 0; i < size; i++) {
            objects[i] = curr.getItem();
            curr = curr.getNext();
        }

        return (E[]) objects;
    }

    /**
     * Converts the list into an array of Objects.
     * @return The array containing the elements of the list.
     */
    @Override
    public Object[] toArray() {
        return toArray(new Object[size]);
    }

    /**
     * Returns an iterator for the list.
     * @return An iterator for traversing the list.
     */
    @Override
    public Iterator<E> iterator() {
        return new DLLIterator();
    }

    /**
     * Iterator for traversing the doubly linked list.
     */
    private class DLLIterator implements Iterator<E> {

        private MyDLLNode<E> currentNode = head;

        /**
         * Checks if there is a next element in the list.
         * @return True if there is a next element, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        /**
         * Returns the next element in the list.
         * @return The next element.
         * @throws NoSuchElementException if there are no more elements.
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E data = currentNode.getItem();
            currentNode = currentNode.getNext();

            return data;
        }
    }

    /**
     * A node in the doubly linked list, containing an item and pointers to the previous and next nodes.
     */
    private static class MyDLLNode<T> {
        private T item;
        private MyDLLNode<T> next;
        private MyDLLNode<T> prev;

        public MyDLLNode(T item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public MyDLLNode<T> getNext() {
            return next;
        }

        public void setNext(MyDLLNode<T> next) {
            this.next = next;
        }

        public MyDLLNode<T> getPrev() {
            return prev;
        }

        public void setPrev(MyDLLNode<T> prev) {
            this.prev = prev;
        }
    }
}
