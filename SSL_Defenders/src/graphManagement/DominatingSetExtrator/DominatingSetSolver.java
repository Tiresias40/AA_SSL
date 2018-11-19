package graphManagement.DominatingSetExtrator;

import java.util.Vector;

public interface DominatingSetSolver {

    boolean hasDominatingSet(int maxSize);
    Vector getVertices();
}
