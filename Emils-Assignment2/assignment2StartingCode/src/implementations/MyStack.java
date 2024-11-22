package implementations;

import java.io.Serializable;
// import exceptions.EmptyStackException; was getting errors using a custom Empty StackException even though it was implemented properly and is the same as the java.util.EmptyStackException
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import utilities.*;

public class MyStack<E> implements StackADT<E>, Serializable {
    private static final long serialVersionUID = 1L; // For serialization compatibility.
    private MyArrayList<E> stack; // The list that stores the stack elements.
    private int capacity; // The maximum capacity of the stack (-1 for no limit).

    // Constructor: Initializes an empty stack with no fixed capacity.
    public MyStack() {
        this.stack = new MyArrayList<>();
        this.capacity = -1; // No fixed size.
    }
    
    // Pushes an element to the top of the stack.
    // Throws an exception if the element is null or if the stack is full.
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot add null to the stack.");
        stack.add(toAdd); // Adds the element to the top of the stack.
    }

    // Pops the top element from the stack and returns it.
    // Throws EmptyStackException if the stack is empty.
    public E pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return stack.remove(stack.size() - 1); // Removes and returns the top element.
    }

    // Peeks at the top element of the stack without removing it.
    // Throws EmptyStackException if the stack is empty.
    public E peek() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return stack.get(stack.size() - 1); // Returns the top element without removing it.
    }

    // Clears the stack, removing all elements.
    public void clear() {
        stack.clear(); // Removes all elements from the stack.
    }

    // Checks if the stack is empty.
    public boolean isEmpty() {
        return stack.isEmpty(); // Returns true if the stack has no elements.
    }

    // Converts the stack to an array of Objects.
    public Object[] toArray() {
    	Object[] result = new Object[size()]; // Create a new Object array.
        for (int i = 0; i < size(); i++) {
            result[size() - 1 - i] = stack.get(i); // Add each element to the array.
        }
        return result;
    }

    // Converts the stack to an array of a specific type.
    @SuppressWarnings("unchecked")
	public E[] toArray(E[] holder) {
        if (holder == null) {
            throw new NullPointerException("The provided array cannot be null.");
        }

        // If the holder array is too small, create a new array of the same runtime type.
        if (holder.length < stack.size()) {
            holder = (E[]) java.lang.reflect.Array.newInstance(holder.getClass().getComponentType(), stack.size());
        }

        // Copy elements from the stack into the array.
        for (int i = 0; i < stack.size(); i++) {
            holder[size() - 1 - i] = stack.get(i); // Maintain the stack's order.
        }

        // If the holder array is larger, set the remaining elements to null.
        if (holder.length > stack.size()) {
            holder[stack.size()] = null;
        }

        return holder;
    }

    // Checks if the stack contains a specific element.
    // Throws NullPointerException if the element to find is null.
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Element to find cannot be null.");
        return stack.contains(toFind); // Checks if the stack contains the specified element.
    }

    // Searches for an element in the stack and returns its 1-based position from the top.
    // Returns -1 if the element is not found.
    public int search(E toFind) {
        if (toFind == null) return -1; // Null elements cannot be found.
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (toFind.equals(stack.get(i))) {
                return stack.size() - i; // Return the 1-based position from the top.
            }
        }
        return -1; // Element not found.
    }

    // Creates an iterator for the stack to iterate from the top to the bottom.
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = stack.size() - 1; // Start at the top of the stack.

            // Checks if there are more elements to iterate through.
            @Override
            public boolean hasNext() {
                return currentIndex >= 0; // Iterates until the bottom of the stack.
            }

            // Returns the next element in the stack.
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the stack.");
                }
                return stack.get(currentIndex--); // Return the current element and move down.
            }
        };
    }

    // Compares this stack to another stack for equality.
    public boolean equals(StackADT<E> that) {
        if (that == null || this.size() != that.size()) return false; // Different sizes imply inequality.
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = that.iterator();
        while (thisIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) return false; // Compare each element.
        }
        return true; // All elements match.
    }

    // Returns the number of elements in the stack.
    public int size() {
        return stack.size(); // Returns the number of elements in the stack.
    }

    // Checks if the stack has reached its capacity.
    public boolean stackOverflow() {
        return capacity != -1 && stack.size() >= capacity; // Checks if the stack has reached its capacity.
    }
}
