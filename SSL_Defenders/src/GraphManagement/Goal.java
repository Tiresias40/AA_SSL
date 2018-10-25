
public class Goal {
	private ArrayList<Point2D> limits;
	private Point2D direction;
	
	public Goal (ArrayList<Point2D> limits, Point2D direction) {
		for (Point2D lim : limits) {
			this.limits.add(lim.clone());
		}
		this.direction = direction;
	}
}
