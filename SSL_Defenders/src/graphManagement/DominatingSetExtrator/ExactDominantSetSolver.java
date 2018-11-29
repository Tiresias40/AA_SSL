package graphManagement.DominatingSetExtrator;

import IOManager.InputJSON;
import graphManagement.Edge;
import graphManagement.Graph;
import graphManagement.GraphBuilder.BasicGraphBuilder;
import graphManagement.Vertex;

import java.util.Vector;

public class ExactDominantSetSolver implements DominatingSetSolverInterface{

    public Vector<Vertex> dominatingSet = new Vector<>();
    private Graph<Vertex, Edge> g;
    private Vector<Vertex> verticesSet = new Vector<>();
    private InputJSON input = null;

    private boolean trivialReturnValue;

    public ExactDominantSetSolver(Graph<Vertex, Edge> g){
        this.g = g;
        addAllVerticesToVertexSet(g);
    }

    @Override
    public boolean hasDominatingSet(int maxSize)
    {
        getJSONInstanceOrDie();
        //if(checkTrivialCases(maxSize))
        //    return trivialReturnValue;

        Vector<Vertex> currentSet = new Vector<>();
        return hasDominatingSetRecursive(maxSize, currentSet);
    }

    @Override
    public Vector<Vertex> getVertices() {
        return verticesSet;
    }

    private boolean hasDominatingSetRecursive(int size, Vector<Vertex> currentSet) {
		if (size <= 0)
			return false;

		Vector<Vertex> currentlyDominated = new Vector<>();
		for (Vertex chosen : currentSet) {
			Vector<Vertex> tmp = new Vector<>();

			for (Object e : g.edgesOf(chosen)) {
			    Vertex t = (Vertex) ((Edge) e).getTarget();
			    Vertex s = (Vertex) ((Edge) e).getSource();
			    if(!tmp.contains(t))
				    tmp.add(t);
			    if(!tmp.contains(s))
                    tmp.add(s);
			}

			for (Vertex tmpV : tmp) {
				if (!currentlyDominated.contains(tmpV))
					currentlyDominated.add(tmpV);
			}

			if (!currentlyDominated.contains(chosen))
				currentlyDominated.add(chosen);
		}

		if (currentlyDominated.size() == g.vertexSet().size() && BasicGraphBuilder.allIntersected(currentSet)) {
			dominatingSet.addAll(currentSet);
			return true;
		}

		for (Vertex v : verticesSet) {
			if (!currentSet.contains(v) && !currentlyDominated.contains(v)) {
				currentSet.add(v);
				if (hasDominatingSetRecursive(size - 1, currentSet))
					return true;
				currentSet.remove(v);
			}
		}

		return false;
	}

    private void trivialSet()
    {
        if(g == null)
            throw new RuntimeException("graph type Problem");
        Vector<Vertex> opponents = g.getOpponentVertices();
        for(Vertex opponent : opponents)
        {

        }
    }

    private void getJSONInstanceOrDie()
    {
        try {
            input = InputJSON.getInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private boolean checkTrivialCases(int size) {
        boolean existTrivialCase = false;
        if (size <= 0) {
            existTrivialCase = true;
            trivialReturnValue = false;
        }
        else if(size > input.opponentNumber())
        {
            existTrivialCase = true;
            trivialReturnValue = true;

            trivialSet();
        }
        return  existTrivialCase;
    }
    
    public Vector<Vertex> getDominatingSet() {
    	return dominatingSet;
    }


    private void addAllVerticesToVertexSet(Graph<Vertex, Edge> g)
    {
        verticesSet.addAll(g.getDefendersVertices());
    }
}
