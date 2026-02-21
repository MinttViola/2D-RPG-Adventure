package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import InteractableObj.SuperObjectBaseModel;
import Main.GamePanel;
import Service.KeyService;
import Util.Enums.AnimationStateEnum;
import Util.Enums.DirectionEnum;
import Util.Enums.GameStateEnum;
import lombok.Getter;

public class Player extends Entity {
	KeyService keyH;
	public List<SuperObjectBaseModel> backpack = new ArrayList();

	final int screenY;
	final int screenX;
	NPC NPCForDialogue;

	public Player(GamePanel gp,KeyService keyH){
		super(gp,2,6);
		this.keyH=keyH;
		isPlayer = true;
		name = "Player";

		maxHP = 100;
		curHP = maxHP;
		
		screenX = gp.getScreenHeight()/2-(gp.getTileSize()/2);
		screenY = gp.getScreenWidth()/2-(gp.getTileSize()/2);
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
		gp.getCollisionService().checker(this);
		gp.getEventService().checkEvent();
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
		if(!gp.getKeyService().EPressed)
			return;
		if(NPC != null){
			gp.getlevelService().dialogueWithNPC(NPCForDialogue);
		}
	}

	public void itemIteract(SuperObjectBaseModel obj){
		switch (obj.type) {
			case CHEST:
				for(int i=0;i<backpack.size();i++){
					if(obj.id==backpack.get(i).id){
					obj.interaction();
					backpack.remove(i);
					gp.getUIService().showMassage("Открыт сундук "+obj.id);
					break;
					}
				gp.getUIService().showMassage("Найди другой ключ "+obj.id);
				}
				break;
			case CRYSTAL:
				speed += 2;
				gp.getUIService().showMassage("Ускорение");
				obj.interaction();
				break;
			case KEY:
				backpack.add(obj);
				obj.interaction();
				gp.getUIService().showMassage("Найден ключ "+obj.id);
				break;
			case HEART:
				obj.interaction();
				changeHP(20);
				gp.getUIService().showMassage("Восстановленно здоровье");
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
	public void changeHP(int HPchanged){
		curHP += HPchanged;
		if(curHP>maxHP)
			curHP=maxHP;
		else if(curHP<0)
			curHP = 0;
		gp.getUIService().changeHealthBar(curHP);
	}


	public boolean ifPlayerCanSeeThis(int thisWorldX, int thisWorldY){
		boolean playerCanSeeThis = false;
		int tileSize = gp.getTileSize();
		if(thisWorldX+2+tileSize>worldX - screenX &&
		thisWorldX-2*tileSize<worldX + screenX &&
		thisWorldY+2*tileSize>worldY - screenY &&
		thisWorldY-2*tileSize<worldY + screenY)
			playerCanSeeThis = true;
		return playerCanSeeThis;
	}

	public int xPlaceIfCanSee(int thisWorldX){

		int thisScreenX = thisWorldX-worldX + screenX;
		return thisScreenX;
	}
	public int yPlaceIfCanSee(int thisWorldY){

		int thisScreenY = thisWorldY-worldY + screenY;
		return thisScreenY;
	}

	public boolean isPlayerIntersects(Rectangle rect){
		if (solidArea.intersects(rect))
			return true;
		else
			return false;
	}

	//Getters and setters
	public void setPlayerSolidAreaWorldX(){solidArea.x = worldX + solidArea.x;}
	public void setPlayerSolidAreaWorldY(){solidArea.y = worldY + solidArea.y;}
	public void returnSolidAreaToDefault(){
		solidArea.x = solidAreaDefaultX; 
		solidArea.y = solidAreaDefaultY;}
	public NPC getDialogueNPC(){return NPCForDialogue;}
	public DirectionEnum getDirection(){return direction;}
}