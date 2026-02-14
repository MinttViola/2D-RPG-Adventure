package Service;

import java.util.List;

import javax.xml.namespace.QName;

import Main.GamePanel;
import Repositories.DialogueRepository;
import Repositories.ModelsForRepositories.DialogueModel;

public class DialogueService { 
	
	private DialogueRepository repository;
	private GamePanel gp;

	public DialogueService(GamePanel gp) {
		this.gp = gp;
		repository = new DialogueRepository();
	}

	public List<String> getDialogueList(String id){
		DialogueModel dialogue = repository.getDialogue(id);
    if (dialogue == null) {
        return List.of(); 
    }
    return dialogue.getLines();
	}
	public int getMaxDialogueNumFromID(String id){
		DialogueModel dialogue = repository.getDialogue(id);
    if (dialogue == null) {return 0;}
    return dialogue.getLines().size();
	}
}
