package implementations;

import exceptions.EmptyStackException;
import utilities.StackADT;
import utilities.Iterator;
import java.io.Serializable;

public class MyStack<E> implements StackADT<E>, Serializable {
    private static final long serialVersionUID = 1L;
    private MyArrayList<E> arrayList;

    // Constructs an empty stack
    public MyStack() {
        arrayList = new MyArrayList<>();
    }

    
    // Adds an element to the top of the stack.
    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot add null to the stack.");
        arrayList.add(toAdd);
    }

    // Adds an element to the top of the stack.
    @Override
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        int lastIndex = arrayList.size() - 1;
        E element = arrayList.get(lastIndex); 
        arrayList.remove(lastIndex); 
        return element;
    }


    
    // Returns the element at the top of the stack without removing it.
    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException("Stack is empty.");
        return arrayList.get(arrayList.size() - 1);
    }

    // Removes all elements from the stack.
    @Override
    public void clear() {
        arrayList.clear();
    }

    // Checks if the stack is empty.
    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    // Checks if the stack contains the specified element.
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return arrayList.contains(toFind);
    }

    // Finds the 1-based position of the specified element from the top of the stack.
    @Override
    public int search(E toFind) {
        return findIndex(toFind) == -1 ? -1 : arrayList.size() - findIndex(toFind);
    }
    
    // Helper method to find the index of an element in the stack.
    private int findIndex(E toFind) {
        if (toFind == null) throw new NullPointerException("Cannot search for null in the stack.");
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals(toFind)) {
                return i;
            }
        }
        return -1;
    }

    // Returns an iterator over the elements in the stack.
    @Override
    public Iterator<E> iterator() {
        return arrayList.iterator();
    }

    // Compares this stack to another stack for equality.
    @Override
    public boolean equals(StackADT<E> that) {
        if (this.size() != that.size()) return false;
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = that.iterator();
        while (thisIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) return false;
        }
        return true;
    }

    // Converts the stack to an array of objects.
    @Override
    public Object[] toArray() {
        return arrayList.toArray();
    }

    // Converts the stack to an array of the specified type.
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return arrayList.toArray(holder);
    }

    // Checks if the stack is at its capacity. Not applicable for dynamic stacks.
    @Override
    public boolean stackOverflow() {
        return false;
    }

    // Returns the number of elements in the stack.
    @Override
    public int size() {
        return arrayList.size();
    }
}
