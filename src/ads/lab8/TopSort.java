package ads.lab8;

import java.util.*;
import ads.graph.*;

/**
 * A class for topological sorting
 */
public class TopSort {
	
	/**
	 * Returns the list of vertices of 'G' in
	 * (one) topological order
	 */
	public static List<Vertex> sort1(DiGraph G) {
		Map<Vertex,Integer> inDegree = new HashMap<Vertex,Integer>();
		Queue<Vertex> queue = new LinkedList<Vertex>();
		List<Vertex> sorted = new LinkedList<Vertex>();

		for(Vertex v : G.vertices()) {
			int inDegreeV = G.inDegree(v);
			inDegree.put(v, inDegreeV);

			if (inDegreeV == 0) {
				queue.add(v);
			}
		}

		while (!queue.isEmpty()) {
			Vertex v = queue.remove();
			sorted.add(v);
			for (Vertex u : G.adjacents(v)) {
				inDegree.put(u, inDegree.get(u)-1);
				if (inDegree.get(u) == 0) {
					queue.add(u);
				}
			}
		}

		return sorted;
	}
	
	/**
	 * Returns the list of vertices of 'G' in
	 * (one) topological order
	 */
	public static List<Vertex> sort2(DiGraph G) {
		Set<Vertex> visited = new HashSet<Vertex>();
		List<Vertex> sorted = new LinkedList<Vertex>();

		for (Vertex v : G.vertices()) {
			if (!visited.contains(v)) {
				visit(G, v, visited, sorted);
			}
		}
		return sorted;
	}
	
	/**
	 * Visit the graph 'G' using DFS from vertex 'u' and add all the visited
	 * vertices in 'sorted' such that they appear in topological order
	 */
	private static void visit(DiGraph G, Vertex u, Set<Vertex> visited, List<Vertex> sorted) {
		visited.add(u);
		for (Vertex v : G.adjacents(u)) {
			if (!visited.contains(v)) {
					visit(G, v, visited, sorted);
			}
		}
		sorted.add(0, u);
	}

	/////////////// 
	
	public static void main(String[] s) {
		DiGraph G = GraphReader.DD4;
		System.out.println(TopSort.sort2(G));
	}	
}
