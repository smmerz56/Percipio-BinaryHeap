package com.skillsoft.datastructures;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws HeapFullException, HeapEmptyException{
//		MinHeap<Integer> minHeap = new MinHeap<>(Integer.class);
//		
//		minHeap.insert(9);
//		minHeap.insert(4);
//		minHeap.insert(17);
//		minHeap.insert(6);
//		minHeap.insert(100);
//		minHeap.insert(3);
//		minHeap.insert(13);
//		minHeap.insert(23);
//		System.out.println(minHeap);
//		
//		System.out.println("----------------------------------------");
//		
//		System.out.println();
//		System.out.println(minHeap);
//		System.out.println("Highest priority element: "+ minHeap.getHighestPriority());
//		
//		System.out.println();
//		System.out.println(minHeap);
//		minHeap.removeHightPriority();
		
		int[] array = {4, 6, 9, 2, 10, 56, 12, 5, 1, 17, 14};
		System.out.println(Arrays.toString(array));
		
		heapSort(array);
		
		System.out.println(Arrays.toString(array));
	}
	
	public static int getParentIndex(int index, int endIndex) {
		if(index < 0 || index > endIndex) {
			return -1;
		}
		
		return (index - 1) / 2;
	}
	
	public static int getLeftChildIndex(int index, int endIndex) {
		
		int leftChildIndex = 2 * index +1;
		
		if(leftChildIndex > endIndex) {
			return -1;
		}
		
		return leftChildIndex;
	}
	
	private static int getRightChildIndex(int index, int endIndex) {
		
		int rightChildIndex = 2 * index +2;
		
		if(rightChildIndex > endIndex) {
			return -1;
		}
		
		return rightChildIndex;
	}
	
	//protected used by derived classes
	private static void swap(int[] array, int index1, int index2) {
		
		int tempValue = array[index1];
		
		array[index1] = array[index2];
		array[index2] = tempValue;
	}
	
	private static void percolateDown(int[] array, int index, int endIndex) {
		System.out.println("Percolating down: "+ array[index]);
		
		int leftChildIndex = getLeftChildIndex(index, endIndex);
		int rightChildIndex = getRightChildIndex(index, endIndex);
		
		int  largerIndex = -1;
		
		if(leftChildIndex != -1 && rightChildIndex != -1) {
			
			largerIndex = array[leftChildIndex] > array[rightChildIndex] ? leftChildIndex : rightChildIndex;
			
		} else if (leftChildIndex != -1) {
			largerIndex = leftChildIndex;			
		}else if(rightChildIndex != -1) {
			largerIndex = rightChildIndex;
		}
		
		if(largerIndex == -1) {
			System.out.println("Stop percolating down, found position");
			return;
		}
		
		//Note: Compare the larger child with the current index to see if a swap
		// and further sift down is needed.
		
		if(array[largerIndex] > array[index]) {
			swap(array, largerIndex, index);
			System.out.println("Swap: "+ Arrays.toString(array));
			percolateDown(array, largerIndex, endIndex);
		}
	}
	
	public static void heapify(int[] array, int endIndex) {
		int index = getParentIndex(endIndex, endIndex);
		
		System.out.println("Child: "+ array[endIndex]);
		System.out.println("Parent: "+array[index]);
		
		while(index >= 0) {
			System.out.println("\nProcessing index: "+ index +
					" Array: "+ Arrays.toString(array));
			percolateDown(array, index, endIndex);
			index--;
		}
	}
	
	public static void heapSort(int[] array) {
		heapify(array, array.length - 1);
		
		System.out.println("--------------------------------------");
		
		int endIndex = array.length -1;
		
		while(endIndex > 0) {
			System.out.println("\nEnd of heap: "+ array[endIndex]+
					" Array: "+ Arrays.toString(array));
			System.out.println("\nMove "+ array[0]+ " to the end");
			
			swap(array, 0, endIndex);
			System.out.println("\nAfter swap: "+ Arrays.toString(array));
			
			endIndex--;
			
			percolateDown(array, 0, endIndex);
		}
	}
}
