package IOManager;

import graphManagement.Goal;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputJSON {
	private ArrayList<Point2D> fieldLimits;
	private ArrayList<Goal> goals;
	private ArrayList<Point2D> opponents;
	private double robotRadius;
	private double thetaStep;
	private double posStep;
	
	public InputJSON (String filePath) {
		JSONObject jObj = getJsonFromFile(filePath);
		
		fieldLimits = new ArrayList<Point2D>();
		JSONArray fields = jObj.getJSONArray("fields_limits");
		Iterator i = fields.iterator();
        while (i.hasNext()) {
            JSONObject field = (JSONObject) i.next();
        }
		
		robotRadius = (double) jObj.get("robot_radius");
		thetaStep = (double) jObj.get("theta_step");
		posStep = (double) jObj.get("pos_step");
	}
	
	private JSONObject getJsonFromFile (String filePath) {
		JSONParser parser = new JSONParser();

		JSONObject jObj = null;
        try {     
            jObj = (JSONObject) parser.parse(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return jObj;
    }
	
	public ArrayList<Point2D> getFieldLimits() {
		return fieldLimits;
	}

	public void setFieldLimits(ArrayList<Point2D> fieldLimits) {
		this.fieldLimits = fieldLimits;
	}

	public ArrayList<Goal> getGoals() {
		return goals;
	}

	public void setGoals(ArrayList<Goal> goals) {
		this.goals = goals;
	}

	public ArrayList<Point2D> getOpponents() {
		return opponents;
	}

	public void setOpponents(ArrayList<Point2D> opponents) {
		this.opponents = opponents;
	}

	public double getRobotRadius() {
		return robotRadius;
	}

	public void setRobotRadius(double robotRadius) {
		this.robotRadius = robotRadius;
	}

	public double getThetaStep() {
		return thetaStep;
	}

	public void setThetaStep(double thetaStep) {
		this.thetaStep = thetaStep;
	}

	public double getPosStep() {
		return posStep;
	}

	public void setPosStep(double posStep) {
		this.posStep = posStep;
	}
}
