package Utils;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Geometry {

    public static Point.Double segmentLintIntersection(Point.Double segmentP1, Point.Double segmentP2, Point.Double lineP1, Point.Double lineP2)
    {
        return segmentLintIntersection(new Line2D.Double(segmentP1, segmentP2), new Line2D.Double(lineP1, lineP2));
    }

    public static Point.Double segmentLintIntersection(Line2D segment, Line2D line)
    {
        double x1 = segment.getX1();
        double y1 = segment.getY1();
        double x2 = segment.getX2();
        double y2 = segment.getY2();
        double x3 = line.getX1();
        double y3 = line.getY1();
        double x4 = line.getX2();
        double y4 = line.getY2();
        double num = (x1-x3)*(y3-y4)-(y1-y3)*(x3-x4);
        double den = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);
        if(den == 0)
            return null;

        double t = num/den;
        if(t > 1 || t < 0)
            return null;
        return new Point2D.Double(x1+t*(x2-x1), y1+t*(y2-y1));
    }

    public static Point.Double circleLineIntersection(Point.Double segStart, Point.Double segEnd, Point.Double circleCenter, double radius)
    {
        return null;
    }
}
