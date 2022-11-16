package com.skillsoft.datastructures;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class Heap<T extends Comparable <T>> {
	private static int MAX_SIZE = 10;
	private T[] array;
	private int count = 0;
	
	
	@SuppressWarnings("unchecked")
	public Heap(Class<T> clazz) {
		array = (T[]) Array.newInstance(clazz, MAX_SIZE);
	}
	
	public int getCount() {
		return count;
	}
	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public boolean isFull() {
		return count == array.length;
	}
	
	public T getElementAtIndex(int index) {
		return array[index];
	}
	
	public int getLeftChildIndex(int parentIndex) {
		if(parentIndex < 0) {
			return -1;
		}
		
		int leftChildIndex = 2 * parentIndex +1;
		
		if(leftChildIndex >= count) {
			return -1;
		}
		
		return leftChildIndex;
	}
	
	public int getRighChildIndex(int parentIndex) {
		if(parentIndex < 0) {
			return -1;
		}
		
		int righChildIndex = 2 * parentIndex +2;
		
		if(righChildIndex >= count) {
			return -1;
		}
		
		return righChildIndex;
	}
	
	public int getParentIndex(int childIndex) {
		if(childIndex <= 0 || childIndex >= count) {
			return -1;
		}
		
		return (childIndex - 1) / 2;
	}
	//protected used by derived classes
	protected void swap(int index1, int index2) {
		T tempValue = array[index1];
		
		array[index1] = array[index2];
		array[index2] = tempValue;
	}
	
	//abstract because implementations will be different whether its a min/max heap
	protected abstract void siftDown(int index);
	protected abstract void siftUp(int index);
	
	public T getHighestPriority() throws HeapEmptyException{
		if(count == 0) {
			throw new HeapEmptyException("Empty heap!");
		}
		return (T)array[0];
	}
	
	public void insert(T value) throws HeapFullException{
		if(count >= array.length) {
			throw new HeapFullException("Full heap!");
		}
		array[count] = value;
		count++;
		
		System.out.println("Inserted: "+ value + " "+this.toString());
		
		siftUp(count -1);
	}
	
	public T removeHightPriority() throws HeapEmptyException{
		T first = getHighestPriority();
		
		array[0] = array[count -1];
		
		array[count -1] = null;
		count--;
		
		siftDown(0);
		
		return first;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(array);
	}
}
