package WorkWithJson.ModelsForJson;

import java.util.List;

public class LayerModel {

    String name;
    int width;
    int height;
    int[] data;
    boolean visible;

    public String getName() {return name;}
    public int[] getData() {return data;}
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isVisible() { return visible; } 
}
