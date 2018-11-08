package graphManagement.GraphBuilder;

import IOManager.InputJSON;
import graphManagement.Edge;
import graphManagement.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;

import java.awt.geom.Point2D;
import java.util.Vector;

public class BasicGraphBuilder {

    private static Graph<Vertex, Edge> graph = new SimpleGraph<Vertex, Edge>(Edge.class);

    private static Vector<Vertex> defendersVertexSet = new Vector<>();
    private static Vector<Vertex> opponentsVertexSet = new Vector<>();
    private static InputJSON inputValues;

    private BasicGraphBuilder(){}


    public static Graph buildBasicGraph(InputJSON input)
    {
        inputValues = input;

        createVertices();
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


    private static void createVertices()
    {
        createDefendersVertices();
        createOpponentVertices();
    }

    private static void createDefendersVertices()
    {
        double xRunner = inputValues.getFieldLimits().get(0).getX();
        double xBorder = inputValues.getFieldLimits().get(1).getX();

        double yRunner = inputValues.getFieldLimits().get(0).getY();
        double yBorder = inputValues.getFieldLimits().get(1).getY();

        for(;xRunner < xBorder; xRunner += inputValues.getPosStep())
            for (; yRunner < yBorder; yRunner += inputValues.getPosStep())
                defendersVertexSet.add(new Vertex(xRunner, yRunner));
    }

    private static void createOpponentVertices()
    {
        for(Point2D opponentPos : inputValues.getOpponents()) {
            Vertex v = new Vertex(opponentPos);
            if (defendersVertexSet.contains(v))
                defendersVertexSet.remove(v);
            v.setOpponent();
            opponentsVertexSet.add(v);
        }
    }


    private static boolean intersect(Vertex opponent, Vertex defender)
    {

        return false;
    }




}
