package GraphManagement;


import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.json.JSONObject;

public class GraphBuilder {

    private static Graph<Vertex, DefaultEdge> graph;

    public static Graph buildGraph(JSONObject jsonSource)
    {
        graph = new SimpleGraph<>(DefaultEdge.class);



        return graph;
    }

}
