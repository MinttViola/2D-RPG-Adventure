package Entity;

import Main.GamePanel;
import Util.AnimationStateEnum;
import Util.DirectionEnum;
import java.awt.Graphics2D;

public class NPC extends Entity{

	public int id;

	public NPC(GamePanel gp, int id,int animTypes, int countFrames,int xStartPos, int yStartPos, DirectionEnum direction)
	{
		super(gp,animTypes,countFrames);
		this.dir = direction;
		this.id = id;
		worldX = xStartPos*gp.tileSize;
		worldY = yStartPos* gp.tileSize;
	}

	
	public void draw(Graphics2D g2){
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){	
			  animators[AnimationStateEnum.getIdbyState("Idle")].draw(g2,screenY,screenX);
		}
	}
}
