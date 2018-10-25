package GraphManagement;


import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.json.JSONObject;

import java.util.Vector;

public class GraphBuilder {

    private static Graph<Vertex, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

    public static Graph buildGraph(JSONObject jsonSource)
    {

        Vector<Vertex> vertexSet = new Vector<>();



        return graph;
    }

}
