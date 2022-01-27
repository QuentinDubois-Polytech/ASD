package ads.lab1;

/**
 * A class for practicing recursion
 */
public class Recursion {

    ///// printing binary words

    /**
     * Print out all binary words of length k
     * Complexity: THETA(2^k)
     */
    public static void binary(int k) {
        binary("", k);
    }

    /**
     * Print out all binary words of length d
     * prefixed by s
     * Complexity: THETA(2^k)
     */
    private static void binary(String s, int d) {
        if (d == 0) {
            System.out.print(s + " ");
        } else {
            binary(s + "0", d - 1);
            binary(s + "1", d - 1);
        }
    }

    ///// printing A-B words

    /**
     * Print out all words made of
     * x letters 'A' and y letters 'B'
     * Complexity: THETA( C(x,x+y) = C(y,x+y) )
     */
    public static void words(int x, int y) {
        words(x, y, "");
    }

    /**
     * Print out all words made of
     * x letters 'A' and y letters 'B'
     * prefixed by s
     * Complexity: THETA( C(x,x+y) = C(y,x+y) )
     */
    private static void words(int x, int y, String s) {
        if (x == 0 && y == 0) {
            System.out.print(s + " ");
        } else {
            if (x != 0) {
                words(x - 1, y, s + "A");
            }

            if (y != 0) {
                words(x, y - 1, s + "B");
            }
        }
    }

    ///// printing all permutations of { 0, 1, 2, ..., n - 1 }

    /**
     * Print out all permutations of
     * (1, 2, 3, ..., n)
     * Complexity:
     */
    public static void permutations(int n) {
        int[] p = new int[n];
        for (int i = 0; i < p.length; i++)
            p[i] = i + 1;
        permutations(p, 0);
    }

    private static void permutationElement(int[] tab, int i, int j) {
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }

    /**
     * Print out the array p for all
     * permutations of (i, ..., n).
     * This method must NOT change the array p
     * (i.e. after the call, p is the same as
     * before the call).
     * Complexity: THETA( (n - i + 1)! )
     * where n = p.length
     */
    private static void permutations(int[] p, int i) {
        if (i < p.length) {
            for (int j = i; j < p.length; j++) {
                permutationElement(p, i, j);
                permutations(p, i + 1);
                permutationElement(p, i, j);
            }
        } else {
            print(p);
        }
    }

    /**
     * Prints out the content of the
     * array in the form of a permutation
     */
    private static void print(int[] p) {
        System.out.print('(');
        System.out.print(p[0]);
        for (int i = 1; i < p.length; i++)
            System.out.print("," + p[i]);
        System.out.print(") ");
    }

    ///// checks if a given value can be found by summing up items in an array

    /**
     * Check if N can be found by adding
     * some values taken inside A
     * Complexity: THETA(2^k)
     */
    public static boolean sum(int[] A, int N) {
        return sum(A, N, A.length);
    }

    /**
     * Check if N can be found by adding
     * some values taken inside A[0..k-1]
     * Complexity: THETA(2^k)
     */
    private static boolean sum(int[] A, int N, int k) {
        if (N == 0) {
            return true;
        }

        if (k == 0) {
            return false;
        }

        return sum(A, N, k - 1) || sum(A, N - A[k-1], k - 1);
    }

    public static void sans00(int x) {
        sans00(x, "");
        if (x > 0) {
            sans00(x - 1, "0");
        }
    }

    /**
     * Print out all binary words of length x
     * without '00'
     * Complexity: THETA( C(x) = C(x-1) + C(x-2) )
     */
    private static void sans00(int x, String s) {
        if (x == 0) {
            System.out.print(s + " ");
        } else if (x == 1) {
            System.out.print(s + "1 ");
        } else {
            sans00(x - 1, s + "1");
            sans00(x - 2, s + "10");
        }
    }
}
