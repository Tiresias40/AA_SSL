package Main;

import IOManager.InputJSON;
import IOManager.OutputJSON;
import graphManagement.*;
import graphManagement.DominatingSetExtrator.ExactDominantSetSolver;
import graphManagement.GraphBuilder.BasicGraphBuilder;

public class Main {
    public static void main(String args[])
    {
        InputJSON input = InputJSON.getInstance("../data_json/problem/basic_problem_1.json");
        Graph<Vertex, Edge> g = BasicGraphBuilder.buildGraph(input);
        ExactDominantSetSolver dss = new ExactDominantSetSolver(g);
        boolean result = dss.hasDominatingSet(1);
    	System.out.println(result);
        if(result)
        	OutputJSON.writeToJSON(dss);
    }
}
