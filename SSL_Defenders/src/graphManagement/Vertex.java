package graphManagement;

import java.awt.Point;

public class Vertex {
	public Point.Double location;
	public VertexType type;

	public Vertex(double x, double y) {
		location = new Point.Double(x, y);
		type = VertexType.DEFENDER;
	}

	public Vertex(Point p) {
		this(p.getX(), p.getY());
	}

	public boolean isDefender() {
		return type == VertexType.DEFENDER;
	}

	public boolean isOpponent() {
		return type == VertexType.OPPONENT;
	}

	public void setDefender() {
		type = VertexType.DEFENDER;
	}

	public void setOpponent() {
		type = VertexType.OPPONENT;
	}

}
