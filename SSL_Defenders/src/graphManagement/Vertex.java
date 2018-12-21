package graphManagement;

import IOManager.InputJSON;

import java.awt.geom.Point2D;

public class Vertex {
	public Point2D.Double location;
	private VertexType type;
	private boolean intercept;

	// ----- CONSTRUCTOR ---------------
	public Vertex(double x, double y) {
		this.location = new Point2D.Double(x, y);
		this.type = VertexType.DEFENDER;
		this.intercept = false;
	}

	public Vertex(Point2D.Double p) {
		this(p.getX(), p.getY());
	}

	public Vertex(Point2D.Double p, VertexType type) {
		this(p);
		this.type = type;
	}

	// ----- GETTER AND SETTERS ---------------
	public VertexType getType() {
		return type;
	}

	public void setType(VertexType type) {
		this.type = type;
	}

	public boolean isIntercept() {
		return intercept;
	}

	public void setIntercept(boolean intercept) {
		this.intercept = intercept;
	}

	// ----- METHODS ---------------
	/**
	 * Check if the vertex is a defender
	 * 
	 * @return boolean represent if vertex type is DEFENDER
	 */
	public boolean isDefender() {
		return (this.getType() == VertexType.DEFENDER);
	}

	/**
	 * Check if the vertex is a opponent
	 * 
	 * @return boolean represent if vertex type is OPPPONENT
	 */
	public boolean isOpponent() {
		return (this.getType() == VertexType.OPPONENT);
	}

	/**
	 * Check if the vertex is a goal keeper
	 * 
	 * @return boolean represent if vertex type is GOAL_KEEPER
	 */
	public boolean isGoalKeeper() {
		return (this.getType() == VertexType.GOAL_KEEPER);
	}

	/**
	 * Change type of the vertex to DEFENDER
	 */
	public void setDefender() {
		type = VertexType.DEFENDER;
	}

	/**
	 * Change type of the vertex to OPPONENT
	 */
	public void setOpponent() {
		this.type = VertexType.OPPONENT;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Type : ").append(type).append("\n");
		sb.append("Location : ").append(location).append("\n");

		return sb.toString();
	}

	/**
	 * Check if the vertex is on same location with the vertex in parameter
	 * 
	 * @param other : other vertex to compare location
	 * @return boolean represent if the vertex is on the same location
	 */
	public boolean hasSameLocation(Vertex other) {
		double dist = Math.pow(location.getX() - other.location.getX(), 2)
				+ Math.pow(location.getY() - other.location.getY(), 2);
		dist = Math.sqrt(dist);
		double minDist = InputJSON.getInstance().getRobotRadius() * 2;
		return dist < minDist;
	}

	/**
	 * Check is the vertex respect the minDistance to other vertex
	 * 
	 * @param other : other vertex to check distance
	 * @param minDistance : minimal distance to respect with other vertex
	 * @return boolean represent if the vertex respect the minimal distance
	 */
	public boolean isAwayEnough(Vertex other, double minDistance) {
		double dist = Math.pow(location.getX() - other.location.getX(), 2)
				+ Math.pow(location.getY() - other.location.getY(), 2);
		dist = Math.sqrt(dist);
		minDistance += InputJSON.getInstance().getRobotRadius() * 2;
		return dist > minDistance;
	}

}
