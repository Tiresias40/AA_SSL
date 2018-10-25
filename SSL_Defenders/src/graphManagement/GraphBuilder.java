package graphManagement;

import IOManager.InputJSON;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.json.JSONObject;

import java.awt.geom.Point2D;
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
                vertexSet.add(new Vertex(xRunner, yRunner));

        }

        for(Point2D opponent : input.getOpponents())
        {
            Vertex v = new Vertex(opponent);
            if(vertexSet.contains(v))
            {
                vertexSet.get(vertexSet.indexOf(v)).type = VertexType.OPPONENT;
            }
            else
            {
                v.type = VertexType.OPPONENT;
                vertexSet.add(v);
            }
        }


        for(Vertex v : vertexSet)
            graph.addVertex(v);

        for(Vertex v : graph.vertexSet())
            for(Vertex v2 : graph.vertexSet())
            {
                if(v.equals(v2))
                    continue;
                if(v.isDefender() && v2.isDefender())
                    graph.addEdge(v, v2);
            }

        return graph;
    }

}
