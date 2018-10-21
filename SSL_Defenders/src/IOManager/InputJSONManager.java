package IOManager;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import java.io.FileReader;

public class InputJSONManager {
    private String fileContent;

    public InputJSONManager()
    {

    }

    public void readFromFile(String filePath) throws Exception
    {
        FileReader reader = new FileReader(filePath);
        StringBuffer buffer = new StringBuffer();
        int i;
        char a;
        while((i=reader.read()) != -1)
        {
            a = (char) i;
            buffer.append(a);
        }

        fileContent = buffer.toString();
    }

    public void readJsonFromFile(String filePath) throws Exception
    {
        readFromFile(filePath);
        JSONObject json = new JSONObject(fileContent);

        System.out.println(json.toString());

    }

}
