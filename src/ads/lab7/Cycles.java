package ads.lab7;

import java.util.*;
import ads.graph.*;

/**
 * A class to find cycles in undirected and directed graphs
 */
public class Cycles {
	
	private static Graph G;
	
	/////////////// Cycle detection for undirected graphs
	
	private static Set<Vertex> visited;
	
	/**
	 * Returns true if the graph 'G' is cyclic
	 * false otherwise
	 */
	public static boolean hasCycle(UnDiGraph G) {
		Cycles.G = G;
		for (Vertex v : G.vertices()) {
			visited = new HashSet<>();
			if (hasCycle(v, null)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if a cycle was found by traversing
	 * the graph, coming from vertex from and going by vertex u
	 * Precondition: vertex 'from' is visited and vertex 'u' is
	 * not visited
	 */
	private static boolean hasCycle(Vertex u, Vertex from) {
		if (visited.contains(u)) {
			return true;
		}

		visited.add(u);
		for (Vertex v : G.adjacents(u)) {
			if (v != from) {
				if (hasCycle(v, u)) {
					return true;
				}
			}
		}

		return false;
	}
	
	/////////////// Cycle detection for directed graphs
	
	enum Status { UnDiscovered, InProgress, Completed } // status of vertex
	
	private static Map<Vertex,Status> vertexStatus; // to set the status of each vertex

	/**
	 * Returns true if the graph 'G' is cyclic
	 * false otherwise
	 */
	public static boolean hasCycle(DiGraph G) {
		vertexStatus = new HashMap<>();
		Cycles.G = G;
		for (Vertex v : G.vertices()) {
			if (hasCycle(v)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if a cycle was found by traversing
	 * the graph from vertex u
	 * Precondition: vertex 'u' is 'UnDiscovered'
	 */
	private static boolean hasCycle(Vertex u) {
		if (status(u).equals(Status.InProgress)) {
			return true;
		}

		setStatus(u, Status.InProgress);
		for (Vertex v : G.adjacents(u)) {
			if (hasCycle(v)) {
				return true;
			}
		}

		setStatus(u, Status.Completed);
		return false;
	}
	
	
	/**
	 * Returns the status of vertex 'u'
	 */
	private static Status status(Vertex u) {
		Status s = vertexStatus.get(u);
		if ( s == null )
			return Status.UnDiscovered;
		return s;
	}
	
	/**
	 * Sets the status of vertex 'u' to 's'
	 */	
	private static void setStatus(Vertex u, Status s) {
		vertexStatus.put(u,s);
	}
	
	/////////////// 
	
	public static void main(String[] s) {
		DiGraph G = GraphReader.D1;
		
		System.out.println(hasCycle(G));
	}
}
