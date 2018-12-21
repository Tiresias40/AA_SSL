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
	public static void main(String args[]) {
		// Set timer for program duration
		double time = System.currentTimeMillis();

		// Reading data from problem jsonfile
		InputJSON input = null;
		if (args.length > 0)
			input = InputJSON.getInstance(args[0]);
		else
			input = InputJSON
					.getInstance("../data_json/problem/basic_problem_2.json");

		// method = (0 = Exact brute-force / 1 = Recursive) for brute-force/ 2 =
		// greedy algorithm)
		int method = -1;
		if (args.length > 1
				&& (args[1].equals("0") || args[1].equals("1") || args[1]
						.equals("2")))
			method = Integer.parseInt(args[1]);
		else
			System.err
					.println("Second argument is method : (0 for exact or 1 for recursive) for brute-force algorithm and 2 for greedy algorithm");

		// size of dominant set we try to find
		int k = -1;
		if (args.length > 2 && Integer.parseInt(args[2]) > 0
				&& Integer.parseInt(args[2]) <= 6)
			k = Integer.parseInt(args[2]);
		else
			System.err
					.println("Third argument is the size of dominant set you want to find : must be between 1 and 6");

		// Create graph with the json data
		Graph<Vertex, Edge> g = BasicGraphBuilder.buildGraph(input);
		System.out.println("Graph created");

		// Initialize dominant solver, it's depend on method choice
		DominatingSetSolverInterface dss = null;
		if (method == 0)
			//Not optimized brute-force algorithm (Work slowly)
			dss = new ExactDominantSetSolver(g);
		else if (method == 1)
			dss = new RecursiveDominantSetSolver(g);
		else if (method == 2)
			dss = new GreedyDominantSetSolver(g);

		// check if graph contains dominatingSet
		boolean result = dss.hasDominatingSet(k);
		if (!result)
			System.out.println("No solution find for this position step");
		else {
			System.out.println("A solution was found in : "
					+ (System.currentTimeMillis() - time) + "ms");
		}

		// Write in out.json file position of defenders of dominantSet
		if (result) {
			OutputJSON.writeToJSON(dss);
			System.out.println("Output file have been writing");
		}

	}
}
