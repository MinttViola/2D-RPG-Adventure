package WorkWithJson;

import WorkWithJson.ModelsForJson.MapModel;
import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapLoader {
    public MapModel loadMap(String path) {
        Gson gson = new Gson();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path);
             InputStreamReader reader = new InputStreamReader(is)) {
            
            if (is == null) throw new RuntimeException("Map file not found: " + path);
            return gson.fromJson(reader, MapModel.class);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}