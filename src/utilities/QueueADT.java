package utilities;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface QueueADT<T> {

    /**
     * Adds an element to the end of the queue.
     * Pre-condition: None
     * Post-condition: The element is added to the queue.
     * 
     * @param element the element to add
     * @throws NullPointerException if the element is null
     */
    void enqueue(T element) throws NullPointerException;

    /**
     * Removes and returns the element at the front of the queue.
     * Pre-condition: The queue is not empty.
     * Post-condition: The front element is removed from the queue.
     * 
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    T dequeue() throws NoSuchElementException;

    /**
     * Removes all elements from the queue.
     * Pre-condition: The queue is not empty.
     * Post-condition: The queue is empty.
     */
    void dequeueAll();

    /**
     * Returns the front element of the queue without removing it.
     * Pre-condition: The queue is not empty.
     * Post-condition: The queue remains the same.
     * 
     * @return the front element of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    T peek() throws NoSuchElementException;

    /**
     * Returns an iterator for the elements in the queue.
     * Pre-condition: The queue is not empty.
     * Post-condition: The queue remains the same.
     * 
     * @return an iterator for the elements in the queue
     */
    Iterator<T> iterator();

    /**
     * Checks if the queue is empty.
     * Pre-condition: None
     * Post-condition: The queue remains the same.
     * 
     * @return true if the queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the queue.
     * Pre-condition: None
     * Post-condition: The queue remains the same.
     * 
     * @return the number of elements in the queue
     */
    int size();
}
