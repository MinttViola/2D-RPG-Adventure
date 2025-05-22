package InteractableObj;

import java.awt.Graphics2D;

import Main.GamePanel;
import Util.Animator;

public class SuperObject {
	public Animator anim;
	public String name;
	public String path;
	public int worldX,worldY;
	public boolean collision=false;
	public GamePanel gp;

	public void findPath(){
		path = "/Assets/Props_Items_(animated)/"+name+"_anim_strip.png";
	}

	public void draw(Graphics2D g2){				
		int screenX = worldX-gp.player.worldX+gp.player.screenX;
		int screenY = worldY-gp.player.worldY+gp.player.screenY;
		if(worldX+gp.tileSize>gp.player.worldX - gp.player.screenX &&
		worldX+2*gp.tileSize<gp.player.worldX+gp.player.screenX &&
		worldY+2*gp.tileSize>gp.player.worldY - gp.player.screenY &&
		worldY+2*gp.tileSize<gp.player.worldY+gp.player.screenY){	
			anim.draw(g2, screenX, screenY);
	}}
}
