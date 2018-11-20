package IOManager;

import graphManagement.Goal;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.awt.Point;
import java.io.FileReader;

public class InputJSON {

	public static InputJSON singleton = null;

	private ArrayList<Point> fieldLimits;
	private ArrayList<Goal> goals;
	private ArrayList<Point> opponents;
	private double robotRadius;
	private double thetaStep;
	private double posStep;

	public static InputJSON getInstance(String filePath) {
		if (singleton == null)
			singleton = new InputJSON(filePath);
		return singleton;
	}

	public static InputJSON getInstance() throws RuntimeException {
		if (singleton != null)
			return singleton;

		throw new RuntimeException("InputJson not instantiated yet");
	}

	public static InputJSON renewInstance(String filePath) {
		singleton = null;
		return getInstance(filePath);
	}

	private InputJSON(String filePath) {
		JSONObject jObj = readJsonFromFile(filePath);
		if (jObj == null)
			System.exit(-1);

		fieldLimits = new ArrayList<Point>();
		JSONArray fields = jObj.getJSONArray("field_limits");
		for (int i = 0; i < fields.length(); i++) {
			JSONArray field = fields.getJSONArray(i);
			fieldLimits.add(new Point(field.getInt(0), field.getInt(1)));
		}


		goals = new ArrayList<Goal>();
		JSONArray listGoals = jObj.getJSONArray("goals");
		for (int i = 0 ; i < listGoals.length() ; i++) {			
			JSONObject currGoal = listGoals.getJSONObject(i);

			ArrayList<Point> limits = new ArrayList<Point>();
			JSONArray posts = currGoal.getJSONArray("posts");
			for (int j = 0 ; j < posts.length() ; j++) {
				JSONArray point = posts.getJSONArray(j);
				limits.add(new Point(point.getInt(0), point.getInt(1)));
			}
			
			JSONArray direction = currGoal.getJSONArray("direction");
			goals.add(new Goal(limits, new Point(direction.getInt(0), direction.getInt(1))));
		}
		
		opponents = new ArrayList<Point>();
		JSONArray totalOpponents = jObj.getJSONArray("opponents");
		for (int i = 0 ; i < totalOpponents.length() ; i++) {
			JSONArray opponent = totalOpponents.getJSONArray(i);
			opponents.add(new Point(opponent.getInt(0), opponent.getInt(1)));
		}
		

		robotRadius = jObj.getDouble("robot_radius");
		thetaStep = jObj.getDouble("theta_step");
		posStep = jObj.getDouble("pos_step");
	}

	private JSONObject readJsonFromFile(String filePath) {
		try {
			FileReader reader = new FileReader(filePath);
			StringBuilder buffer = new StringBuilder();
			int i;
			char a;
			while ((i = reader.read()) != -1) {
				a = (char) i;
				buffer.append(a);
			}

			reader.close();

			return new JSONObject(buffer.toString());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public int opponentNumber() {
		return opponents.size();
	}

	public ArrayList<Point> getFieldLimits() {
		return fieldLimits;
	}

	public void setFieldLimits(ArrayList<Point> fieldLimits) {
		this.fieldLimits = fieldLimits;
	}

	public ArrayList<Goal> getGoals() {
		return goals;
	}

	public void setGoals(ArrayList<Goal> goals) {
		this.goals = goals;
	}

	public ArrayList<Point> getOpponents() {
		return opponents;
	}

	public void setOpponents(ArrayList<Point> opponents) {
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
