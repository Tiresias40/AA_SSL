package GraphManagement;

import org.jgrapht.Graph;

import java.util.Set;
import java.util.Vector;

public class ExactDominantSetSolver {

    public static Vector<Vertex> dominantSet = new Vector<>();

    public static boolean hasDominantSet(Graph g)
    {
        Set<Vertex> vertexSet = g.vertexSet();

        for(Vertex v1 : vertexSet)
        {
            for(Vertex v2 : vertexSet)
            {
                if(v1 == v2)
                    continue;
                if(g.containsEdge(v1, v2))
                    return false;
            }
        }

        return false;
    }
}
