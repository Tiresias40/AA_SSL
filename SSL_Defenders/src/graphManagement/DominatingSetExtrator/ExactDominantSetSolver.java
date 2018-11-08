package graphManagement.DominatingSetExtrator;

import IOManager.InputJSON;
import graphManagement.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Vector;

public class ExactDominantSetSolver {

	public Vector<Vertex> dominatingSet = new Vector<>();
	private Graph<Vertex, DefaultEdge> g;
	private Vector<Vertex> verticesSet = new Vector<>();

	public ExactDominantSetSolver(Graph<Vertex, DefaultEdge> g) {
		this.g = g;
		verticesSet.addAll(g.vertexSet());
	}

	public boolean hasDominatingSet(int maxSize) {
		if (maxSize <= 0)
			return false;

		InputJSON input = null;
		try {
			input = InputJSON.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		if (maxSize >= input.opponentNumber()) {
			trivialSet(maxSize);
			return true;
		}

		Vector<Vertex> currentSet = new Vector<>();

		for (Vertex v : verticesSet) {
			if (v.isOpponent())
				continue;

			currentSet.add(v);
			if (hasDominatingSetRecursive(maxSize - 1, currentSet))
				return true;
			currentSet.remove(v);
		}

		return false;
	}

	private boolean hasDominatingSetRecursive(int size, Vector<Vertex> currentSet) {
		if (size <= 0)
			return false;

		Vector<Vertex> currentlyDominated = new Vector<>();
		for (Vertex chosen : currentSet) {
			Vector<Vertex> tmp = new Vector<>();
			tmp.addAll(g.edgesOf(chosen));

			for (Vertex tmpV : tmp) {
				if (!currentlyDominated.contains(tmpV))
					currentlyDominated.add(tmpV);
			}

			if (!currentlyDominated.contains(chosen))
				currentlyDominated.add(chosen);
		}

		if (currentlyDominated.size() == g.vertexSet().size()) {
			dominatingSet.addAll(currentSet);
			return true;
		}

		for (Vertex v : verticesSet) {
			if (currentSet.contains(v))
				continue;
			currentSet.add(v);
			if (hasDominatingSetRecursive(size - 1, currentSet))
				return true;
			currentSet.remove(v);
		}

		return false;
	}

	private void trivialSet(int size) {
		// ajouter un voisin de chaque opponent dans le dominatingSet
	}
}
