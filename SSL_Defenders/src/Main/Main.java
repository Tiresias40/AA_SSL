package Main;

import IOManager.InputJSON;
import graphManagement.Graph;
import graphManagement.GraphBuilder.BasicGraphBuilder;

public class Main {
    public static void main(String args[])
    {
        InputJSON input = InputJSON.getInstance("/Users/louis/pb.json");
        System.out.println(input);
        Graph g = BasicGraphBuilder.buildGraph(input);
        System.out.println(g);
    }
}
