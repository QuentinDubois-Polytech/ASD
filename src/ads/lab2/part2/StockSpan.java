package ads.lab2.part2;

import ads.lab2.part1.ArrayStack;
import ads.lab2.part1.EmptyStackException;

import java.util.Arrays;

/**
 * A class to provide two implementations
 * of the stock span algorithm
 */
public class StockSpan {
	
    /**
     * A short main for quick testing
     */
	public static void main(String[] args) throws EmptyStackException {
		int[] prices = {100,80,60,70,60,75,85,120};
		
		System.out.println(Arrays.toString(naive(prices)));
		System.out.println(Arrays.toString(smart(prices)));
	}
	// expected output
	//
	// [1, 1, 1, 2, 1, 4, 6, 8]
	// [1, 1, 1, 2, 1, 4, 6, 8]
	
	/**
	 * Compute and return the span of stocks
	 * whose prices are stored in 'prices'
	 * Complexity: O(n²) where n = prices.length
	 */
	public static int[] naive(int[] prices) {
		int[] span = new int[prices.length];

		span[0] = 1;
		for (int i = 1; i < prices.length; i++) {
			int res = 1;
			for (int j = i-1; j >= 0; j--) {
				if (prices[i] > prices[j]) {
					res++;
				}
			}
			span[i] = res;
		}
		return span;
	}

	/**
	 * Compute and return the span of stocks
	 * whose prices are stored in 'prices'
	 * Complexity: O(n) where n = prices.length
	 */
	public static int[] smart(int[] prices) throws EmptyStackException {
		int[] span = new int[prices.length];
		ArrayStack<Integer> stack = new ArrayStack<>();

		span[0] = 1;
		stack.push(0);
		for (int i = 1; i < prices.length; i++) {
			int res = 1;
			for (int j = 0; j <= stack.size(); j++) {
				if (prices[stack.peek()] < prices[i]) {
					res += span[stack.peek()];
					stack.pop();
				} else {
					break;
				}
			}
			stack.push(i);
			span[i] = res;
		}
		return span;
	}
}
