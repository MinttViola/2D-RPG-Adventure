package WorkWithJson.ModelsForJson;

import java.util.List;

public class MapModel {
    int width;
    int height;
    int tilewidth;
    int tileheight;
    List<LayerModel> layers;

    public List<LayerModel> getLayers() {
        return layers;
    }
}