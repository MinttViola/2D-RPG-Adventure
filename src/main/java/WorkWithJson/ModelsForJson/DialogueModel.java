package WorkWithJson.ModelsForJson;

import java.util.List;

public class DialogueModel {

    private String id;          
    private List<String> lines;

    public DialogueModel(String id, List<String> lines) {
        this.id = id;
        this.lines = lines;
    }

    public String getId() {
        return id;
    }

    public List<String> getLines() {
        return lines;
    }
}
