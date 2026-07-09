package Service;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import Entity.NPC;
import Main.GamePanel;
import Tile.TileService.LayerService;
import Tile.TileService.TileService;
import Util.Factories.NPCPFactory;
import WorkWithJson.ModelsForJson.MapModel;
import WorkWithJson.MapLoader;
import WorkWithJson.ModelsForJson.LayerModel;

public class LevelService {
	GamePanel gp;
	SoundService bgMusic = new SoundService();
	private int layersCount;
	private ArrayList<LayerService> layersS;
	private TileService overworldTilseS;
	private NPCPFactory npcFactory;
	private DialogueService dialogueService;
	private List<NPC>	npcList = new ArrayList<NPC>();
	private TileService tileService = new TileService("overworld", 16, 32);

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
    String jsonPath = "Assets/Levels/" + lvl + "/"+lvl+".json"; 
    MapLoader mapReader = new MapLoader();
    MapModel mapModel = mapReader.loadMap(jsonPath);
    if (!layersS.isEmpty()) {
			layersS.clear();
    }
    if (mapModel != null && mapModel.getLayers() != null) {
			for (LayerModel layerModel : mapModel.getLayers()) {
				LayerService layer = new LayerService(gp, layerModel, overworldTilseS);
				layersS.add(layer); 
			}
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
	public void drawMap(Graphics2D g2) {
     for (LayerService layer : layersS) {
         layer.draw(g2);
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
	public void dialogueWithNPC(NPC npcForFialogue){dialogueService.dialogueFromNPC(npcForFialogue);}
	public void dialogueFromEvent(String eventType){dialogueService.dialogueFromEvent(eventType);}

}
