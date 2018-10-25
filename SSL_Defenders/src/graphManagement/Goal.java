package graphManagement;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Goal {
	private ArrayList<Point2D> limits;
	private Point2D direction;
	
	public Goal (ArrayList<Point2D> limits, Point2D direction) {
		for (Point2D lim : limits) {
			this.limits.add((Point2D) lim.clone());
		}
		this.direction = direction;
	}
}
