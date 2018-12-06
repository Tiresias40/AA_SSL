package Main;

import IOManager.InputJSON;
import IOManager.OutputJSON;
import graphManagement.*;
import graphManagement.DominatingSetExtrator.DominatingSetSolverInterface;
import graphManagement.DominatingSetExtrator.ExactDominantSetSolver;
import graphManagement.DominatingSetExtrator.RecursiveDominantSetSolver;
import graphManagement.GraphBuilder.BasicGraphBuilder;

public class Main {
    public static void main(String args[])
    {
    	//Reading data from problem jsonfile
        InputJSON input = InputJSON.getInstance("../data_json/problem/basic_problem_2.json");
        int problemSize = 1;
        if(args.length > 1)
            problemSize = Integer.parseInt(args[0]);


        //Create graph with the json data and mode (0 = brute-force algorithm/ 1 = greedy algorithm)
        Graph<Vertex, Edge> g = BasicGraphBuilder.buildGraph(input, problemSize);

        //Initialize dominant solver

        DominatingSetSolverInterface dss = new RecursiveDominantSetSolver(g);
        System.out.println("Graph created");
        //check if graph contains dominantSet

        boolean result = dss.hasDominatingSet(3);
        System.out.println(result);
    	//Write in out.json file position of defenders of dominantSet
        if(result)
        	OutputJSON.writeToJSON(dss);

    }
}
