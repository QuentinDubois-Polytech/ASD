package ads.lab6;

import java.util.Arrays;

/**
 * A class for the recursive merge sort algorithm.
 */
public class MergeSort {

	/**
	 * Sort the array using the recursive merge sort algorithm.
	 * This algorithm is not in place and needs an auxiliary array of
	 * the same size than the array to sort
	 * Complexity: THETA( n.log(n) ) where n is the size of the array
	 */
	@SuppressWarnings("unchecked")
	public static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array) {
		if (array.length >= 2) {
			AnyType[] tmp = (AnyType[]) new Comparable[array.length];
			sort(array, tmp, 0, array.length - 1);
		}
	}
	
	/**
	 * Sort the array in the range [lo, hi] using the portion [lo, hi]
	 * of the auxiliary array tmp
	 * Complexity: THETA( n.log(n) ) where n = hi - lo + 1
	 */
	private static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array, AnyType[] tmp, int lo, int hi) {
		if (lo < hi) {
			int middle = (hi + lo) / 2;
			sort(array, tmp, lo, middle);
			sort(array, tmp, middle + 1, hi);
			merge(array, tmp, lo, middle, hi);
			transfer(tmp, array, lo, hi);
		}
	}
	
	/**
	 * Merge array[lo, mid] and array[mid+1, hi] into tmp[lo, hi]
	 * and copy back the result into array[lo, hi]
	 * Precondition: array[lo, mid] and array[mid+1, hi] are sorted
	 * Complexity: THETA( n ) where n = hi - lo + 1
	 */
	private static <AnyType extends Comparable<AnyType>> void merge(AnyType[] array, AnyType[] tmp, int lo, int mid, int hi) {
		int i = lo;
		int j = mid + 1;
		int k = lo;

		while (i <= mid && j <= hi) {
			tmp[k++] = (array[i].compareTo(array[j]) < 0) ? array[i++] : array[j++];
		}

		while (i <= mid) {
			tmp[k++] = array[i++];
		}

		while (j <= hi) {
			tmp[k++] = array[j++];
		}
	}
	
	/**
	 * Copy the elements from tmp[lo, hi] into array[lo, hi]
	 * Complexity: THETA( n ) where n = hi - lo + 1
	 */
	private static <AnyType> void transfer(AnyType[] tmp, AnyType[] array, int lo, int hi) {
		if (hi + 1 - lo >= 0) System.arraycopy(tmp, lo, array, lo, hi + 1 - lo);
	}

	public static void main(String[] args) {
		Integer[] toto = new Integer[]{3, 5, 6, 2, 1, 4};
		sort(toto);
		System.out.println(Arrays.toString(toto));

	}
}
