package graphManagement;

import org.jgrapht.graph.SimpleGraph;

import java.util.Vector;

public class Graph<V, E> extends SimpleGraph<Object, Object> {

	private Vector<Vertex> opponentsVertices;
	private Vector<Vertex> defendersVertices;

	public Graph(Class edgeClass) {
		super(edgeClass);
		opponentsVertices = new Vector<Vertex>();
		defendersVertices = new Vector<Vertex>();
	}

	/**
	 * Add vertex to corresponding set of the graph (defender/oppponent)
	 * 
	 * @param v : vertex to add
	 * @return boolean represent result of the operation
	 */
	public boolean addVertex(Vertex v) {
		Vertex vertex = (Vertex) v;
		if (vertex.isDefender())
			defendersVertices.add(vertex);
		else if (vertex.isOpponent())
			opponentsVertices.add(vertex);
		return super.addVertex(v);
	}

	/**
	 * Remove vertex to corresponding set of the graph (defender/opponent)
	 * 
	 * @param v : vertex to remove
	 * @return boolean represent result of the operation
	 */
	public boolean removeVertex(Vertex v) {
		if (v.getType().equals(VertexType.OPPONENT))
			opponentsVertices.remove(v);
		if (v.getType().equals(VertexType.DEFENDER))
			defendersVertices.remove(v);
		return super.removeVertex(v);
	}

	/**
	 * Obtain all opponent vertices
	 * 
	 * @return set of opponent vertices
	 */
	public Vector<Vertex> getOpponentVertices() {
		return opponentsVertices;
	}

	/**
	 * Obtain one opponent vertice
	 * 
	 * @param v : opponent need to obtain
	 * @return Vertex represent the oppponent vertice
	 */
	public Vertex getOpponentVertice(Vertex v) {
		return opponentsVertices.get(opponentsVertices.indexOf(v));
	}

	/**
	 * Obtain all defender vertices
	 * 
	 * @return set of defender vertices
	 */
	public Vector<Vertex> getDefendersVertices() {
		return defendersVertices;
	}

	/**
	 * Obtain one defender vertice
	 * 
	 * @param v : opponent need to obtain
	 * @return Vertex represent the defender vertice
	 */
	public Vertex getDefenderVertice(Vertex v) {
		return defendersVertices.get(defendersVertices.indexOf(v));
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("defenders").append(defendersVertices).append("\n");
		sb.append("opponents").append(opponentsVertices).append("\n");

		return sb.toString();
	}
}
