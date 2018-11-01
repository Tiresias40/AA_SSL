package graphManagement.DominatingSetExtrator;

import IOManager.InputJSON;
import graphManagement.Vertex;
import org.jgrapht.Graph;

import java.util.ArrayList;
import java.util.Vector;

public class ExactDominantSetSolver {

    public Vector<Vertex> dominatingSet = new Vector<>();
    private Graph g;

    public ExactDominantSetSolver(Graph g){
        this.g = g;
    }

    public boolean hasDominatingSet(int maxSize)
    {
        if(maxSize <= 0)
            return false;

        InputJSON input = null;
        try {
            input = InputJSON.getInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

        if(maxSize >= input.opponentNumber())
        {
            trivialSet(maxSize);
            return true;
        }


        Vector<Vertex> verticesSet = new Vector<>();
        verticesSet.addAll(g.vertexSet());

        Vector<Vertex> currentSet = new Vector<>();

        for(Vertex v : verticesSet)
        {
            if(v.isOpponent())
                continue;

            currentSet.add(v);
            if(hasDominatingSetRecursive(maxSize-1, currentSet))
                return true;
            currentSet.remove(v);
        }

        return false;
    }

    private boolean hasDominatingSetRecursive(int size, Vector<Vertex> currentSet)
    {
        if(size <= 0)
            return false;

        Vector<Vertex> currentlyDominated = new Vector<>();
        for(Vertex chosen : currentSet)
        {
            Vector<Vertex> tmp = new Vector<>();
            tmp.addAll(g.edgesOf(chosen));

            for(Vertex tmpV : tmp)
            {
                if(currentlyDominated.contains(tmpV))
                    continue;
                currentlyDominated.add(tmpV);
            }
        }

        return false;
    }

    private void trivialSet(int size)
    {

    }
}
