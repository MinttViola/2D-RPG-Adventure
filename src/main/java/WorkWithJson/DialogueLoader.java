package WorkWithJson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import Main.GamePanel;
import Repositories.ModelsForRepositories.DialogueModel;
import Repositories.ModelsForRepositories.DialogueModelForJson;
import Repositories.ModelsForRepositories.NPCModel;
import Util.WorkWithFilesUtil;

public class DialogueLoader {
	GamePanel gp;
	private WorkWithFilesUtil filesUtil = new WorkWithFilesUtil();
	String path = "Assets/Levels/";
	String langue = "";

	public DialogueLoader(GamePanel gp){
		this.gp = gp;
		langue = gp.getStringLang();
	}
	public List<DialogueModel> getDialogues(int lvl){
		String curPath = path + lvl + "/Dialogue/" + langue + ".json" ;
		List<DialogueModel> result = new ArrayList<>();
		try {
        URL url = filesUtil.get(curPath);
        InputStream is = url.openStream();
        InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
        Gson gson = new Gson();
        DialogueModelForJson[] array = gson.fromJson(reader, DialogueModelForJson[].class);
        result = converFromJsonToModel(array);
    } catch(Exception e){
        e.printStackTrace();
    }
		return result;
	}

	private List<DialogueModel> converFromJsonToModel(DialogueModelForJson[] array){
		List<DialogueModel> models = new ArrayList<DialogueModel>();
		for(DialogueModelForJson a : array) {
            models.add(new DialogueModel(a.id, a.lines));
        }
		return models;
	}
}
