package Main;

import IOManager.InputJSON;
import IOManager.OutputJSON;
import graphManagement.*;
import graphManagement.DominatingSetExtrator.DominatingSetSolverInterface;
import graphManagement.DominatingSetExtrator.RecursiveDominantSetSolver;
import graphManagement.GraphBuilder.BasicGraphBuilder;

public class Main {
    public static void main(String args[])
    {
    	//mode = (0 = brute-force algorithm/ 1 = greedy algorithm)
    	int problemType = -1;
    	//try to find vertex cover with k number of vertex
        int k = -1;
        
    	//Reading data from problem jsonfile
        InputJSON input = InputJSON.getInstance("../data_json/problem/basic_problem_1.json");
        problemType = Integer.parseInt(args[0]);
        k = Integer.parseInt(args[1]);

        //Create graph with the json data and algorithm 
        Graph<Vertex, Edge> g = BasicGraphBuilder.buildGraph(input, problemType);

        //Initialize dominant solver
        DominatingSetSolverInterface dss = new RecursiveDominantSetSolver(g);
        System.out.println("Graph created");
        //check if graph contains dominantSet

        boolean result = dss.hasDominatingSet(k);
        System.out.println(result);
    	//Write in out.json file position of defenders of dominantSet
        if(result)
        	OutputJSON.writeToJSON(dss);
    }
}
