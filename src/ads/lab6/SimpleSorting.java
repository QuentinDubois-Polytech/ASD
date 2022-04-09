package ads.lab6;

/**
 * A class for simple sorting methods
 */
public class SimpleSorting {

    /**
     * Sort the array in place using the selection sort algorithm
     */
    public static <AnyType extends Comparable<AnyType>> void selection(AnyType[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j].compareTo(array[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                swap(array, i, min);
            }
        }
    }

    /**
     * Sort the array in place using the insertion sort algorithm
     */
    public static <AnyType extends Comparable<AnyType>> void insertion(AnyType[] array) {
        for (int i = 1; i < array.length; i++) {
            AnyType value = array[i];
            int j;
            for (j = i; j > 0 && array[j - 1].compareTo(value) > 0; j--) {
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
}
