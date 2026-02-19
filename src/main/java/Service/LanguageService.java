package Service;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;

import Util.WorkWithFilesUtil;

public class LanguageService {
	
	private Map<String, Object> data;
	private WorkWithFilesUtil filesUtil = new WorkWithFilesUtil();
	private String startPath = "Localization/";

	public void loadLanguage(String type, String lang){
		String fullPath = startPath+type+"/"+lang+".json";
		try{
			URL url = filesUtil.get(fullPath);
			InputStreamReader reader = new InputStreamReader(url.openStream());
			Gson gson = new Gson();
			data = gson.fromJson(reader, Map.class);
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String get(String key){
		String[] parts = key.split("\\.");
		Object current = data;
		for(String part : parts){
			if(current instanceof Map){
				current = ((Map<?,?>) current).get(part);
			}
		}
		return current != null ? current.toString() : key;
	}
}
