package Service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import Main.GamePanel;
import Repositories.DialogueRepository;
import Repositories.ModelsForRepositories.DialogueModel;
import WorkWithJson.DialogueLoader;

public class DialogueService { 
	
	private DialogueLoader loader;
	private GamePanel gp;
	private List <DialogueModel>dialogueList = new ArrayList<DialogueModel>();

	public DialogueService(GamePanel gp) {
		this.gp = gp;
		loader = new DialogueLoader(gp);
	}

	public void setDialogue(int lvl){
		if(dialogueList.size() != 0) dialogueList.clear();
		dialogueList = loader.getDialogues(lvl);
	}

	public List<String> getDialogueList(String id){
		DialogueModel dialogue = dialogueList.stream()
        .filter(d -> d.getId().equals(id))
        .findFirst()
        .orElse(null);;
    if (dialogue == null) {
        return List.of(); 
    }
    return dialogue.getLines();
	}
	public int getMaxDialogueNumFromID(List<String> dialogues){
    if (dialogues == null) {return 0;}
    return dialogues.size();
	}
}
