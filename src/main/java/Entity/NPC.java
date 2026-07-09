package Entity;

import Main.GamePanel;
import Util.Enums.AnimationStateEnum;
import Util.Enums.DirectionEnum;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Random;

public class NPC extends Entity{

	public int id;
	private int actionLockCounter = 0;
	public int lastX,lastY;
	int minY, maxY, minX, maxX;
	public DirectionEnum[] AllDirectionsArray = {DirectionEnum.left,DirectionEnum.right};

	public NPC(GamePanel gp,int id,int xStartPos,int yStartPos,DirectionEnum StartDirection,int speed,int minX,int maxX,int minY,int maxY){
		super(gp,1,1);
		collisionOn = true;
		this.speed = speed;
		this.direction = StartDirection;
		this.id = id;
		worldX = xStartPos*gp.getTileSize();
		worldY = yStartPos*gp.getTileSize();
		this.minY = minY*gp.getTileSize();
		this.maxY = maxY*gp.getTileSize();
		this.minX = minX*gp.getTileSize();
		this.maxX = maxX*gp.getTileSize();
	}
	public NPC(NPC npc, int animTypes, int countFrames
	)
	{
		super(npc.gp,animTypes,countFrames);
		collisionOn = true;
		this.speed = npc.speed;
		this.direction = npc.direction;
		this.id = npc.id;
		worldX = npc.worldX;
		worldY = npc.worldY;
		this.minY = npc.minY;
		this.maxY = npc.maxY;
		this.minX = npc.minX;
		this.maxX = npc.maxX;
	}
	public void setAction(){ randomDirectionForNPC();}

	public void playerInteract(){
		if(!gp.getKeyService().EPressed)
			return;
		turnToPlayer();}
	
	public void update(){
		setAction();
		collisionOn = true;
		gp.getCollisionService().checker(this);
		if(collisionOn){
			solidAreaUpdate();
			switch (direction) {
				case down:
					lastY = worldY;
					if(worldY + speed>=maxY)
						worldY +=speed;
					break;
				case right:
					lastX = worldX;
					if(worldX + speed <= maxX)
						worldX +=speed;
					break;
				case left:
					lastX = worldX;
					if(worldX - speed >= minX)
						worldX -=speed;
					break;
				case up:
					lastY = worldY;
					if(worldY - speed <= minY)
						worldY -=speed;
					break;
				default:
					break;}}
	}

	public void draw(Graphics2D g2){
		int screenX = gp.getPlayer().xPlaceIfCanSee(worldX);
		int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().screenY;
		if(lastX !=worldX || lastY!=worldY)
			if( gp.getPlayer().ifPlayerCanSeeThis(worldX, worldY)){	
					animators[AnimationStateEnum.getIdbyState("Walk")].draw(g2,screenX,screenY);
			}
			else
			animators[AnimationStateEnum.getIdbyState("Idle")].draw(g2,screenX,screenY);
	}

	public void solidAreaUpdate(){
		int saSize = gp.getTileSize()/gp.colDivisiorforNPC*solidAreaMultiplier;//solid area size
		solidArea = new Rectangle(worldX,worldY,saSize,saSize+7);
	}

	public void turnToPlayer(){
		DirectionEnum oppositeDirection = DirectionEnum.getOppositeSide(gp.getPlayer().direction);
		if(Arrays.asList(AllDirectionsArray).contains(oppositeDirection)){
		direction = oppositeDirection;
		return;
		}
		if(Arrays.asList(AllDirectionsArray).contains(DirectionEnum.left)){
			turnToPlayerAlongY();
			return;}
		else turnToPlayerAlongX();
	}

	private void turnToPlayerAlongY(){
		if(worldY>gp.getPlayer().worldY){
		direction = DirectionEnum.left;
		return;
		}if(worldY<gp.getPlayer().worldY){
		direction = DirectionEnum.right;
		return;
		}
	}
	private void turnToPlayerAlongX(){
		if(worldX<gp.getPlayer().worldX){
		direction = DirectionEnum.up;
		return;
		}if(worldX>gp.getPlayer().worldX){
		direction = DirectionEnum.down;
		return;
		}
	}
	public void randomDirectionForNPC(){
		actionLockCounter++;
		if(actionLockCounter == 120){
			if(AllDirectionsArray.length==2)
				randomDirectionTwo();
			else
				randomDirectionAllDir();
			actionLockCounter = 0;
		}
	}

	private void randomDirectionTwo(){
		Random random = new Random();
		int randomNumber = random.nextInt(100)+1;
		if(randomNumber<=25)
		direction = AllDirectionsArray[0];
		else if(randomNumber>25 && randomNumber<=50)
		direction = AllDirectionsArray[1];
		else if(randomNumber>50&&randomNumber<=75)
		direction = AllDirectionsArray[0];
		else 
		direction = AllDirectionsArray[1];

	}
	private void randomDirectionAllDir(){
		
		Random random = new Random();
		int randomNumber = random.nextInt(100)+1;
		if(randomNumber<=25)
		direction = DirectionEnum.left;
		else if(randomNumber>25 && randomNumber<=50)
		direction = DirectionEnum.up;
		else if(randomNumber>50&&randomNumber<=75)
		direction = DirectionEnum.right;
		else 
		direction = DirectionEnum.down;
		
	}
}
