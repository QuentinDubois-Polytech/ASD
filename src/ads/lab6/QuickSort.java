package ads.lab6;

import java.util.Arrays;

/**
 * A class for the quicksort algorithm
 */
public class QuickSort {

    private static final int CUTOFF = 10;

    /**
     * Sort the array in place using the quicksort algorithm
     */
    public static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array) {
        sort(array, 0, array.length - 1);
    }

    /**
     * Sort the portion array[lo,hi] in place using the quicksort algorithm
     */
    private static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array, int lo, int hi) {
        int cmp = hi - lo;
        if (cmp >= 1 && cmp < CUTOFF) {
            insertion(array, lo, hi);
        } else if (cmp >= CUTOFF) {
            int indexPivot = partition(array, lo, hi);
            sort(array, lo, indexPivot - 1);
            sort(array, indexPivot + 1, hi);
        }
    }

    /**
     * Partition the portion array[lo,hi] and return the index of the pivot
     */
    private static <AnyType extends Comparable<AnyType>> int partition(AnyType[] array, int lo, int hi) {
        int indexPivot = median(array, lo, (lo + hi) / 2, hi);

        if (indexPivot != lo) {
            swap(array, indexPivot, lo);
        }

        int i = lo + 1;
        int j = hi;
        while (i <= j) {
            if (array[j].compareTo(array[lo]) > 0) {
                j--;
            } else if (array[i].compareTo(array[lo]) <= 0) {
                i++;
            } else {
                swap(array, i++, j--);
            }
        }


        if (array[j].compareTo(array[lo]) < 0) {
            swap(array, lo, j);
        }
        return j;
    }

    /**
     * Return the index of the median of { array[lo], array[mid], array[hi] }
     */
	private static <AnyType extends Comparable<AnyType>> int median(AnyType[] array, int lo, int mid, int hi) {
		int cmpMidHigh = array[mid].compareTo(array[hi]);
		if ((cmpMidHigh < 0) ^ (array[hi].compareTo(array[lo]) > 0)) {
			return hi;
		} else if ((cmpMidHigh > 0) ^ (array[mid].compareTo(array[lo]) > 0)) {
			return mid;
		} else {
			return lo;
		}
	}

    /**
     * Sort array[lo, hi] in place using the insertion sort algorithm
     */
    private static <AnyType extends Comparable<AnyType>> void insertion(AnyType[] array, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            AnyType value = array[i];
            int j;
            for (j = i; j > lo && array[j - 1].compareTo(value) > 0; j--) {
                array[j] = array[j - 1];
            }
            array[j] = value;
        }
    }

    /**
     * Swap array[i] and array[j]
     */
    private static <AnyType> void swap(AnyType[] array, int i, int j) {
        AnyType tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

	public static void main(String[] args) {
        // Integer[] array = new Integer[]{1, 65, 70, 50, 60, 48, 40, 50, 1};
		Integer[] array = new Integer[]{237, 121, 165, 142, 1, 13, 70, 229};    // 237, 121, 165, 142, 1, 13, 70, 229
		sort(array);
		System.out.println(Arrays.toString(array));
	}
}
