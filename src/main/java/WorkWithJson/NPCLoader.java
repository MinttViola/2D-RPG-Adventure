package WorkWithJson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.google.gson.Gson;

import Entity.NPC;
import Main.GamePanel;
import Repositories.ModelsForRepositories.NPCModel;
import Util.WorkWithFilesUtil;

public class NPCLoader {
	GamePanel gp;
	private WorkWithFilesUtil filesUtil = new WorkWithFilesUtil();
	String path = "Assets/Levels/";

	public NPCLoader(GamePanel gp){
		this.gp = gp;
	}
	public List<NPCModel> getNPCs(int lvl){
		String curPath = path + lvl + "/NPC.json";
		List<NPCModel> result = new ArrayList<>();
		try {
        URL url = new WorkWithFilesUtil().get(curPath);
        InputStream is = url.openStream();
        InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
        Gson gson = new Gson();
        NPCModel[] array = gson.fromJson(reader, NPCModel[].class);
        result = Arrays.asList(array);
    } catch(Exception e){
        e.printStackTrace();
    }
		return result;
	}
}
