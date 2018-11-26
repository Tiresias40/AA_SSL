package graphManagement;

import org.jgrapht.graph.SimpleGraph;

import java.util.Vector;

public class Graph<V,E> extends SimpleGraph {

    private Vector<Vertex> opponentsVertices;
    private Vector<Vertex> defendersVertices;

    public Graph(Class edgeClass) {
        super(edgeClass);
        opponentsVertices = new Vector<>();
        defendersVertices = new Vector<>();
    }

    @Override
    public boolean addVertex(Object v)
    {
        if(!v.getClass().equals(Vertex.class))
            return false;
        Vertex vertex = (Vertex)v;
        if(vertex.isDefender())
            defendersVertices.add(vertex);
        else if(vertex.isOpponent())
            opponentsVertices.add(vertex);


        return super.addVertex(v);
    }

    public boolean removeVertex(Vertex v)
    {
        if(v.isOpponent())
            opponentsVertices.remove(v);
        if(v.isDefender())
            defendersVertices.remove(v);
        return super.removeVertex(v);
    }


    public Vector<Vertex> getOpponentVertices() { return opponentsVertices; }
    public Vector<Vertex> getDefendersVertices() { return defendersVertices; }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("defenders").append(defendersVertices).append("\n");
        sb.append("opponents").append(opponentsVertices).append("\n");

        sb.append("Edges : ").append("\n");


        return sb.toString();
    }

}
