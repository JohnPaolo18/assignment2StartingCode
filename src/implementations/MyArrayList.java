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
		return size == 0;
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
