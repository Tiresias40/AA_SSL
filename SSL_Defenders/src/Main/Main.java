package Main;

import IOManager.InputJSON;
import graphManagement.Graph;
import graphManagement.GraphBuilder.BasicGraphBuilder;

public class Main {
    public static void main(String args[])
    {
        InputJSON input = InputJSON.getInstance("../basic_problem_1.json");
        Graph g = BasicGraphBuilder.buildGraph(input);
        System.out.println(g);
    }
}
