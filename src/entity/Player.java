package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

import InteractableObj.SuperObject;
import Main.GamePanel;
import Main.KeyHandler;
import Util.Direction;
import Util.TypesOfSuperObj;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	DirectionAnimator walkAnim;
	DirectionAnimator idleAnim;
	Direction lastDir;
	BufferedImage mainImage;
	public List<SuperObject> backpack = new ArrayList();

	public final int screenY;
	public final int screenX;

	public Player(GamePanel gp,KeyHandler keyH){
		this.keyH=keyH;
		this.gp=gp;
		isPlayer = true;
		screenX = gp.screenWidth/2- (gp.tileSize/2);
		screenY = gp.screenHeight/2- (gp.tileSize/2);
		int saSize = gp.tileSize/gp.colDivisiorforNPC*solidAreaMultiplier;//solid area size
		solidArea = new Rectangle(gp.tileSize/gp.colDivisiorforNPC,gp.tileSize/gp.colDivisiorforNPC*2,saSize,saSize+7);
		walkAnim = new DirectionAnimator(gp,this,"Walk",6);
		idleAnim = new DirectionAnimator(gp,this,"Idle",6);
		setDefaultValues();
	}

	

	public void setDefaultValues(){
		worldX=(gp.maxScreenCol/2)*gp.tileSize;
		worldY=(gp.maxScreenRow/2)*gp.tileSize;
		speed = 4;
	}

	public void update(){
		switch (keyH.yChange) {
			case 1:
				dir =Direction.down;
				break;
			case -1:
				dir =Direction.up;
				break;
			default:
				break;
		}		switch (keyH.xChange) {
			case 1:
				dir =Direction.right;
				break;
			case -1:
				dir =Direction.left;
				break;			
			default:
				break;
		}

		collisionOn = true;
		gp.cCheck.checker(this);
		if((collisionOn&&keyH.xChange!=0)||(collisionOn&&keyH.yChange!=0)){
		switch (dir) {
			case down:
				worldY +=speed;
				break;
			case right:
				worldX +=speed;
				break;
			case left:
				worldX -=speed;
				break;
			case up:
				worldY -=speed;
				break;
			default:
				break;}}
		

	}

	public void itemIteract(SuperObject obj){
		switch (obj.type) {
			case CHEST:
				for(int i=0;i<backpack.size();i++){
					if(obj.id==backpack.get(i).id){
					obj.interaction();
					backpack.remove(i);
					System.out.println("Открыт сундук "+obj.id);
					break;
					}
				System.out.println("Найди другой ключ "+obj.id);
				}
				break;
			case CRYSTAL:
				obj.interaction();
				break;
			case KEY:
				backpack.add(obj);
				obj.interaction();
				System.out.println("Найден ключ "+obj.id);
				break;
		
			default:
				break;
		}
	}

	public void draw(Graphics2D g2){
		if (keyH.xChange==0&&keyH.yChange ==0) {
			idleAnim.draw(g2,screenX,screenY);
		}else{
		walkAnim.draw(g2,screenX,screenY);}
	}

}
