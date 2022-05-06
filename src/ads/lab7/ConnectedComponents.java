package ads.lab7;

import java.util.*;
import ads.graph.*;

/**
 * A class to find connected components from an undirected graphs
 */
public class ConnectedComponents {
	
	private static Map<Vertex,Integer> cc; // the resulting map
	private static UnDiGraph G; // the undirected graph

	/**
	 * Returns the map of the connected components of 'G'
	 * If cc is the returned map, then, for all verticies u and v,
	 * cc.get(u) = cc.get(v) if and only if u and v are in the same
	 * connected component
	 */
	public static Map<Vertex,Integer> find(UnDiGraph G) {
		cc = new HashMap<>();
		ConnectedComponents.G = G;

		int number = 0;
		for (Vertex v : G.vertices()) {
			if (notNumbered(v)) {
				setComponent(v, ++number);
			}
		}

		return cc;
	}
	
	/**
	 * Set the component number to 'number' for 'u' and
	 * all the vertices reachable from u
	 */
	private static void setComponent(Vertex u, int number) {
		if (notNumbered(u)) {
			cc.put(u, number);
			G.adjacents(u).forEach(v -> setComponent(v, number));
		}
	}
	
	private static boolean notNumbered(Vertex u) {
		return ! cc.containsKey(u);
	}
	
	private static void setNumber(Vertex u, int number) {
		cc.put(u, number);
	}
	
	public static void main(String[] s) {
		
		Map<Vertex,Integer> cc = find(GraphReader.U1);
		System.out.println(cc);
		
		cc = find(GraphReader.U2);
		System.out.println(cc);
	}
}
