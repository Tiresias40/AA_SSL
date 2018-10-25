package GraphManagement;


import IOManager.InputJSON;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.json.JSONObject;

import java.util.Vector;

public class GraphBuilder {

    private static Graph<Vertex, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

    public static Graph buildBasicGraph(InputJSON input)
    {

        Vector<Vertex> vertexSet = new Vector<>();

        double xRunner = input.getFieldLimits().get(0).getX();
        double xBorder = input.getFieldLimits().get(1).getX();

        double yRunner = input.getFieldLimits().get(0).getY();
        double yBorder = input.getFieldLimits().get(1).getY();

        for(;xRunner < xBorder; xRunner += input.getPosStep())
        {
            for(;yRunner < yBorder; yRunner += input.getPosStep())
            {
                vertexSet.add(new Vertex(xRunner, yRunner));
            }
        }

        return graph;
    }

}
