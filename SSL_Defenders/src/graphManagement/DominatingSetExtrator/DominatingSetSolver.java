package graphManagement.DominatingSetExtrator;

import java.util.Vector;

import graphManagement.Vertex;

public interface DominatingSetSolver {

    boolean hasDominatingSet(int maxSize);
    Vector<Vertex> getDominatingSet();
    Vector<Vertex> getVertices();
}
