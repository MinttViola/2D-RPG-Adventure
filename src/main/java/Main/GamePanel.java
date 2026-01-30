package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.NPC;
import Entity.NPCPlasment;
import Entity.Player;
import InteractableObj.ObjPlasment;
import InteractableObj.SuperObjectBaseModel;
import Service.CollisionService;
import Service.EventService;
import Service.KeyService;
import Service.SoundService;
import Service.UIService;
import Tile.TileService.LayerService;
import Tile.TileService.TileService;
import Util.GameState;
import WorkWithJson.MapLayerEnum;

public class GamePanel extends JPanel implements Runnable {
	//screen settings 
	private int originalTitleSize = 16; 
	private int scale = 4;
	private int tileSize = originalTitleSize*scale;
	private final int maxScreenCol=20;//1680
	private final int maxScreenRow=10;//640
	private final int screenWidth=tileSize*maxScreenCol;
	private final int screenHeight=tileSize*maxScreenRow;

  //word settings
	private final int maxWorldCol = 30;
	private final int maxWorldRow = 30;
	private final int worldWidth=tileSize*maxWorldCol;
	private final int worldHeight=tileSize*maxWorldRow;
	private final int layersCount = 4;

	public final int  startPlayerPositionX=0*tileSize;
	public final int  startPlayerPositionY=6*tileSize;
	

	
	public final int colDivisiorforNPC = 6;
	public final int colDivisiorforTiles = 6;

	private int FPS = 60;
	private TileService overworldTilseS = new TileService(	"overworld",originalTitleSize,tileSize);
	private LayerService[] layersS = new LayerService[layersCount];
	Thread gameThread;
	
	private CollisionService cCheck = new CollisionService(this);	
	private KeyService keyH = new KeyService(this);
	private UIService ui = new UIService(this);
	SoundService bgMusic = new SoundService();
	private EventService eventService = new EventService(this);
	private Player player = new Player(this,keyH);
	private SuperObjectBaseModel obj[] = new SuperObjectBaseModel[10];
	private ObjPlasment objPlase = new ObjPlasment(this);
	private NPC npc[] = new NPC[10];
	NPCPlasment NPCPlase = new NPCPlasment(this);
	private GameState gameState = GameState.PlayState;

	public GamePanel(){
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		bgMusic.playBackgroundMusic(0);
		LayersServicesSetup();
		startGameThread();
	}

	public void startGameThread(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void LayersServicesSetup(){
		for(int i =0;i<layersCount;i++){
			layersS[i] = new LayerService(this, 0, MapLayerEnum.getNameByOrder(i), overworldTilseS);
		}
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long curTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread !=null){
			curTime = System.nanoTime();
			delta +=(curTime-lastTime)/drawInterval;
			timer +=(curTime-lastTime);
			lastTime=curTime;
			if (delta>=1) {
				update();
				repaint();
				delta--;
				drawCount ++;
			}
			if(timer>=1000000000){
				timer = 0;
				drawCount = 0;
			}
		}
	}

	public void update(){
		switch(gameState){
			case PlayState:
			player.update();
		for(int i = 0;i<=npc.length-1;i++){
			if(npc[i]!=null)
				npc[i].update();
		}
			break;
			case PauseState:
			break;
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(gameState == GameState.TitleState){
			ui.draw(g2);
			g2.dispose();
		}
		else{
			for(int i =0;i<layersCount;i++){
				layersS[i].draw(g2);
			}
			player.draw(g2);
			for(int i = 0;i<=obj.length-1;i++){
				if(obj[i]!=null)
					obj[i].draw(g2);
				if(npc[i]!=null)
					npc[i].draw(g2);
			}
			ui.draw(g2);
			g2.dispose();
		}	
	}

	public void stopMusic(){bgMusic.stop();}
	
	public void playDisapearSE(String name){bgMusic.playSE(name);}

	public void nextDialogue(){ui.nextDialogue();}

	public void muteAudio(){bgMusic.muteAudio();}

	//Setters and Getters
	public void setGameState(GameState newState){this.gameState = newState;}
	public GameState getGameState(){return gameState;}
	public int getOrignalTileSize(){return originalTitleSize;}
	public int getScreenWidth(){return screenWidth;}
	public int getScreenHeight(){return screenHeight;}
	public int getMaxWorldRow(){return maxWorldRow;}
	public int getMaxWorldCol(){return maxWorldCol;}
	public int getLayersCount(){return layersCount;}
	public LayerService getLayerFromLayerService(int index){return layersS[index];}	
	public CollisionService getCollisionService(){return cCheck;}
	public KeyService getKeyService(){return keyH;}
	public UIService getUIService(){return ui;}
	public Player getPlayer(){return player;}
	public int getTileSize() {return tileSize;}
	public Player getPlayerInstance() {return player;}
	public SuperObjectBaseModel getSuperObj(int index) {return obj[index];}
	public void setSuperObjInArray(SuperObjectBaseModel obj, int index) {this.obj[index] = obj;}
	public int getSuperObjArrayLength() {return obj.length;}
	public NPC getNPC(int index) {return npc[index];}
	public int getNPCArrayLength() {return npc.length;}
	public void setNPCInArray(NPC npc, int index) {this.npc[index] = npc;}
	public EventService getEventService() {return eventService;}
}
