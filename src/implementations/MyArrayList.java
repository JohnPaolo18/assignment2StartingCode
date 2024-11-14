package implementations;

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E> {
	
	private static final long serialVersionUID = 7486098015156606550L;
	//constants
	private final int CAPACITY = 10;
	private final int MULTIPLIER = 2;
	
	//attributes
	private E[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		array = (E[]) new Object[CAPACITY];
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		size = 0;
		
	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		if(toAdd == null)
			throw new NullPointerException();
		
		checkCapacity();
		shiftToRight(index);
		array[index] = toAdd;
		size++;
		return true;
	}

	private void shiftToRight(int index) {
		for(int i = size; i > index; i--) 
		{
			array[i] = array[i-1];
		}
		
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException {
		if (toAdd == null) 
			throw new NullPointerException();
		checkCapacity();
		array[size++] = toAdd;
		return true;
	}

	@SuppressWarnings("unchecked")
	private void checkCapacity() {
		if(size == array.length) 
		{
			E[] newArray = (E[]) new Object[array.length * MULTIPLIER];
			for(int i = 0; i < array.length; i++) 
			{
				newArray[i] = array[i];
			}
			
			array = newArray;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
		if (toAdd == null) 
	        throw new NullPointerException();

	    for (Object element : toAdd.toArray()) {
	        checkCapacity(); 
	        array[size++] = (E) element;  // Casting each Object to E
	    }
	    return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException();
        return array[index];
	}

	@Override
	//removes the element at the specified index, shift remaining elements to the left
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		return null;
	}

	//finds and removes the first occurrence of toRemove in the list.
	//if found, it shifts and returns the removed element; if not found, it returns null
	@Override
	public E remove(E toRemove) throws NullPointerException {
		if (toRemove == null)
			throw new NullPointerException();
		
		for (int i = 0; i < size; i++) {
			if (array[i].equals(toRemove)) {
				return remove(i);
			}
		}
		return null;
	}
	//replaces the element at a specified index with toChange and returns the element that was replaced
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
		if (toChange == null)
			throw new NullPointerException();
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		E oldElement = array[index];
		array[index] = toChange;
		return oldElement;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	//This method checks if the list contains an element equal toFind
	@Override
	public boolean contains(E toFind) throws NullPointerException {
		if (toFind == null)
			throw new NullPointerException();
		
		for (int i = 0; i < size; i++) {
			if(array[i].equals(toFind)) {
				return true;
			}
		}
		return false;
	}

	//This method copies elements from the list into toHold, or creates a new array of toHold is too small
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
	    if (toHold == null)
	        throw new NullPointerException();

	    if (toHold.length < size) {
	        return (E[]) java.util.Arrays.copyOf(array, size, toHold.getClass());
	    }
	    System.arraycopy(array, 0, toHold, 0, size);
	    if (toHold.length > size) {
	        toHold[size] = null;
	    }
	    return toHold;
	}

	//This method returns a new array containing all elements in the list
	@Override
	public Object[] toArray() {
		return java.util.Arrays.copyOf(array, size);
	}

	//This method returns an iterator over the list's elements.
	@Override
	public Iterator<E> iterator() {
	    return new Iterator<E>() {
	        private int currentIndex = 0;

	        @Override
	        public boolean hasNext() {
	            return currentIndex < size;
	        }

	        @Override
	        public E next() {
	            if (!hasNext())
	                throw new java.util.NoSuchElementException();
	            return array[currentIndex++];
	        }
	    };
	}

}
