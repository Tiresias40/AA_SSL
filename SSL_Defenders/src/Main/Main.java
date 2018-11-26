package Main;

import IOManager.InputJSON;
import IOManager.OutputJSON;
import graphManagement.*;
import graphManagement.DominatingSetExtrator.DominatingSetSolver;
import graphManagement.DominatingSetExtrator.ExactDominantSetSolver;
import graphManagement.GraphBuilder.BasicGraphBuilder;

public class Main {
    public static void main(String args[])
    {
        String file = "/Users/louis/pb2.json";
        if(args.length > 1)
            file = args[1];
        InputJSON input = InputJSON.getInstance(file);
        Graph<Vertex, Edge> g = BasicGraphBuilder.buildGraph(input);
        DominatingSetSolver dss = new ExactDominantSetSolver(g);
        boolean result = dss.hasDominatingSet(2);
    	System.out.println(result);
        if(result)
        	OutputJSON.writeToJSON(dss);
    }
}
