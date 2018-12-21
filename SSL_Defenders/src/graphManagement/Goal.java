package graphManagement;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Goal {
	private ArrayList<Point2D.Double> limits;
	private Point2D.Double direction;

	public Goal(ArrayList<Point2D.Double> limits, Point2D.Double direction) {
		this.limits = new ArrayList<>();
		for (Point2D.Double lim : limits) {
			this.limits.add((Point2D.Double) lim.clone());
		}
		this.direction = direction;
	}

	/**
	 * Obtain limits of goal
	 * 
	 * @return collection of Point2D.Double represent the goal limits
	 */
	public ArrayList<Point2D.Double> getGoalLimits() {
		return limits;
	}

	/**
	 * Obtain direction of goal
	 * 
	 * @return Point2D.Double represent direction
	 */
	public Point2D.Double getDirection() {
		return direction;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("limits : ").append(limits).append("\n");
		sb.append("direction : ").append(direction).append("\n");

		return sb.toString();
	}
}
