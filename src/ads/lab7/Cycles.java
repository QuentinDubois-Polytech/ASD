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
		visited = new HashSet<>();

		for (Vertex v : G.vertices()) {
			if (! visited.contains(v)) {
				if (hasCycle(v, null)) {
					return true;
				}
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
		visited.add(u);
		for (Vertex v : G.adjacents(u)) {
			if (! visited.contains(v)) {
				if (v != from) {
					if (hasCycle(v, u)) {
						return true;
					}
				}
			} else if (v != from) {
				return true;
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
			if (status(v).equals(Status.UnDiscovered)) {
				if (hasCycle(v)) {
					return true;
				}
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
		setStatus(u, Status.InProgress);
		for (Vertex v : G.adjacents(u)) {
			if (status(v).equals(Status.UnDiscovered)) {
				if (hasCycle(v)) {
					return true;
				}
			} else if (status(v).equals(Status.InProgress)) {
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
