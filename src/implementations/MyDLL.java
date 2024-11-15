package implementations;

import utilities.Iterator;
import utilities.ListADT;

public class MyDLL<E> implements ListADT<E> {

	private static final long serialVersionUID = -1080782129422688520L;

	//attributes
	private MyDLLNode<E> head, tail;
	private int size;
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		head = null;
        tail = null;
        size = 0;
		
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd == null) 
			throw new NullPointerException();
        if (index < 0 || index > size) 
        	throw new IndexOutOfBoundsException();

        if (index == size) { 
        	// Adding to the end
            return add(toAdd);
        } else if (index == 0) { 
        	// Adding to the start
            MyDLLNode<E> newNode = new MyDLLNode<>(toAdd, null, head);
            if (head != null) {
                head.setPrev(newNode);
            }
            head = newNode;
            if (tail == null) {
                tail = head;
            }
        } else { 
        	// Adding in the middle
            MyDLLNode<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            MyDLLNode<E> newNode = new MyDLLNode<>(toAdd, current.getPrev(), current);
            current.getPrev().setNext(newNode);
            current.setPrev(newNode);
        }
        size++;
        return true;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null) 
			throw new NullPointerException();

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd, tail, null);
        if (tail == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
        return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if (toAdd == null)
			throw new NullPointerException("The provided list cannot be null.");
		
		Iterator<? extends E> iterator = toAdd.iterator();
		//Iterate over each element in the list, get that element and adds it to the end of the list
		while (iterator.hasNext()) {
			E element = iterator.next();
			add(element); 
		}
		
		return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		//Start from the head of the node and traverse to the specified index
		MyDLLNode<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.element;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
	    if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException();
	    }

	    MyDLLNode<E> current = head;
	    if (index == 0) { // Removing the head
	        head = head.getNext();
	        if (head != null) {
	            head.setPrev(null);
	        } else {
	            tail = null;
	        }
	    } else if (index == size - 1) { // Removing the tail
	        current = tail;
	        tail = tail.getPrev();
	        tail.setNext(null);
	    } else { // Removing from the middle
	        for (int i = 0; i < index; i++) {
	            current = current.getNext();
	        }
	        current.getPrev().setNext(current.getNext());
	        current.getNext().setPrev(current.getPrev());
	    }
	    size--;
	    return current.getElement();
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
	    if (toRemove == null)
	        throw new NullPointerException();

	    MyDLLNode<E> current = head;
	    while (current != null) {
	        if (current.getElement().equals(toRemove)) {
	            if (current == head) {
	                head = head.getNext();
	                if (head != null) {
	                    head.setPrev(null);
	                } else {
	                    tail = null;
	                }
	            } else if (current == tail) {
	                tail = tail.getPrev();
	                tail.setNext(null);
	            } else {
	                current.getPrev().setNext(current.getNext());
	                current.getNext().setPrev(current.getPrev());
	            }
	            size--;
	            return current.getElement();
	        }
	        current = current.getNext();
	    }
	    return null; 
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
	    if (toChange == null)
	        throw new NullPointerException();
	    if (index < 0 || index >= size)
	        throw new IndexOutOfBoundsException();

	    MyDLLNode<E> current = head;
	    for (int i = 0; i < index; i++) {
	        current = current.getNext();
	    }
	    E oldElement = current.getElement();
	    current.setElement(toChange);
	    return oldElement;
	}

	@Override
	public boolean isEmpty() {
	    return size == 0;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
	    if (toFind == null)
	        throw new NullPointerException();

	    MyDLLNode<E> current = head;
	    while (current != null) {
	        if (current.getElement().equals(toFind)) {
	            return true;
	        }
	        current = current.getNext();
	    }
	    return false;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
	    if (toHold == null)
	        throw new NullPointerException();

	    if (toHold.length < size) {
	        toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
	    }

	    MyDLLNode<E> current = head;
	    for (int i = 0; i < size; i++) {
	        toHold[i] = current.getElement();
	        current = current.getNext();
	    }

	    if (toHold.length > size) {
	        toHold[size] = null; // Null-terminate the array if it’s larger
	    }
	    return toHold;
	}

	@Override
	public Object[] toArray() {
	    Object[] array = new Object[size];
	    MyDLLNode<E> current = head;
	    for (int i = 0; i < size; i++) {
	        array[i] = current.getElement();
	        current = current.getNext();
	    }
	    return array;
	}

	@Override
	public Iterator<E> iterator() {
	    return new MyIterator();
	}

	private class MyIterator implements Iterator<E> {
	    private MyDLLNode<E> current = head;  // Start from the head node

	    @Override
	    public boolean hasNext() {
	        return current != null;  
	    }

	    @Override
	    public E next() throws NoSuchElementException {
	        if (!hasNext()) {
	            throw new NoSuchElementException("No more elements found.");
	        }

	        E result = current.getElement();  // Get the element at the current node
	        current = current.getNext();      // Move to the next node
	        return result;  
		}
	}

}