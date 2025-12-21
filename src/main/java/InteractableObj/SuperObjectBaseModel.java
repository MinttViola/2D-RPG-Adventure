package InteractableObj;

import Main.GamePanel;
import Service.SoundService;
import Util.Animator;
import Util.TypesOfSuperObjEnum;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SuperObjectBaseModel {
	public Animator anim;
	public String name,path;
	public int worldX,worldY;
	public boolean collision=false;
	public GamePanel gp;
	public int id = -1;
	public float frameRate = 0.16f;
	public Rectangle solidArea;
	public TypesOfSuperObjEnum type;
	private SoundService sound = new SoundService();

	public void start(GamePanel gp, int xStartPos, int yStartPos){
		this.gp = gp;
		worldX = xStartPos*gp.getTileSize();
		worldY = yStartPos*gp.getTileSize();
		solidArea = new Rectangle(worldX,worldY,gp.getTileSize(),gp.getTileSize());
	}

	public void findPath(){
		path = "Assets/Props_Items_(animated)/"+name+"_anim.png";
	}

	public void interaction(){
		switch (type) {
			case CHEST:
				disapear();
				break;
			case CRYSTAL:
				disapear();
				break;
			case KEY:
				disapear();
				break;
			case HEART:
				disapear();
				break;
			default:
				break;
		}
	}

	public void disapear(){
			sound.playSE(name+" disapear");
			anim.nextStatus();
			solidArea.height=0;
			solidArea.width=0;
	}

	public void draw(Graphics2D g2){				
		int screenX = gp.player.xPlaceIfCanSee(worldX);
		int screenY = gp.player.yPlaceIfCanSee(worldY);
		if(gp.player.ifPlayerCanSeeThis(worldX, worldY)){	
			anim.draw(g2, screenY, screenX);
		}
	}
}
