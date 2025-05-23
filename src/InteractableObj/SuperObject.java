package InteractableObj;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.GamePanel;
import Util.Animator;
import Util.TypesOfSuperObj;

public class SuperObject {
	public Animator anim;
	public String name;
	public String path;
	public int worldX,worldY;
	public boolean collision=false;
	public GamePanel gp;
	public int id = -1;
	public float frameRate = 0.16f;
	public Rectangle solidArea;
	public TypesOfSuperObj type;

	public void start(GamePanel gp, int xStartPos, int yStartPos){
		this.gp = gp;
		worldX = xStartPos*gp.tileSize;
		worldY = yStartPos*gp.tileSize;
		solidArea = new Rectangle(worldX,worldY,gp.tileSize,gp.tileSize);
	}

	public void findPath(){
		path = "/Assets/Props_Items_(animated)/"+name+"_item_anim_strip.png";
	}

	public void interaction(){
		switch (type) {
			case CHEST:
				anim.nextStatus();
				disapear();
				break;
			case CRYSTAL:
				disapear();
				break;
			case KEY:
				anim.nextStatus();
				disapear();
				break;
			default:
				break;
		}

	}

	public void disapear(){
				solidArea.height=0;
				solidArea.width=0;
	}

	public void draw(Graphics2D g2){				
		int screenX = worldX-gp.player.worldX+gp.player.screenX;
		int screenY = worldY-gp.player.worldY+gp.player.screenY;
		if(worldX+2+gp.tileSize>gp.player.worldX - gp.player.screenX &&
		worldX-2*gp.tileSize<gp.player.worldX+gp.player.screenX &&
		worldY+2*gp.tileSize>gp.player.worldY - gp.player.screenY &&
		worldY-2*gp.tileSize<gp.player.worldY+gp.player.screenY){	
			anim.draw(g2, screenX, screenY);
		}
	}
}
