package ads.lab6;

import ads.lab5.BinaryHeap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A class for the heap sort algorithm.
 */
public class HeapSort {
	
	/**
	 * Sort the array in place using the heapsort algorithm
	 * Complexity: THETA( n.log(n) ) where n is the size of the array
	 */	
	public static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array) {
		BinaryHeap<AnyType> heap = new BinaryHeap<>(array, Comparator.reverseOrder());

		for (int i = array.length - 1; i > 0; i--) {
			try {
				array[i] = heap.deleteExtreme();
			} catch (Exception e) {}
		}
	}

	public static void main(String[] args) {
		Integer[] toto = new Integer[]{3, 5, 6, 2, 1};
		sort(toto);
		System.out.println(Arrays.toString(toto));
	}
}
