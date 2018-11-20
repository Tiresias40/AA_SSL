package graphManagement;

import java.awt.Point;
import java.util.ArrayList;

public class Goal {
	private ArrayList<Point> limits;
	private Point direction;

	public Goal(ArrayList<Point> limits, Point direction) {
		this.limits = new ArrayList<>();
		for (Point lim : limits) {
			this.limits.add((Point) lim.clone());
		}
		this.direction = direction;
	}

	public ArrayList<Point> getGoalLimits() {
		return limits;
	}

	public Point getDirection() {
		return direction;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("limits : ").append(limits).append("\n");
		sb.append("direction : ").append(direction).append("\n");

		return sb.toString();
	}
}
