package graphManagement.DominatingSetExtrator;

import graphManagement.Edge;
import graphManagement.Graph;
import graphManagement.GraphBuilder.BasicGraphBuilder;
import graphManagement.Vertex;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class GreedyDominantSetSolver extends ExactDominantSetSolver {
    private HashMap<Vertex, HashMap<Double, Boolean>> intersectionsAlreadyTaken;
    private int bestNumberOfEntries;
    private HashMap<Vertex, HashMap<Double, Boolean>> bestForNow;

    public GreedyDominantSetSolver(Graph<Vertex, Edge> g)
    {
        super(g);
        intersectionsAlreadyTaken = new HashMap<>();
        bestNumberOfEntries = 0;
    }

    @Override
    public boolean hasDominatingSet(int maxSize) {
        Vector<Vertex> takenVertices = new Vector<>();
        while(maxSize > 0)
        {
            bestNumberOfEntries = 0;
            Vertex bestYet = null;
            HashMap<Vertex, HashMap<Double, Boolean>> tmp;
            for(Vertex n : verticesSet)
            {
                if(takenVertices.contains(n))
                    continue;
                int tmpInt = 0;
                tmp = BasicGraphBuilder.intersectCount(g.getOpponentVertices(), n);
                tmpInt = bestNumberOfEntries;
                checkNumberOfIntersections(tmp);
                if(tmpInt < bestNumberOfEntries)
                {
                    bestYet = n;
                }
            }
            System.out.println(bestYet);
            if(bestYet != null)
                takenVertices.add(bestYet);
            if(BasicGraphBuilder.allIntersected(takenVertices))
            {
                dominatingSet = takenVertices;
                return true;
            }
            maxSize--;
        }
        return false;
    }


    public void checkNumberOfIntersections(HashMap<Vertex, HashMap<Double, Boolean>> lastIntersections)
    {
        int numberOfValidEntries = 0;
        for(Map.Entry i : lastIntersections.entrySet())
        {
            if(!intersectionsAlreadyTaken.containsKey(i.getKey()))
                intersectionsAlreadyTaken.put((Vertex)i.getKey(), new HashMap<>());
            for(Map.Entry j : lastIntersections.get(i.getKey()).entrySet())
                if(!intersectionsAlreadyTaken.get(i.getKey()).containsKey(j.getKey()))
                    numberOfValidEntries ++;

        }
        if(numberOfValidEntries > bestNumberOfEntries)
        {
            bestNumberOfEntries = numberOfValidEntries;
            bestForNow = lastIntersections;
        }
    }
}
