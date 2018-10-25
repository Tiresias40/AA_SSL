package graphManagement;

import java.awt.geom.Point2D;

public class Vertex {
    public Point2D.Double location;
    public VertexType type;

    public Vertex(double x, double y)
    {
        location = new Point2D.Double(x, y);
        type = VertexType.NONE;
    }
}
