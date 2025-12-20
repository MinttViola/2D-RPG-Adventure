package Entity;

import InteractableObj.SuperObjectBaseModel;
import Main.GamePanel;
import Service.KeyService;
import Util.AnimationStateEnum;
import Util.DirectionEnum;
import Util.GameState;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
	KeyService keyH;
	public List<SuperObjectBaseModel> backpack = new ArrayList();

	public final int screenY;
	public final int screenX;
	public NPC NPCForDialogue;

	public Player(GamePanel gp,KeyService keyH){
		super(gp,2,6);
		this.keyH=keyH;
		isPlayer = true;
		name = "Player";
		
		screenX = gp.screenHeight/2-(gp.tileSize/2);
		screenY = gp.screenWidth/2-(gp.tileSize/2);
		setDefaultValues();
		setUpAnimators(4);
	}

	

	public void setDefaultValues(){
		worldX=gp.startPlayerPositionX;
		worldY=gp.startPlayerPositionY;
		speed = 4;
	}

	public void update(){
		switch (keyH.xChange) {
			case -1:
				direction = DirectionEnum.up;
				break;
			case 1:
				direction = DirectionEnum.down;
				break;
			default:
				break;
		}		switch (keyH.yChange) {
			case 1:
				direction = DirectionEnum.right;
				break;
			case -1:
				direction = DirectionEnum.left;
				break;			
			default:
				break;
		}

		collisionOn = true;
		gp.cCheck.checker(this);
		if((collisionOn&&keyH.xChange!=0)||(collisionOn&&keyH.yChange!=0)){
		switch (direction) {
			case down:
				worldX -=speed;
				break;
			case right:
				worldY +=speed;
				break;
			case left:
				worldY -=speed;
				break;
			case up:
				worldX +=speed;
				break;
			default:
				break;}}
		

	}

	public void NPCIteract(NPC NPC){
		if(!gp.keyH.EPressed)
			return;
		if(NPC != null){
			NPCForDialogue = NPC;
			gp.setGameState(GameState.DialogState);
		}
		/*switch(NPC.name){
			case "KnightNPC":
			System.out.println(NPC.name);
			gp.gameState = GameState.DialogState;
			break;
			case "PhantomNPC":
			System.out.println(NPC.name);
			break;
		}*/
	}

	public void itemIteract(SuperObjectBaseModel obj){
		switch (obj.type) {
			case CHEST:
				for(int i=0;i<backpack.size();i++){
					if(obj.id==backpack.get(i).id){
					obj.interaction();
					backpack.remove(i);
					gp.ui.showMassage("Открыт сундук "+obj.id);
					break;
					}
				gp.ui.showMassage("Найди другой ключ "+obj.id);
				}
				break;
			case CRYSTAL:
				speed += 2;
				gp.ui.showMassage("Ускорение");
				obj.interaction();
				break;
			case KEY:
				backpack.add(obj);
				obj.interaction();
				gp.ui.showMassage("Найден ключ "+obj.id);
				break;
			default:
				break;
		}
	}
	public void draw(Graphics2D g2){
		if (keyH.xChange==0&&keyH.yChange ==0) {
			animators[AnimationStateEnum.getIdbyState("Idle")].draw(g2,screenY,screenX);
		}else{
			animators[AnimationStateEnum.getIdbyState("Walk")].draw(g2,screenY,screenX);
	}
}
}