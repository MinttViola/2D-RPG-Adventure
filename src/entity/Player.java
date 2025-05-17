package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	DirectionAnomator dirAnim;
	Direction lastDir;
	BufferedImage mainImage;

	public Player(GamePanel gp,KeyHandler keyH){
		this.keyH=keyH;
		this.gp=gp;
		getMainImage();
		dirAnim = new DirectionAnomator(gp,this,mainImage,6);
		setDefaultValues();
	}

	
	public void getMainImage(){
		try{
			mainImage= ImageIO.read(getClass().getResourceAsStream("/Assets/Player/Player.png"));} catch(IOException e){
				e.printStackTrace();
			}
	}

	public void setDefaultValues(){
		x=4;
		y=4;
		speed = 1;
	}

	public void update(){
		switch (keyH.yChange) {
			case 1:
				dirAnim.still=false;
				y +=speed;
				dirAnim.dir =Direction.down;
				break;
			case -1:
				dirAnim.still=false;
				y -=speed;
				dirAnim.dir =Direction.up;
				break;
			case 0:
			if(keyH.xChange ==0)
				dirAnim.still=true;
				break;
			default:
				break;
		}		switch (keyH.xChange) {
			case 1:
				dirAnim.still=false;
				x +=speed;
				dirAnim.dir =Direction.right;
				break;
			case -1:
				dirAnim.still=false;
				x -=speed;
				dirAnim.dir =Direction.left;
				break;			
			case 0:
			if(keyH.yChange ==0)
				dirAnim.still=true;
				break;
			default:
				break;
		}

	}

	public void draw(Graphics2D g2){
		dirAnim.draw(g2);
	}

}
