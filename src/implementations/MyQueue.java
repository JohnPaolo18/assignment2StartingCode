package implementations;

import exceptions.EmptyQueueException;
import utilities.QueueADT;
import utilities.Iterator;
import java.io.Serializable;

public class MyQueue<E> implements QueueADT<E>, Serializable {
    private static final long serialVersionUID = 1L;
    private MyDLL<E> list;

    // Constructor
    public MyQueue() {
        list = new MyDLL<>();
    }
    
    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null to the queue.");
        }
        list.add(toAdd); // Adds to the end of the list
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty.");
        }
        return list.remove(0); // Removes from the front of the list
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException("Queue is empty.");
        }
        return list.get(0); // Gets the front element without removing it
    }

    @Override
    public void dequeueAll() {
        list.clear(); // Clears all elements from the queue
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty(); // Checks if the queue is empty
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return list.contains(toFind); // Uses MyDLL's contains method
    }

    @Override
    public int search(E toFind) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(toFind)) {
                return i + 1; // Return 1-based index
            }
        }
        return -1; // Element not found
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator(); // Returns an iterator from MyDLL
    }

    @Override
    public boolean equals(QueueADT<E> that) {
        if (this.size() != that.size()) return false;
        
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = that.iterator();
        
        while (thisIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        return list.toArray(); // Converts to a general Object array
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return list.toArray(holder); // Uses the specified type array
    }

    @Override
    public boolean isFull() {
        return false; // Assuming no fixed size, so it's never full
    }

    @Override
    public int size() {
        return list.size(); // Returns the current size of the queue
    }
}
