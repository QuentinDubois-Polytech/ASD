package ads.lab2;

/**
 * A class for stacks supporting the findMin operation in THETA(1)
 */
public class StackMin<AnyType extends Comparable<AnyType>> {

	private final ArrayStack<AnyType> stack;
	private final ArrayStack<AnyType> min;

	/**
	 * Build an empty stack
	 * Complexity: THETA(1)
	 */
	public StackMin() {
		stack = new ArrayStack<>();
		min = new ArrayStack<>();
	}
	
	/**
	 * Check if the stack is empty
	 * Complexity: THETA(1)
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * Return the next value to be popped from the stack
	 * Throw EmptyStackException if the stack is empty
	 * Complexity: THETA(1)
	 */		
	public AnyType peek() throws EmptyStackException {
		return stack.peek();
	}
	
	/**
	 * Push the value x onto the stack.
	 * If needed, the underlying array 
	 * will be enlarged by twice its size
	 * Complexity: THETA(1)
	 */
	public void push(AnyType x) throws EmptyStackException {
		stack.push(x);
		if (min.isEmpty() || min.peek().compareTo(x) >= 0){
			min.push(x);
		}
	}
	
	/**
	 * Pop the stack and return the value popped
	 * Throw EmptyStackException if the stack is empty
	 * Complexity: THETA(1)
	 */		
	public AnyType pop() throws EmptyStackException {
		if (stack.peek().equals(min.peek())) {
			min.pop();
		}

		return stack.pop();
	}
	
	/**
	 * Return the minimum value currently in the stack
	 * Throw EmptyStackException if the stack is empty
	 * Complexity: THETA(1)
	 */	
	public AnyType findMin() throws EmptyStackException {
		return min.peek();
	}

	public String toString() {
		return stack.toString();
	}
	
    /**
     * A short main for quick testing
     */
	public static void main(String[] args) throws EmptyStackException {
		StackMin<Integer> s = new StackMin<Integer>();
		s.push(3); s.push(1); s.push(2);
		System.out.println(s.findMin());
		s.pop(); s.pop(); s.push(5);
		System.out.println(s.findMin());
		s.push(2); s.push(4); s.push(6);
		System.out.println(s.findMin());
	}
	// expected output
	//
	// 1
	// 3
	// 2
}
