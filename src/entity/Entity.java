package Entity;

import Main.GamePanel;
import Util.AnimationStateEnum;
import Util.DirectionAnimator;
import Util.DirectionEnum;
import java.awt.Rectangle;

public class Entity {
	GamePanel gp;
	public String name;
	public int worldX,worldY;
	public int speed;
	public Rectangle solidArea;
	public boolean collisionOn = false;
	public int solidAreaMultiplier = 4;
	public DirectionEnum direction = DirectionEnum.down;
	public boolean isPlayer = false;
	DirectionAnimator[] animators;
	int dirCountFrames;
	int maxHP,curHP;

	public Entity(GamePanel gp, int animTypes, int countFrames){
		this.gp = gp;
		int saSize = gp.tileSize/gp.colDivisiorforNPC*solidAreaMultiplier;//solid area size
		solidArea = new Rectangle(gp.tileSize/gp.colDivisiorforNPC,gp.tileSize/gp.colDivisiorforNPC*2,saSize,saSize+7);
		animators = new DirectionAnimator[animTypes];
		dirCountFrames = countFrames;
	}
	public void setUpAnimators(int dirCount){
		for(int i = 0; i<animators.length;i++){
			animators[i] = new DirectionAnimator(gp, this, AnimationStateEnum.getStateById(i),dirCountFrames,dirCount);
		}
	}
}
