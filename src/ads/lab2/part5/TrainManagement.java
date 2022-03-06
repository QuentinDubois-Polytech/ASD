package ads.lab2.part5;

import java.util.*;

/**
 * A class to arrange train configuration
 */
public class TrainManagement {
	
	private int[] from; // the initial ordering
	private int[] to;   // the final ordering

	private final String unsortedString = "U";
	private final String temporaryString = "T";
	private final String sortedString = "S";
	
	/**
	 * Build a TrainManagement object
	 * Preconditions:
	 * 'from' and 'to' have the same size N and are
	 * both permutations of [ 0, 1, 2,..., N-1 ]
	 */
	public TrainManagement(int[] from, int[] to) {
		this.from = from;
		this.to = to;
	}

	/**
	 * Output the basic move commands to transfer
	 * the cars from the 'from' order on track U
	 * to the 'to' order on track S
	 */
	public void arrange() throws EmptyStackException {
		Stack<Integer> from = new Stack<>();
		Stack<Integer> to = new Stack<>();
		Stack<Integer> temporary = new Stack<>();

		int index = 0;

		Set<Integer> set = new HashSet<>();
		for (int i = this.from.length - 1; i >= 0; i--) {
			from.push(this.from[i]);
		}

		while (index < this.from.length) {
			int value;
			if (!set.contains(this.to[index])) {
				while (temporary.size() == 0 || temporary.peek() != this.to[index]) {
					value = from.pop();
					temporary.push(value);
					display(value, unsortedString, temporaryString);
					set.add(value);
				}
			} else {
				while (temporary.size() == 0 || temporary.peek() != this.to[index]) {
					value = temporary.pop();
					from.push(value);
					display(value, temporaryString, unsortedString);
					set.remove(value);
				}
			}
			// Element trouvé
			value = temporary.pop();
			to.push(value);
			display(value, temporaryString, sortedString);
			set.remove(value);
			index++;
		}
	}

	/**
	 * Display the basic command (message) for moving
	 * the car 'car' from tack 'from' to track 'to'
	 */
	private void display(int car, String from, String to) {
		System.out.println("move car " + car + " from " + from + " to " + to);
	}

	////////////////////////////////////
	
	/**
	 * Checks if the array 'track' is a legal track
	 * i.e. is a permutation of [ 0, 1, 2,..., N-1 ]
	 * where N = track.length
	 */
	private static boolean notApermutation(int[] track) {
		boolean[] check = new boolean[track.length];
		for (int i = 0; i < track.length; i++)
			if ( track[i] < 0 || track[i] >= track.length || check[track[i]] )
				return true;
			else
				check[track[i]] = true;
		return false;
	}
	
	@SuppressWarnings("resource")
	private static int[] readTrack(Scanner input, String prompt) {
		List<Integer> list = new LinkedList<Integer>();
		System.out.print(prompt);
		Scanner scan = new Scanner(input.nextLine());
		while ( scan.hasNextInt() )
			list.add(scan.nextInt());
		return list.stream().mapToInt(i->i).toArray();
	}
	
    /**
     * An interactive main for testing.
     */	
	public static void main(String[] args) {
//		Scanner keyboard = new Scanner(System.in);
//		System.out.println("Welcome to the Train arrangement program\n");
//		while (true) {
//			int[] from = readTrack(keyboard,"\nEnter the 'from' track (just enter to exit): ");
//			if ( from.length == 0 )
//				break;
//			if ( notApermutation(from) ) {
//				System.out.println("bad track!");
//				continue;
//			}
//			int[] to = readTrack(keyboard,"Enter the 'to' track: ");
//			if ( notApermutation(to) ) {
//				System.out.println("bad track!");
//				continue;
//			}
//			if ( from.length != to.length ) {
//				System.out.println("the 'from' and 'to' tracks don't have the same size!");
//				continue;
//			}
//			try {
//				(new TrainManagement(from,to)).arrange();
//			}
//			catch (EmptyStackException ese) {
//				System.out.println("oops! EmptyStackException");
//			}
//		}
//		System.out.println("\nThank you for using the Train arrangement program");
//	}


		try {
			int[] from = {1, 3, 0, 2 };int[] to = {3, 2, 1, 0 };
			(new TrainManagement(from,to)).arrange();
		} catch(Exception e) { System.out.println(e); }
	}
}
