package graphManagement.GraphBuilder;

import IOManager.InputJSON;
import Utils.Geometry;
import graphManagement.Edge;
import graphManagement.Goal;
import graphManagement.Graph;
import graphManagement.Vertex;

import java.awt.Point;
import java.awt.geom.Point2D;
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

    protected static void createVertices() {
        createDefendersVertices();
        createOpponentVertices();
    }

    protected static void createDefendersVertices() {
        double xRunner = inputValues.getFieldLimits().get(0).getX();
        double xBorder = inputValues.getFieldLimits().get(0).getY();

        double yRunner = inputValues.getFieldLimits().get(1).getX();
        double yBorder = inputValues.getFieldLimits().get(1).getY();

        for (; xRunner < xBorder; xRunner += inputValues.getPosStep())
        {
            xRunner = Math.floor(xRunner*10)/10;

            for (yRunner = inputValues.getFieldLimits().get(1).getX(); yRunner < yBorder; yRunner += inputValues.getPosStep())
            {
                yRunner = Math.floor(yRunner*10)/10;
                defendersVertexSet.add(new Vertex(xRunner, yRunner));
            }
        }


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

	}


	protected static boolean intersect(Vertex opponent, Vertex defender) {
        double angle = 0;
        double PI_2 = Math.PI *2;
        while(angle < PI_2)
        {
            double x = opponent.location.getX() + Math.sin(angle);
            double y = opponent.location.getY() + Math.cos(angle);

            for(Goal g : inputValues.getGoals())
            {
                Point.Double gp1 = new Point.Double(g.getGoalLimits().get(0).getX(),g.getGoalLimits().get(0).getY());
                Point.Double gp2 = new Point.Double(g.getGoalLimits().get(1).getX(),g.getGoalLimits().get(1).getY());
                Point.Double crossLine = Geometry.segmentLintIntersection(gp1, gp2, new Point2D.Double(x, y), opponent.location);
                if(crossLine == null)
                    continue;
                if(Geometry.circleLineIntersection(opponent.location, crossLine, defender.location, inputValues.getRobotRadius()) != null)
                    return true;
            }

            angle += inputValues.getThetaStep();
        }
		return false;
	}

}
