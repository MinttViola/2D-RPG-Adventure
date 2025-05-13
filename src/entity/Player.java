package entity;


import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	PersonAnimator anim;

	public Player(GamePanel gp,KeyHandler keyH){
		this.keyH=keyH;
		this.gp=gp;
		anim = new PersonAnimator(gp,this);
		setDefaultValues();
	}

	public void setDefaultValues(){
		x=4;
		y=4;
		speed = 1;
	}

	public void update(){
		switch (keyH.yChange) {
			case 1:
				y +=speed;
				anim.dir =Direction.right;
				anim.still=false;
				break;
			case -1:
				y -=speed;
				anim.dir =Direction.left;
				anim.still=false;
				break;
			default:
				anim.still=true;
				break;
		}		switch (keyH.xChange) {
			case 1:
				x +=speed;
				anim.dir =Direction.up;
				anim.still=false;
				break;
			case -1:
				x -=speed;
				anim.dir =Direction.down;
				anim.still=false;
				break;
			default:
				anim.still=true;
				break;
		}

	}

	public void draw(Graphics2D g2){
		anim.draw(g2);
	}

}
