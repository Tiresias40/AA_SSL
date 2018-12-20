package graphManagement.GraphBuilder;

import IOManager.InputJSON;
import Utils.Geometry;
import graphManagement.Edge;
import graphManagement.Goal;
import graphManagement.Graph;
import graphManagement.Vertex;
import graphManagement.VertexType;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class BasicGraphBuilder {

	private static Graph<Vertex, Edge> graph = new Graph<Vertex, Edge>(Edge.class);

	private static Vector<Vertex> defendersVertexSet = new Vector<Vertex>();
	private static Vector<Vertex> opponentsVertexSet = new Vector<Vertex>();
	private static InputJSON inputValues;

	private static int i = 0;

	/* Initialize graph with json data
	 * input : json file 
	 * mode : 0 = brute-force algorithm / 1 = greedy algorithm
	 */
	public static Graph<Vertex, Edge> buildGraph(InputJSON input, int mode) {
		inputValues = input;

		createVertices();
		setGraphVertices();
		setGraphEdges();

		return graph;
	}

	// Create all vertex
	private static void createVertices() {
        createOpponentVertices();
	    createDefendersVertices();
	}

	private static void createDefendersVertices() {
		double xRunner = inputValues.getFieldLimits().get(0).getX();
		double xBorder = inputValues.getFieldLimits().get(0).getY();

		double yRunner;
		double yBorder = inputValues.getFieldLimits().get(1).getY();

		for (; xRunner < xBorder; xRunner += inputValues.getPosStep()) {
			for (yRunner = inputValues.getFieldLimits().get(1).getX(); yRunner < yBorder; yRunner += inputValues
					.getPosStep()) {
				Vertex v = new Vertex(xRunner, yRunner);
				boolean isCandidate = false;
				for(Vertex opponent : opponentsVertexSet){
				    if(!v.hasSameLocation(opponent) && intersect(opponent, v)) {
                        isCandidate = true;
                        break;
                    }
                }
                if(isCandidate){
                    if(inputValues.hasGoalKeeper() && isInGoalKeeperArea(v))
                        v.setType(VertexType.GOAL_KEEPER);
                    defendersVertexSet.add(v);
                }

			}
		}
	}

	private static void createOpponentVertices() {
		for (Point2D.Double opponentPos : inputValues.getOpponents())
            opponentsVertexSet.add(new Vertex(opponentPos, VertexType.OPPONENT));
	}

	private static void setGraphEdges() {
		// Create edge between opponent and defender if defender block opponent
		setOpponentsDefendersEdges();
		// Delete no blocking defenders
		for (Vertex v : new Vector<Vertex>(defendersVertexSet)) {
			if (v.getDegree() == 0) {
				defendersVertexSet.remove(v);
				graph.removeVertex(v);
			}
		}
		// Create edge between defenders
		setDefendersClique();
	}

	private static void setGraphVertices() {
		for (Vertex defender : defendersVertexSet)
			graph.addVertex(defender);
		for (Vertex opponent : opponentsVertexSet)
			graph.addVertex(opponent);
	}

	private static void setDefendersClique() {
		for (Vertex defender1 : defendersVertexSet)
			for (Vertex defender2 : defendersVertexSet)
				if (defender1 != defender2)
					graph.addEdge(defender1, defender2);
	}

	private static void setOpponentsDefendersEdges() {
		for (Vertex opponent : opponentsVertexSet)
			for (Vertex defender : defendersVertexSet)
				if (intersect(opponent, defender)) {
					graph.addEdge(opponent, defender);

					opponent.setDegree(opponent.getDegree() + 1);
					defender.setDegree(defender.getDegree() + 1);
				}
	}

	private static boolean intersect(Vertex opponent, Vertex defender) {
		double angle = 0;
		double PI_2 = Math.PI * 2;
		while (angle < PI_2) {
			double x = opponent.location.getX() + Math.sin(angle);
			double y = opponent.location.getY() + Math.cos(angle);

			for (Goal g : inputValues.getGoals()) {
				Point.Double gp1 = new Point.Double(g.getGoalLimits().get(0)
						.getX(), g.getGoalLimits().get(0).getY());
				Point.Double gp2 = new Point.Double(g.getGoalLimits().get(1)
						.getX(), g.getGoalLimits().get(1).getY());
				Point.Double crossLine = Geometry.segmentLinetIntersection(gp1,
						gp2, new Point2D.Double(x, y), opponent.location);
				if (crossLine == null)
					continue;
                if (Geometry.circleLineIntersection(opponent.location,
						crossLine, defender.location,
						inputValues.getRobotRadius()) != null)
                    return true;

			}

			angle += inputValues.getThetaStep();
		}
		return false;
	}

	public static boolean allIntersected(Vector<Vertex> defenders) {
        //to check we didn't just continued on every single point
		double angle;
        final double PI_2 = Math.PI * 2;
        for(Vertex opponent : graph.getOpponentVertices())
        {
            angle = 0;
            while (angle < PI_2) {
                double x = opponent.location.getX() + Math.sin(angle);
                double y = opponent.location.getY() + Math.cos(angle);

                for (Goal g : inputValues.getGoals()) {
                    Point.Double gp1 = new Point.Double(g.getGoalLimits().get(0)
                            .getX(), g.getGoalLimits().get(0).getY());
                    Point.Double gp2 = new Point.Double(g.getGoalLimits().get(1)
                            .getX(), g.getGoalLimits().get(1).getY());
                    Point.Double crossLine = Geometry.segmentLinetIntersection(gp1,
                            gp2, new Point2D.Double(x, y), opponent.location);
                    if (crossLine == null)
                        continue;
                    boolean tmpValue = false;
                    for(Vertex defender : defenders)
                    {
                        if(Geometry.circleLineIntersection(opponent.location,
                                crossLine, defender.location, inputValues.getRobotRadius()) != null)
                            tmpValue = true;


                    }

                    if(!tmpValue)
                    	return false;

                }
                angle += inputValues.getThetaStep();
            }
        }
        return true;
    }

	public static HashMap<Vertex, HashMap<Double, Boolean>> intersectCount(Vertex opponent, Vertex defender) {
        double angle = 0;
        double PI_2 = Math.PI * 2;
        HashMap<Vertex, HashMap<Double, Boolean>> intersections = new HashMap<>();

        while (angle < PI_2) {
            double x = opponent.location.getX() + Math.sin(angle);
            double y = opponent.location.getY() + Math.cos(angle);

            for (Goal g : inputValues.getGoals()) {
                Point.Double gp1 = new Point.Double(g.getGoalLimits().get(0)
                        .getX(), g.getGoalLimits().get(0).getY());
                Point.Double gp2 = new Point.Double(g.getGoalLimits().get(1)
                        .getX(), g.getGoalLimits().get(1).getY());
                Point.Double crossLine = Geometry.segmentLinetIntersection(gp1,
                        gp2, new Point2D.Double(x, y), opponent.location);
                if (crossLine == null)
                    continue;
                if (Geometry.circleLineIntersection(opponent.location,
                        crossLine, defender.location,
                        inputValues.getRobotRadius()) != null)
                {
                    if(!intersections.containsKey(opponent))
                        intersections.put(opponent, new HashMap<>());
                    intersections.get(opponent).put(angle, true);
                }

            }

            angle += inputValues.getThetaStep();
        }
        return intersections;
	}

    public static HashMap<Vertex, HashMap<Double, Boolean>> intersectCount(Vector<Vertex> opponents, Vertex defender)
    {
        HashMap<Vertex, HashMap<Double, Boolean>> intersections = new HashMap<>();
        for(Vertex opponent : opponents)
            intersections.putAll(intersectCount(opponent, defender));
        return intersections;
    }

    private static boolean isInGoalKeeperArea(Vertex v)
    {
        return     v.location.getX() >= inputValues.getGoalkeeper_area().get(0).getX()
                && v.location.getX() <= inputValues.getGoalkeeper_area().get(0).getY()
                && v.location.getY() >= inputValues.getGoalkeeper_area().get(1).getX()
                && v.location.getY() <= inputValues.getGoalkeeper_area().get(1).getY();

    }
}
