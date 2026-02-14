package Repositoryes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.DialogueModel;

public class DialogueRepositories {
		private Map<String, DialogueModel> dialogueMap = new HashMap<>();

    public DialogueRepositories() {
        loadDialogues();
    }

    private void loadDialogues() {
        dialogueMap.put("KnightNPC_1", new DialogueModel(
                "KnightNPC_1",
                List.of(
                        "this is knight thing",
                        "this is also knight thing",
                        "this is more knight thing"
                )
        ));
        dialogueMap.put("KnightNPC_2", new DialogueModel(
                "KnightNPC_2",
                List.of(
                        "this is knight thing1",
                        "this is also knight thing2",
                        "this is more knight thing3"
                )
        ));
    }

    public DialogueModel getDialogue(String id) {
        return dialogueMap.get(id);
    }
}
