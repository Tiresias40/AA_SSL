package IOManager;

import org.json.JSONObject;

import java.io.FileReader;

public class InputJSON {
    private String fileContent;
    public JSONObject jsonFileContent;


    private void readFromFile(String filePath) throws Exception
    {
        FileReader reader = new FileReader(filePath);
        StringBuilder buffer = new StringBuilder();
        int i;
        char a;
        while((i=reader.read()) != -1)
        {
            a = (char) i;
            buffer.append(a);
        }

        fileContent = buffer.toString();
    }

    private void readFileAndSetJSONObject(String filePath) throws Exception
    {
        readFromFile(filePath);
        jsonFileContent = new JSONObject(fileContent);
    }

    public void readJsonFromFile(String filePath)
    {
        try
        {
            readFileAndSetJSONObject(filePath);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

    }

}
