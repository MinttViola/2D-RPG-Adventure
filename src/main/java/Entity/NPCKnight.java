package Entity;

public class NPCKnight extends NPC{


	public NPCKnight(NPC npc){
		super(npc, 2, 6);
		name = "KnightNPC";
		setUpAnimators(4);
	}

	@Override
	public void setAction(){
		randomDirectionForNPC();
	}
} 
