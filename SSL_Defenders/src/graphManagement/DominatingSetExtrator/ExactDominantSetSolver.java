package graphManagement.DominatingSetExtrator;

import IOManager.InputJSON;
import graphManagement.Edge;
import graphManagement.Graph;
import graphManagement.Vertex;

import java.util.Vector;

public class ExactDominantSetSolver implements DominatingSetSolverInterface{

    public Vector<Vertex> dominatingSet = new Vector<>();
    private Graph<Vertex, Edge> g;
    private Vector<Vertex> verticesSet = new Vector<>();
    private InputJSON input = null;

    public ExactDominantSetSolver(Graph<Vertex, Edge> g){
        this.g = g;
        addAllVerticesToVertexSet(g);
    }

    @Override
    public Vector<Vertex> getVertices() {
        return verticesSet;
    }
    
    @Override
    public boolean hasDominatingSet(int maxSize) {
    	boolean ret = false;
    	boolean found = false;
    	
    	while (maxSize > 0) {
    		ret = hasDominatingSetRecursive(maxSize--);
    		if(ret == true)
    			found = ret;
    		if (ret == false)
    			return found;
    	}
    	
    	return found;
    }
    
    public boolean hasDominatingSetRecursive(int maxSize) {
    	// Init edge set with only edges of opponent-defender type
    	Vector<Edge> opponentDefendersEdgesSet = new Vector<Edge>();
    	for(Object e : g.edgeSet()) {
    		if(((Vertex) ((Edge) e).getSource()).isOpponent())
    			opponentDefendersEdgesSet.add((Edge) e);
    	}
    	
    	// Init dominating set with 3 first vertices
    	for(int i = 0 ; i < maxSize ; i++) {
			dominatingSet.add(g.getDefendersVertices().get(i));
		}
    	
    	boolean goodCombination = false;
    	// runs until all combinations have been tested or a good one has been found
    	while(true) {    		
    		for(Edge e : opponentDefendersEdgesSet) {
    			goodCombination = false;
    			for (Vertex v : dominatingSet) {
    				if(e.getTarget().equals(v)) {
    					goodCombination = true;
    					continue;
    				}
    			}
    			if(!goodCombination) {
    				continue;
    			}
    		}
    		if(goodCombination) {
    			return true;
    		}
    		
    		if(!calculateDominatingSet())
    			return false;
    	}
    }
    
    private boolean calculateDominatingSet() {
    	boolean ret = true;
    	
    	// We take the last element of dominating set to incr it
    	Vertex current = dominatingSet.remove(dominatingSet.size() - 1);
    	int indexCurrInDef = g.getDefendersVertices().indexOf(current);
    	
    	// if next index is out of range
    	if(indexCurrInDef + 1 > g.getDefendersVertices().size()) {
    		// if the set is empty, it means all combination have been tested
    		if(dominatingSet.isEmpty())
    			ret = false;
    		// We take the next element and test it
    		ret = calculateDominatingSet();
    		// if the last element of dominating set is equal to the one we were processing, it means we need to go higher in the combination to incr
    		if(g.getDefendersVertices().get(indexCurrInDef).equals(dominatingSet.lastElement())) 
    			ret = calculateDominatingSet();
    		// if the element we were processing is bigger than the last of dominating set
    		// we take the one just after the value of dominating set (we incr only by one)
    		int indexLastOfDSInDef = g.getDefendersVertices().indexOf(dominatingSet.lastElement());
    		if(indexCurrInDef > indexLastOfDSInDef + 1)
    			current = g.getDefendersVertices().get(indexLastOfDSInDef + 1);
    	}
    	// if all goes well, simple incr
    	else {
    		dominatingSet.add(g.getDefendersVertices().get(indexCurrInDef + 1));
    	}
    	
    	return ret;
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
    
    public Vector<Vertex> getDominatingSet() {
    	return dominatingSet;
    }


    private void addAllVerticesToVertexSet(Graph<Vertex, Edge> g)
    {
        verticesSet.addAll(g.getDefendersVertices());
    }
}
