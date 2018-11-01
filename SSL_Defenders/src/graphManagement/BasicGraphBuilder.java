package graphManagement;

import IOManager.InputJSON;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.awt.geom.Point2D;
import java.util.Vector;

public class BasicGraphBuilder {

    private static Graph<Vertex, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

    private static Vector<Vertex> defendersVertexSet = new Vector<>();
    private static Vector<Vertex> opponentsVertexSet = new Vector<>();
    private static InputJSON inputValues;

    private BasicGraphBuilder(){}


    public static Graph buildBasicGraph(InputJSON input)
    {
        inputValues = input;
        double xRunner = input.getFieldLimits().get(0).getX();
        double xBorder = input.getFieldLimits().get(1).getX();

        double yRunner = input.getFieldLimits().get(0).getY();
        double yBorder = input.getFieldLimits().get(1).getY();

        for(;xRunner < xBorder; xRunner += input.getPosStep()) {
            for (; yRunner < yBorder; yRunner += input.getPosStep())
                defendersVertexSet.add(new Vertex(xRunner, yRunner));
        }

        for(Point2D opponent : input.getOpponents()) {
            Vertex v = new Vertex(opponent);
            if (defendersVertexSet.contains(v)) {
                defendersVertexSet.remove(v);
            } else {
                v.type = VertexType.OPPONENT;
                opponentsVertexSet.add(v);
            }
        }


        setGraphVertices();
        setGraphEdges();

        return graph;
    }


    private static void setGraphEdges()
    {
        setDefendersClique();
        setOpponentsDefendersEdges();
    }

    private static void setGraphVertices()
    {
        for(Vertex defender : defendersVertexSet)
            graph.addVertex(defender);
        for(Vertex opponent : opponentsVertexSet)
            graph.addVertex(opponent);
    }

    private static void setDefendersClique()
    {
        for(Vertex defender1: defendersVertexSet)
            for(Vertex defender2: defendersVertexSet)
                if(defender1 != defender2)
                    graph.addEdge(defender1, defender2);
    }

    private static void setOpponentsDefendersEdges()
    {
        for (Vertex opponent: opponentsVertexSet)
            for(Vertex defender: defendersVertexSet)
                if(intersect(opponent, defender))
                    graph.addEdge(opponent, defender);
    }

    private static boolean intersect(Vertex opponent, Vertex defender)
    {
        return false;
    }




}
