package ads.lab4;

import java.util.*;

/**
 * A class for Binary Search Trees
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> implements Iterable<AnyType> {

    // The tree root
	private BinaryNode<AnyType> root;

    /**
     * Construct the tree.
     */
    public BinarySearchTree( ) {
        root = null;
    }
    
    /////////////// isEmpty
    
    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( ) {
        return root.element == null;
    }
    
    /////////////// makeEmpty  

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( ) {
        root = new BinaryNode<>(null, null, null);
    }

    /////////////// contains
    
    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x ) {
        return contains(x, root);
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t ) {
        if (t == null) {
            return false;
        }

        int cmp = t.element.compareTo(x);

        if (cmp > 0) {
            return contains(x, t.left);
        } else if (cmp < 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }
        
    /////////////// insert
    
    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x ) {
        root = insert(x, root);
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t ) {
        if (t == null) {
            return new BinaryNode<>(x);
        }

        int cmp = t.element.compareTo(x);

        if (cmp < 0) {
            t.right = insert(x, t.right);
        } else if (cmp > 0) {
            t.left = insert(x, t.left);
        } else {
            return t;
        }

        return t;
    }
    /////////////// findMin
    
    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( ) throws EmptyTreeException {
        BinaryNode<AnyType> tree = findMin(root);
        if (tree == null) {
            throw new EmptyTreeException();
        }

        return tree.element;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t ) {
        if (t == null) {
            return null;
        }

        if (t.left == null) {
            return t;
        }

        return findMin(t.left);
    }
    
    /////////////// findMax
    
    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( ) throws EmptyTreeException {
        BinaryNode<AnyType> tree = findMax(root);
        if (tree == null) {
            throw new EmptyTreeException();
        }

        return tree.element;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t ) {
        if (t == null) {
            return null;
        }

        if (t.right == null) {
            return t;
        }

        return findMax(t.right);
    }

    /////////////// remove
    
    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x ) {
        root = remove(x, root);
       
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t ) {
//        if (t == null) {
//            return root;
//        }
//
//        int cmp = root.element.compareTo(x);
//
//        if (cmp == 0) {
//            if (root.right == null && root.left == null) {
//                return null;
//            } else if (root.left == null) {
//
//            }
//        }
        return null;
    }

    /////////////// removeLessThan
    
    /**
     * Remove from the tree all the elements
     * less than min
     * @param min the minimum value left in the tree
     */
    public void removeLessThan(AnyType min) {
    	
    }
    
    private BinaryNode<AnyType> removeLessThan(BinaryNode<AnyType> t, AnyType min) {
    	return null;
    }
    
    /////////////// removeGreaterThan
    
    /**
     * Remove from the tree all the elements
     * greater than max
     * @param max the maximum value left in the tree
     */
    public void removeGreaterThan(AnyType max) {
    	
    }
    
    private BinaryNode<AnyType> removeGreaterThan(BinaryNode<AnyType> t, AnyType max) {
    	return null;
    }
    
    /////////////// toSortedList
    
    /**
     * Return a sorted list (increasing) of all
     * the elements of the tree
     * @return the sorted list of all the elements of the tree
     */
    public List<AnyType> toSortedList() {
    	return null;
    }
    
    private void toSortedList(BinaryNode<AnyType> t, List<AnyType> list) {
 
    }
    
    /////////////// sorted list to binary search tree
    
    /**
     * Build a binary search tree with all the
     * elements of the list
     * @param list a sorted (increasing) list of elements
     */
    public BinarySearchTree(List<AnyType> list) {
 
    }
    
    private BinaryNode<AnyType> makeTree(List<AnyType> list, int i, int j) {
    	return null;
    }
   
    /////////////// iterator on binary search tree
    
    /**
     * Return an iterator over the elements of the tree.
     * The elements are enumerated in increasing order.
     */
	public Iterator<AnyType> iterator() {
		return new BSTiterator(root);
	}
	
	/**
	 * Inner class to build iterator over the elements of
	 * a tree
	 */
	private class BSTiterator implements Iterator<AnyType> {
		
		// we must push some binary nodes on the stack
		Stack<BinaryNode<AnyType>> stack; 
		
		/**
		 * Build an iterator over the binary node n.
		 * The elements are enumerated in increasing order.
		 */
		BSTiterator(BinaryNode<AnyType> n) {

		}
		
		/**
		 * Check if there are more elements in the
		 * iterator
		 */
		public boolean hasNext() {
			return false;
		}
		
		/**
		 * Return and remove the next element from
		 * the iterator
		 */
		public AnyType next() {
			return null;
		}
		
		/**
		 * Unsupported operation
		 */
		public void remove() {
			throw new UnsupportedOperationException("remove");
		}
	}

	////////////////////////////////////////////////////
	// Convenience method to print a tree
	////////////////////////////////////////////////////
    
    public void display() {
    	display(root,"","");
    }

    private void display(BinaryNode<AnyType> t, String r, String p) {
        if ( t == null ) {
            System.out.println(r);
        }
        else {
            String rs = t.element.toString();
            System.out.println(r + rs);
            if ( t.left != null || t.right != null ) {
                String rr = p + '|' + makeString('_',rs.length()) + ' ';
                display(t.right,rr, p + '|' + makeString(' ',rs.length() + 1));
                System.out.println(p + '|');
                display(t.left,rr, p + makeString(' ',rs.length() + 2));
            }
        }
    }

    private String makeString(char c, int k) {
        String s = "";
        for ( int i = 0; i < k; i++ ) {
            s += c;
        }
        return s;
    }
    
	////////////////////////////////////////////////////
	// Inner class BinaryNode<AnyType>
	////////////////////////////////////////////////////    
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
            // Constructors
        BinaryNode( AnyType theElement ) {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt ) {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }
    
	////////////////////////////////////////////////////
	// Convenience methods to build a list of integer from a string
	////////////////////////////////////////////////////    
    
    private static List<Integer> read(String inputString) {
    	List<Integer> list = new LinkedList<Integer>();
    	Scanner input = new Scanner(inputString);
    	while ( input.hasNextInt() )
    		list.add(input.nextInt());
    	input.close();
    	return list;
    }
    
    /**
     * A short main for quick testing
     */
    public static void main( String [ ] args ) throws EmptyTreeException {
    	List<Integer> list = read("50 30 70 20 40 80 60");
    	BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
    	for ( Integer n : list )
    		bst.insert(n);
    	bst.display();


        System.out.println(bst.contains(20));
    }
}
