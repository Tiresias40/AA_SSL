package graphManagement.DominatingSetExtrator;

import IOManager.InputJSON;
import graphManagement.Edge;
import graphManagement.Graph;
import graphManagement.GraphBuilder.BasicGraphBuilder;
import graphManagement.Vertex;

import java.util.Vector;

public class RecursiveDominantSetSolver extends ExactDominantSetSolver {
	public RecursiveDominantSetSolver(Graph<Vertex, Edge> g) {
		super(g);
	}

	Vector<Vertex> bestSet;
	int bestSize;
	int maxSize;

	public boolean hasDominatingSet(int maxSize) {
		getJSONInstanceOrDie();
		bestSet = new Vector<>();

		bestSize = 0;
		this.maxSize = maxSize;
		Vector<Vertex> currentSet = new Vector<>();
		if (input.hasGoalKeeper()) {
			for (Vertex v : verticesSet) {
				if (v.isGoalKeeper()) {
					currentSet.add(v);
					if (hasDominatingSetRecursive(maxSize - 1, currentSet)) {
						dominatingSet.addAll(bestSet);
						return true;
					}
					currentSet.remove(v);
				}
			}
		} else {
			if (hasDominatingSetRecursive(maxSize, currentSet)) {
				dominatingSet.addAll(bestSet);
				return true;
			}
		}
		return false;
	}

	private boolean hasDominatingSetRecursive(int size,
			Vector<Vertex> currentSet) {
		if (size < 0)
			return false;
		if (size <= bestSize)
			return true;

		for (Vertex n : verticesSet) {
			if (currentSet.contains(n))
				continue;
			boolean cont = false;
			double minDist = InputJSON.getInstance().getMinDist();

			for (Vertex v : currentSet)
				if (!v.isAwayEnough(n, minDist))
					cont = true;
			if (cont)
				continue;
			currentSet.add(n);
			if (BasicGraphBuilder.allIntersected(currentSet)) {
				bestSize = size;
				bestSet.clear();
				bestSet.addAll(currentSet);
			} else
				hasDominatingSetRecursive(size - 1, currentSet);
			currentSet.remove(n);
		}

		if (bestSize > 0)
			return true;
		return false;

	}
}
