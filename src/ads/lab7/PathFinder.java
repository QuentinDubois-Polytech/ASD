package ads.lab7;

import java.util.*;
import ads.graph.*;

/**
 * A class to find a path between two vertices in a graph (directed or undirected)
 */
public class PathFinder {
	
	private static Graph G;
	
	private static Set<Vertex> visited = new HashSet<>();
	
	/**
	 * Returns a path as from vertex 'u' to vertex 'v' in the graph 'G'
	 * as a list of vertices if such a path exists, the empty list otherwise
	 */
	public static List<Vertex> findPath(Graph G, Vertex u, Vertex v) {
		List<Vertex> path = new LinkedList<Vertex>();

		return path;
	}
	
	/**
	 * If there is a path from vertex 'u' to vertex 'v' in the graph, the
	 * vertices of this path are stored in the list 'path' and the method
	 * returns true. Else the list 'path' remains unchanged and the method
	 * returns false
	 */
	private static boolean findPath(Vertex u, Vertex v, List<Vertex> path) {
		if (u == v) {
			path.add(0, v);
			return true;
		}

		visited.add(u);
		for (Vertex w : G.adjacents(u)) {
			if (!visited.contains(w) && findPath(w, v, path)) {
				path.add(0, u);
				return true;
			}
		}

		return false;
	}
	
	public static void main(String[] s) {

		UnDiGraph G = GraphReader.U3;
		PathFinder.G = G;
		List<Vertex> path = new LinkedList<>();
		boolean b = findPath(G.getVertex("H"),G.getVertex("F"), path);
		System.out.println(b+" : "+path);
	}
}
