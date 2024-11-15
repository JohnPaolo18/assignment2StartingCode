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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(E toFind) throws NullPointerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
