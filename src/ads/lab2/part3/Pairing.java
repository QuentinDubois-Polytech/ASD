package ads.lab2.part3;

import javax.sound.midi.Soundbank;
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

    private static void printPairs(int x, int y) {
        System.out.print("(" + x + "," + y + ") ");
    }

    public static void showPairs(int n, Scanner input) throws EmptyQueueException {
        ListQueue<Integer> numbers = new ListQueue<>();
        while (input.hasNext()) {
            int numberRead = input.nextInt();
            while (! numbers.isEmpty() && numberRead > numbers.peek() + n) {
                numbers.dequeue();
            }

            if (! numbers.isEmpty() && numberRead == numbers.peek() + n) {
                printPairs(numbers.dequeue(), numberRead);
            }
            numbers.enqueue(numberRead);
        }
    }

    /**
     * A short main for quick testing
     */
    public static void main(String[] args) throws FileNotFoundException, EmptyQueueException {
        // put the right path here
        String filepath = System.getProperty("user.dir") + "\\src\\ads\\lab2\\part3\\little-file.txt";
        Scanner input = new Scanner(new File(filepath));
        showPairs(3, input);
        input.close();
    }

}
