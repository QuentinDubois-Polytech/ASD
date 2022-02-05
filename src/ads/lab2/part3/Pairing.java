package ads.lab2.part3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class to find pairs (x,y) of integers inside an increasing
 * sequence matching y = x + n for a given n.
 */
public class Pairing {

    /**
     * Display all the pairs (x,y), x and y  in the input, such that y = x + n
     * The input contains an entirely increasing (strict) sequence of integers
     * Running time complexity: THETA(S) where S is the size of the input
     * Extra memory usage: O(n)
     */
//    public static void showPairs(int n, Scanner input) throws EmptyQueueException {
//        ListQueue<Integer> numbers = new ListQueue<>();
//        if (input.hasNext()) {
//            int numberRead = input.nextInt();
//            int numberPair = numberRead + n;
//
//            int i = 0;
//            while (i++ < n && input.hasNext()) {
//                int number = input.nextInt();
//                if (number < numberPair) {
//                    numbers.enqueue(input.nextInt());
//                } else {
//                    if (nu)
//                }
//            }
//
//            if (numberRead)
//
//                if (numbers.contains(numberRead + n)) {
//                    System.out.println("(" + numberRead + "," + numberRead + n + ")");
//                }
//
//        }
//
//
//    }

    /**
     * A short main for quick testing
     */
    public static void main(String[] args) throws FileNotFoundException, EmptyQueueException {
        // put the right path here
        String filepath = System.getProperty("user.dir") + "\\src\\ads\\lab2\\part3\\big-file.txt";
        Scanner input = new Scanner(new File(filepath));
        //showPairs(1273, input);
        input.close();
    }

}
