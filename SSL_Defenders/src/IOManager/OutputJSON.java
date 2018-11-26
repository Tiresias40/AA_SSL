package IOManager;

import graphManagement.DominatingSetExtrator.DominatingSetSolver;
import graphManagement.Vertex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Vector;

public class OutputJSON {

    public static void writeToJSON(DominatingSetSolver solution)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n\t\"defenders\" : [");

        Vector<Vertex> vertices = solution.getDominatingSet();
        boolean first = true;
        for(Vertex v : vertices)
        {
            if(!first)
                sb.append(",\n");
            else
                first = false;

            sb.append("\t\t");
            sb.append('[').append(v.location.x).append(',').append(v.location.y).append(']');
        }

        sb.append("\n\t]\n}");

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("out.json"));
            writer.write(sb.toString());
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
