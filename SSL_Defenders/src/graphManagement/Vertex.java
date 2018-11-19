package graphManagement;

import java.awt.Point;

public class Vertex {
	public Point.Double location;
	public VertexType type;
	private boolean isOnIntersection = false;

	public Vertex(double x, double y) {
		location = new Point.Double(x, y);
		type = VertexType.DEFENDER;
	}

	public Vertex(Point p) {
		this(p.getX(), p.getY());
	}

	public Vertex(Point p, VertexType type)
    {
        this(p);
        this.type = type;
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

	public boolean isOnIntersection() { return isOnIntersection; }
	public void intersectAShoot() { isOnIntersection = true; }

}
