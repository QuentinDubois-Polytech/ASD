package ads.lab5;

import java.util.Comparator;

/**
 * A class for dynamic median finding.
 */
public class DynamicMedian<AnyType extends Comparable<? super AnyType>> {

    private final BinaryHeap<AnyType> less;    // to store values less than or equal to the median
    private final BinaryHeap<AnyType> greater; // to store values greater than or equal to the median

    /**
     * Build a DynamicMedian object of maximum capacity n
     */
    public DynamicMedian(int n) {
        less = new BinaryHeap<AnyType>(n, Comparator.naturalOrder());
        greater = new BinaryHeap<AnyType>(n, Comparator.reverseOrder());
    }

    /**
     * Return the size (the number of elements
     * currently in the DynamicMedian object)
     * Complexity: THETA(1)
     */
    public int size() {
        return less.size() + greater.size();
    }

    /**
     * Add a new element e.
     * Complexity: O(log(size))
     */
    public void add(AnyType e) throws EmptyHeapException, FullHeapException {
        if (size() == 0) {
            less.add(e);
            return;
        }

        if (findMedian().compareTo(e) > 0) {
            less.add(e);
            lessRebalancing();
        } else {
            greater.add(e);
            greaterRebalancing();
        }
    }

    /**
     * Return the current median.
     * Complexity: THETA(1)
     */
    public AnyType findMedian() throws EmptyHeapException {
        return less.extreme();
    }

    /**
     * Return and delete the current median
     * Complexity: O(log(size))
     */
    public AnyType deleteMedian() throws EmptyHeapException, FullHeapException {
        AnyType median = findMedian();
        less.deleteExtreme();
        greaterRebalancing();
        return median;
    }

    private void greaterRebalancing() throws EmptyHeapException, FullHeapException {
        if (less.size() - greater.size() == -1) {
            AnyType value = greater.extreme();
            greater.deleteExtreme();
            less.add(value);
        }
    }

    private void lessRebalancing() throws EmptyHeapException, FullHeapException {
        if (less.size() - greater.size() == 2) {
            greater.add(deleteMedian());
        }
    }
}
