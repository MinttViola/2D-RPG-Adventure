package Service;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import Entity.NPC;
import Main.GamePanel;
import Repositories.ModelsForRepositories.DialogueModel;
import Tile.TileService.LayerService;
import Tile.TileService.TileService;
import Util.Factories.NPCPFactory;
import WorkWithJson.MapLayerEnum;

public class LevelService {
	GamePanel gp;
	SoundService bgMusic = new SoundService();
	private int layersCount;
	private List<LayerService> layersS;
	private TileService overworldTilseS;
	private NPCPFactory npcFactory;
	private DialogueService dialogueService;
	private List<NPC>	npcList = new ArrayList<NPC>();

	public LevelService(GamePanel gp){
		this.gp = gp;
		npcFactory = new NPCPFactory(gp);
		dialogueService = new DialogueService(gp);
		layersCount = gp.getLayersCount();
		layersS = new ArrayList<LayerService>();
		overworldTilseS = new TileService(	"overworld",gp.getOrignalTileSize(),gp.getTileSize());
	}

	public void setLevel(int lvl){
		setMusic(lvl);
		setMap(lvl);
		setNPC(lvl);
		setSuperObjects(lvl);
		setDialogue(lvl);
	}


	private void setMusic(int lvl)
	{
		bgMusic.playBackgroundMusic(lvl);
	}

	private void setMap(int lvl){
		if(layersS.size() != 0) layersS.clear();
		for(int i =0;i<layersCount;i++){
			layersS.add(new LayerService(gp, 0, MapLayerEnum.getNameByOrder(i), overworldTilseS));
		}
	}

	private void setNPC(int lvl){
		if(npcList.size() != 0) npcList.clear();
		npcList = npcFactory.setNPC(lvl);
	}

	private void setDialogue(int lvl){
		dialogueService.setDialogue(lvl);
	}

	private void setSuperObjects(int lvl){}

	public LayerService getLayerById(int Id){
		return layersS.get(Id);
	}

	//work with bg music 
	public void muteAudio(){bgMusic.muteAudio();}
	public void stopMusic(){bgMusic.stop();}

	//draw
	public void drawNPCs(Graphics2D g2){
		for(NPC npc : npcList){
			npc.draw(g2);
		}
	}

	//update 
	public void updateNPCs(){
		for(NPC npc : npcList){
			npc.update();
		}
	}

	//collision
	public void collisionNPC(int x, int y){
		for(NPC npc : npcList){
			Rectangle col = new Rectangle(x, y, gp.getTileSize(), gp.getTileSize());
			if(npc!=null){
				if(col.intersects(npc.solidArea)){
					npc.playerInteract();
					gp.getPlayer().NPCIteract(npc);
				}
			}
		}
	}

	//dialogue
	public int getMaxDialogueNumFromID(List<String> dialogues){return dialogueService.getMaxDialogueNumFromID(dialogues);}
	public List<String> getDialogueList(String id){return dialogueService. getDialogueList(id);}

}
