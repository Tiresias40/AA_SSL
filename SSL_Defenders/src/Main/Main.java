package Main;

import IOManager.InputJSON;
import IOManager.OutputJSON;
import graphManagement.*;
import graphManagement.DominatingSetExtrator.DominatingSetSolverInterface;
import graphManagement.DominatingSetExtrator.ExactDominantSetSolver;
import graphManagement.DominatingSetExtrator.GreedyDominantSetSolver;
import graphManagement.DominatingSetExtrator.RecursiveDominantSetSolver;
import graphManagement.GraphBuilder.BasicGraphBuilder;

public class Main {
    public static void main(String args[])
    {
    	//Reading data from problem jsonfile
    	InputJSON input = null;
    	
    	if(args.length > 0)
    		input = InputJSON.getInstance(args[0]);
    	else 
    		input = InputJSON.getInstance("../data_json/problem/basic_problem_2.json");
        
    	// Exact or Recursive Method
    	int method = 0;
    	if(args.length > 1 && (args[1].equals("0") || args[1].equals("1")))
    		method = Integer.parseInt(args[1]);
    	//mode = (0 = brute-force algorithm/ 1 = greedy algorithm)
    	int problemType = 0;
    	if(args.length > 2 && (args[2].equals("0") || args[2].equals("1")))
    		problemType = Integer.parseInt(args[2]);
    	//try to find vertex cover with k number of vertex
        int k = 3;
    	if(args.length > 3 && Integer.parseInt(args[3]) > 0)
    		k = Integer.parseInt(args[3]);

        //Create graph with the json data and mode (0 = brute-force algorithm/ 1 = greedy algorithm)
        Graph<Vertex, Edge> g = BasicGraphBuilder.buildGraph(input, problemType);


        //Initialize dominant solver
        DominatingSetSolverInterface dss;
        if(method == 0)
        	dss = new ExactDominantSetSolver(g);
        else if(method == 1)
        	dss = new RecursiveDominantSetSolver(g);
        else
            dss = new GreedyDominantSetSolver(g);

        //check if graph contains dominatingSet
        boolean result = dss.hasDominatingSet(k);
        double time = System.currentTimeMillis();
        if(!result)
            System.out.println("No solution find for this position step");
        else {
            System.out.println("A solution was found in : " + (System.currentTimeMillis() - time) + "ms");
        }

    	//Write in out.json file position of defenders of dominantSet
        if(result)
        	OutputJSON.writeToJSON(dss);

    }
}
