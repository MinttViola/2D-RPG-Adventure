package Entity;

import Main.GamePanel;
import Util.AnimationStateEnum;
import Util.DirectionEnum;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class NPC extends Entity{

	public int id;
	private int actionLockCounter = 0;
	public int lastX,lastY;
	public DirectionEnum[] AllDirectionsList = {DirectionEnum.left,DirectionEnum.right};
	public NPC(GamePanel gp, int id,int animTypes, int countFrames,int xStartPos, int yStartPos, DirectionEnum StartDirection, int speed)
	{
		super(gp,animTypes,countFrames);
		collisionOn = true;
		this.speed = speed;
		this.direction = StartDirection;
		this.id = id;
		worldX = xStartPos*gp.tileSize;
		worldY = yStartPos* gp.tileSize;
	}
	public void setAction(){}
	public void playerInteract(){
		System.err.println("interact " + name);
	}
	public void update(){
		setAction();
		collisionOn = true;
		gp.cCheck.checker(this);
		if(collisionOn){
			solidAreaUpdate();
			switch (direction) {
				case down:
					lastX = worldX;
					worldX -=speed;
					break;
				case right:
					lastY = worldY;
					worldY +=speed;
					break;
				case left:
					lastY = worldY;
					worldY -=speed;
					break;
				case up:
					lastX = worldX;
					worldX +=speed;
					break;
				default:
					break;}}
	}

	public void draw(Graphics2D g2){
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if(lastX !=worldX || lastY!=worldY)
			if( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){	
					animators[AnimationStateEnum.getIdbyState("Walk")].draw(g2,screenY,screenX);
			}
			else
			animators[AnimationStateEnum.getIdbyState("Idle")].draw(g2,screenY,screenX);
	}

	public void solidAreaUpdate(){
		int saSize = gp.tileSize/gp.colDivisiorforNPC*solidAreaMultiplier;//solid area size
		solidArea = new Rectangle(worldX,worldY,saSize,saSize+7);
	}
	public void randomDirectionForNPC(){
		actionLockCounter++;
		if(actionLockCounter == 120){
			if(AllDirectionsList.length==2)
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
		direction = AllDirectionsList[0];
		else if(randomNumber>25 && randomNumber<=50)
		direction = AllDirectionsList[1];
		else if(randomNumber>50&&randomNumber<=75)
		direction = AllDirectionsList[0];
		else 
		direction = AllDirectionsList[1];

	}
	private void randomDirectionAllDir(){
		
		Random random = new Random();
		int randomNumber = random.nextInt(100)+1;
		if(randomNumber<=25)
		direction = DirectionEnum.left;
		else if(randomNumber>25 && randomNumber<=50)
		direction = DirectionEnum.up;
		else if(randomNumber>50&&randomNumber<=75)
		direction = DirectionEnum.left;
		else 
		direction = DirectionEnum.down;
		
	}
}
