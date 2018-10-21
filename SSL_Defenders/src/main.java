import IOManager.InputJSONManager;

public class Main {
    public static void main(String args[])
    {
        InputJSONManager ijm = new InputJSONManager();
        try{
            ijm.readJsonFromFile("/Users/louis/Documents/ssl_defender_viewer/configs/basic_problem_1.json");
        }
        catch (Exception e) { e.getMessage(); }

    }
}
