package IOManager;

import org.json.JSONObject;
import java.io.FileReader;
import java.awt.geom.Point2D;

public class InputJSON {
	private ArrayList<Point2D> fieldLimits;
	private ArrayList<Goal> goals;
	private ArrayList<Point2D> opponents;
	private double robotRadius;
	private double thetaStep;
	private double posStep;
	
	public InputJSON (String filePath) {
		JSONObject jObj = getJsonFromFile(filePath);
		
		
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
	
}
