package ads.lab5;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * A class for binary heap implementation
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
	
	private AnyType[] A; // to store the heap
	private int size;    // the number of elements in the heap
	
	// comparator to choose
	private Comparator<AnyType> c = Comparator.naturalOrder();
	
	///////////// Constructors
	
	/**
	 * Build a heap of capacity n.
	 * The elements are ordered according to the
	 * natural order on AnyType.
	 * The heap is empty.
	 * Complexity: THETA(1)
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap(int n) {
		A = (AnyType[]) new Comparable[n];
		size = 0;
	}
	
	/**
	 * Build a heap of capacity n.
	 * The elements are ordered according to c.
	 * The heap is empty.
	 * Complexity: THETA(1)
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeap(int n, Comparator<AnyType> c) {
		this.A = (AnyType[]) new Comparable[n];
		this.size = 0;
		this.c = c;
	}
	
	/**
	 * Build a heap based on array A.
	 * The elements are ordered according to the
	 * natural order on AnyType.
	 * The heap is full
	 */
	public BinaryHeap(AnyType[] A) {
		this.A = A;
		this.size = A.length;
		buildHeap();
	}

	/**
	 * Build a heap based on array A.
	 * The elements are ordered according to c.
	 * The heap is full
	 */
	public BinaryHeap(AnyType[] A, Comparator<AnyType> c) {
		this.A = A;
		this.size = A.length;
		this.c = c;
		buildHeap();
	}
	
	///////////// Private methods
	
	/**
	 * Swap values in the array
	 * at indexes i and j.
	 * Complexity: THETA(1)
	 */
	private void swap(int i, int j) {
		AnyType tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
	 
	/**
	 * Return the number of the left
	 * node of node number n.
	 * Complexity: THETA(1)
	 */
	private int left(int n) {
		return 2*n + 1;
	}
	
	/**
	 * Return the number of the right
	 * node of node number n.
	 * Complexity: THETA(1)
	 */
	private int right(int n) {
		return 2*(n + 1);
	}
	
	/**
	 * Return the number of the parent
	 * node of node number n.
	 * Complexity: THETA(1)
	 */
	private int parent(int n) {
		return (n - 1)/2;
	}
	
	/**
	 * Percolate down the element à node number n
	 * Complexity: O(log(size))
	 */
	private void percolateDown(int n) {
		if (n <= (size - 1) / 2) {
			int left = left(n);
			int right = right(n);

			if (left >= size && right >= size) {
				return;
			}

			int min;
			if (right >= size) {
				min = left;
			} else {
				min = (A[left].compareTo(A[right]) < 0) ? left : right;
			}

			if (A[n].compareTo(A[min]) > 0) {
				swap(n, min);
				percolateDown(min);
			}
		}
	}
		
	/**
	 * Percolate up the element à node number n
	 * Complexity: O(log(size))
	 */
	private void percolateUp(int n) {
		int parentElem = parent(n);
		if (parentElem >= 0) {
			if (A[parentElem].compareTo(A[n]) > 0) {
				swap(n, parentElem);
				percolateUp(parentElem);
			}
		}
	}
	
	/**
	 * Arrange the elements in A such
	 * that it has the heap property.
	 * Complexity: O(size)
	 */
	private void buildHeap() {
		for (int i = (size-1)/2; i >= 0; i--) {
			percolateDown(i);
		}
	}
	
	///////////// Public methods
	
	/**
	 * Return the size of the heap
	 * (the number of elements in the heap).
	 * Complexity: THETA(1)
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Check if the heap is empty.
	 * Complexity: THETA(1)
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Return the extreme element.
	 * Complexity: THETA(1)
	 */
	public AnyType extreme() throws EmptyHeapException {
		if (isEmpty()) {
			throw new EmptyHeapException();
		}
		return A[0];
	}
	
	/**
	 * Return and delete the extreme element.
	 * Complexity: O(log(size))
	 */
	public AnyType deleteExtreme() throws EmptyHeapException {
		if (isEmpty()) {
			throw new EmptyHeapException();
		}
		AnyType value = A[0];
		swap(0, size-1);
		percolateDown(0);
		size--;
		return value;
	}
	
	/**
	 * Add a new element in the heap
	 * Complexity: O(log(size))
	 */
	public void add(AnyType e) throws FullHeapException {
		if (size == A.length) {
			throw new FullHeapException();
		}
		A[size] = e;
		percolateUp(size++);
	}
	
	///////////// Part 3: deleting in the heap
	
	/**
	 * Delete the element e from the heap.
	 * Complexity: O(size)
	 */
	public void delete(AnyType e) {
		for (int i = 0; i < size; i++) {
			if (A[i].equals(e)) {
				swap(i, --size);
				percolateDown(i);
				percolateUp(i);
				break;
			}
		}
	}
	
	/**
	 * Delete all the elements e from the heap.
	 * Complexity: O(size)
	 */	
	public void deleteAll(AnyType e) {
		for (int i = 0; i < size; i++) {
			if (A[i].equals(e)) {
				int j;
				for (j = --size; j > i && A[j].equals(e); j--) {
					size--;
				}
				swap(i, j);
			}
		}

		for (int i = 0; i < size; i ++) {
			percolateDown(i);
			percolateUp(i);
		}
	}

	//toStringAsTab
	public String toStringAsTab() {
		if (size == 0) return "[]";
		String res = "[";
		int i = 0;
		while (i< size) {
			res = res +A[i]+", ";
			i++;
		}
		return res.substring(0,res.length()-2)+"]";
	}

	////////////////////////////////////////////////////
	// Convenience methods to build a list of integer from a string
	////////////////////////////////////////////////////
	private static List<Integer> read(String inputString) {
		List<Integer> list = new LinkedList<Integer>();
		Scanner input = new Scanner(inputString).useDelimiter("\\,\\s*");
		while ( input.hasNextInt() )
			list.add(input.nextInt());
		input.close();
		return list;
	}

	public static void main(String[] args) {

		BinaryHeap<Integer> bh2 = new BinaryHeap<>(new Integer[]{1, 1, 1, 1, 1, 1});
		System.out.println(bh2.toStringAsTab());
		bh2.deleteAll(1);
		System.out.println(bh2.toStringAsTab());
	}
}