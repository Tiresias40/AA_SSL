package graphManagement.GraphBuilder;

import IOManager.InputJSON;
import graphManagement.Edge;
import graphManagement.Graph;
import graphManagement.Vertex;

import java.awt.Point;
import java.util.Vector;

public class BasicGraphBuilder {

	protected static Graph<Vertex, Edge> graph = new Graph<>(Edge.class);

	protected static Vector<Vertex> defendersVertexSet = new Vector<>();
	protected static Vector<Vertex> opponentsVertexSet = new Vector<>();
	protected static InputJSON inputValues;


	public static Graph<Vertex, Edge> buildGraph(InputJSON input) {
		inputValues = input;

		createVertices();
		setGraphVertices();
		setGraphEdges();

		return graph;
	}

	protected static void setGraphEdges() {
        setOpponentsDefendersEdges();
        setDefendersClique();
	}

	protected static void setGraphVertices() {
		for (Vertex defender : defendersVertexSet)
			graph.addVertex(defender);
		for (Vertex opponent : opponentsVertexSet)
			graph.addVertex(opponent);
	}

	protected static void setDefendersClique() {
		for (Vertex defender1 : defendersVertexSet)
			for (Vertex defender2 : defendersVertexSet)
				if (defender1 != defender2)
					graph.addEdge(defender1, defender2);
	}

	protected static void setOpponentsDefendersEdges() {
		for (Vertex opponent : opponentsVertexSet)
			for (Vertex defender : defendersVertexSet)
				if (intersect(opponent, defender))
					graph.addEdge(opponent, defender);
				else
				    graph.removeVertex(defender);
	}

	protected static void createVertices() {
		createDefendersVertices();
		createOpponentVertices();
	}

	protected static void createDefendersVertices() {
		double xRunner = inputValues.getFieldLimits().get(0).getX();
		double xBorder = inputValues.getFieldLimits().get(1).getX();

		double yRunner = inputValues.getFieldLimits().get(0).getY();
		double yBorder = inputValues.getFieldLimits().get(1).getY();

		for (; xRunner < xBorder; xRunner += inputValues.getPosStep())
			for (; yRunner < yBorder; yRunner += inputValues.getPosStep())
				defendersVertexSet.add(new Vertex(xRunner, yRunner));
	}

	protected static void createOpponentVertices() {
		for (Point opponentPos : inputValues.getOpponents()) {
			Vertex v = new Vertex(opponentPos);
			if (defendersVertexSet.contains(v))
				defendersVertexSet.remove(v);
			else {
				v.setOpponent();
				opponentsVertexSet.add(v);
			}
		}
	}

	protected static boolean intersect(Vertex opponent, Vertex defender) {

		return false;
	}

}
