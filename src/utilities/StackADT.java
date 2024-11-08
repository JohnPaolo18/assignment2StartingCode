package utilities;

import java.util.Iterator;
import java.util.EmptyStackException;

/**
 * StackADT defines the interface for a stack collection.
 * 
 * @param <T> the type of elements in the stack
 */
public interface StackADT<T> {

    /**
     * Adds an element to the top of the stack.
     * Pre-condition: None
     * Post-condition: The element is added to the top of the stack.
     * 
     * @param element the element to push onto the stack
     * @throws NullPointerException if the element is null
     */
    void push(T element) throws NullPointerException;

    /**
     * Removes and returns the element at the top of the stack.
     * Pre-condition: The stack is not empty.
     * Post-condition: The top element is removed from the stack.
     * 
     * @return the element removed from the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    T pop() throws EmptyStackException;

    /**
     * Returns the top element of the stack without removing it.
     * Pre-condition: The stack is not empty.
     * Post-condition: The stack remains the same.
     * 
     * @return the element on top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    T peek() throws EmptyStackException;

    /**
     * Clears all elements from the stack.
     * Pre-condition: The stack is not empty.
     * Post-condition: The stack is empty.
     */
    void clear();

    /**
     * Returns an iterator for the elements in the stack.
     * Pre-condition: The stack is not empty.
     * Post-condition: The stack remains the same.
     * 
     * @return an iterator for the elements in the stack
     */
    Iterator<T> iterator();

    /**
     * Checks if the stack is empty.
     * Pre-condition: None
     * Post-condition: The stack remains the same.
     * 
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the stack.
     * Pre-condition: None
     * Post-condition: The stack remains the same.
     * 
     * @return the number of elements in the stack
     */
    int size();
}
