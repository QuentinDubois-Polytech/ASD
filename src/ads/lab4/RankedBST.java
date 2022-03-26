package ads.lab4;

import java.util.*;

/**
 * A class for binary search tree with rank
 */
public class RankedBST<AnyType extends Comparable<? super AnyType>> {

    // The tree root
    private BinaryNode<AnyType> root;

    /**
     * Construct the tree.
     */
    public RankedBST( ) {
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
        root = new BinaryNode<>(null);
    }
    
    /////////////// rank
    
    /**
     * Return the rank of x in the tree
     * @param x the element
     * @return the rank of x in the tree
     * if x is in the tree, 0 otherwise
     */
    public int rank(AnyType x) {
        return rank(root, x);
    }
    
    private int rank(BinaryNode<AnyType> t, AnyType x) {
    	if (t == null) {
            return 0;
        }

        int cmp = t.element.compareTo(x);

        if (cmp == 0) {
            return t.sizeOfLeft + 1;
        } else if (cmp < 0) {
            int value = rank(t.right, x);
            if (value == 0) {
                return 0;
            }
            return value + t.sizeOfLeft + 1;
        } else {
            return rank(t.left, x);
        }
    }
    
    /////////////// element
    
    /**
     * Return the element of rank r in the tree
     * @param r the rank
     * @return the element of rank r in the tree
     * if such element exists, null otherwise
     */
    public AnyType element(int r) {
    	return element(root, r);
    }
    
    private AnyType element(BinaryNode<AnyType> t, int r) {
    	if (t == null) {
            return null;
        }

        int cmp = t.sizeOfLeft + 1 - r;

        if (cmp == 0) {
            return t.element;
        } else if (cmp > 0) {
            return element(t.left, r);
        } else
            return element(t.right, r - t.sizeOfLeft - 1);
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
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t ) {
        if (t == null) {
            return new BinaryNode<>(x);
        }

        int cmp = t.element.compareTo(x);

        if (cmp < 0) {
            t.right = insert(x, t.right);
        } else if (cmp > 0) {
            t.sizeOfLeft++;
            t.left = insert(x, t.left);
        } else {
            return t;
        }

        return t;
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
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t ) {
        if (t == null) {
            return null;
        }

        int cmp = t.element.compareTo(x);

        if (cmp == 0) {
            if (t.right == null && t.left == null) {
                return null;
            } else if (t.left == null) {
                return t.right;
            } else if (t.right == null) {
                return t.left;
            }

            AnyType elem = findMax(t.left).element;
            t = remove(elem, t);
            t.element = elem;
            return t;
        } else if (cmp < 0) {
            t.right = remove(x, t.right);
        } else {
            t.sizeOfLeft--;
            t.left = remove(x, t.left);
        }
        return t;
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
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t ) {
        if (t == null) {
            return null;
        }

        if (t.right == null) {
            return t;
        }

        return findMax(t.right);
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
            rs = "(" + t.sizeOfLeft + ")" + rs;
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
            element  = theElement;
            left     = null;
            right    = null;
            sizeOfLeft = 0;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        
        int sizeOfLeft; // The size of the left subtree
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
    	RankedBST<Integer> bst = new RankedBST<Integer>();
    	for ( Integer n : list )
    		bst.insert(n);
    	bst.display();
        System.out.println("Rank of 60: " + bst.rank(60));
        System.out.println("Element of rank 6: " + bst.element(6));

//        System.out.println(bst.element(3));
//        bst.insert(35);
//        System.out.println(bst.element(7));
    }
}
