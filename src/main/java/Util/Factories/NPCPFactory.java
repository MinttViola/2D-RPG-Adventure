package Util.Factories;

import java.util.ArrayList;
import java.util.List;

import Entity.NPC;
import Entity.NPCKnight;
import Entity.NPCPhantom;
import Main.GamePanel;
import Repositories.ModelsForRepositories.NPCModel;
import WorkWithJson.NPCLoader;

public class NPCPFactory {
GamePanel gp;
NPCLoader listNPCs;

	public NPCPFactory(GamePanel gp){
		this.gp = gp;
		listNPCs = new NPCLoader(gp);
	}

	public List<NPC> setNPC(int lvl){
		List<NPC> result = new ArrayList<>();
		for(NPCModel m : listNPCs.getNPCs(lvl)){
			NPC npc = createSingleNPC(gp, m);
			if(npc != null) {
				result.add(npc);
			}
		}
		return result;
	}

	private static NPC createSingleNPC(GamePanel gp, NPCModel m) {
	NPC npc = new NPC(gp, m.id,m.xStartPosition,m.yStartPosition,m.direction,m.speed,m.minY,m.maxY,m.minX,m.maxX);
	switch (m.type) {
		case knight:
		NPCKnight knightNPC = new NPCKnight(npc);
		return knightNPC;
		case phantom:
			return new NPCPhantom(npc);
	}
	return null;
	}
}
