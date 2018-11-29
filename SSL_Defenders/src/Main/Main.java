package Main;

import IOManager.InputJSON;
import IOManager.OutputJSON;
import graphManagement.*;
import graphManagement.DominatingSetExtrator.ExactDominantSetSolver;
import graphManagement.GraphBuilder.BasicGraphBuilder;

public class Main {
    public static void main(String args[])
    {
    	//Reading data from problem jsonfile
        InputJSON input = InputJSON.getInstance("data_json/problem/basic_problem_1.json");
        //Create graph with the json data
        Graph<Vertex, Edge> g = BasicGraphBuilder.buildGraph(input);
        //Initialize dominant solver
        ExactDominantSetSolver dss = new ExactDominantSetSolver(g);
        //check if graph contains dominantSet

        boolean result = dss.hasDominatingSet(2);
    	System.out.println(result);
    	//Write in out.json file position of defenders of dominantSet
        if(result)
        	OutputJSON.writeToJSON(dss);

    }
}
