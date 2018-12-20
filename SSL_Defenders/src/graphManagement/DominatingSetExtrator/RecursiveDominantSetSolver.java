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
    private int MaxSize;
    Vector<Vertex> bestSet;
    int bestSize;
    int maxSize;

    public boolean hasDominatingSet(int maxSize) {
        getJSONInstanceOrDie();
        System.out.println("Beginning recursive solving");
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
                        System.out.println(bestSet);
                        return true;
                    }
                    currentSet.remove(v);
                }
            }
        } else {
            if (hasDominatingSetRecursive(maxSize, currentSet)) {
                dominatingSet.addAll(bestSet);
                System.out.println(bestSet);
                return true;
            }
        }

        return false;
    }


    private boolean hasDominatingSetRecursive(int size, Vector<Vertex> currentSet)
    {

        //on a déjà mieux
        if(size < 0)
            return false;
        if(size <= bestSize)
            return true;


        for(Vertex n : verticesSet)
        {
            if(currentSet.contains(n))
                continue;
            boolean cont = false;
            double minDist = InputJSON.getInstance().getMinDist();

            for(Vertex v : currentSet)
                if(!v.isAwayEnough(n, minDist))
                    cont = true;
            if(cont)
                continue;
            currentSet.add(n);
            if(BasicGraphBuilder.allIntersected(currentSet))
            {
                bestSize = size;
                bestSet.clear();
                bestSet.addAll(currentSet);
            }
            else
                hasDominatingSetRecursive(size-1, currentSet);
            currentSet.remove(n);
        }

        if(bestSize > 0)
            return true;
        return false;

    }


    protected boolean hasDominatingSetRecursiveV1(int size, Vector<Vertex> currentSet) {
        if (size < 0)
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

        if (BasicGraphBuilder.allIntersected(currentSet) && currentSet.size() < dominatingSet.size()) {
            dominatingSet.addAll(currentSet);
            return true;
        }


        for (Vertex v : verticesSet) {
            if(input.hasGoalKeeper())
                if(v.isGoalKeeper())
                    continue;
            if(!currentSet.contains(v)){
                boolean awayEnough = true;
                if(InputJSON.getInstance().getMinDist() > 0)
                {
                    for(Vertex v2 : currentSet)
                        if(!v.isAwayEnough(v2, InputJSON.getInstance().getMinDist()))
                            awayEnough = false;
                    for(Vertex v2 : g.getOpponentVertices())
                        if(!v.isAwayEnough(v2, InputJSON.getInstance().getMinDist()))
                            awayEnough = false;
                }

                if(awayEnough)
                {
                    currentSet.add(v);
                    if (hasDominatingSetRecursive(size - 1, currentSet))
                        return true;
                    currentSet.remove(v);
                }
            }
        }

        return false;
    }

}
