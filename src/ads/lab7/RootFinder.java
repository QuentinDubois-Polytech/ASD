package ads.lab7;

import java.util.*;
import ads.graph.*;

/**
 * A class to find a root in a direct graph
 */
public class RootFinder {
	
	private static DiGraph G;
	private static Set<Vertex> visited;
	
	/**
	 * Returns a root of the graph 'G' if
	 * such root exists, null otherwise
	 */
	public static Vertex findRoot(DiGraph G) {
		return null;
	}
	
	/**
	 * Returns a root candidate
	 */
	private static Vertex candidate() {
		Vertex last = null;
		return last;
	}
	
	/**
	 * Visits all the reachable vertices from
	 * vertex 'u' and returns the number of
	 * those vertices
	 */
	private static int visit(Vertex u) {
		return 0;
	}
	
	public static void main(String[] s) {
		DiGraph G = GraphReader.D2;
		
		System.out.println(findRoot(G));
	}	
}
