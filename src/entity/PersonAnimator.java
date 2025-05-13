package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class PersonAnimator {
	Player player;
	GamePanel gp;
	Direction dir = Direction.down;
	float frameRate = 0.16f;
	int dirCountFrames = 6;
	BufferedImage mainImage;
	boolean still=true;

	ArrayList<BufferedImage> upStill= new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> downStill= new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> rightStill= new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> leftStill= new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> upWalk= new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> downWalk= new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> rightWalk= new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> leftWalk= new ArrayList<BufferedImage>();
	Animator upStillAnim;
	Animator downStillAnim;
	Animator rightStillAnim;
	Animator leftStillAnim;
	Animator upWalkAnim;
	Animator downWalkAnim;
	Animator rightWalkAnim;
	Animator leftWalkAnim;

	


	public PersonAnimator(GamePanel gp, Player player){
		this.player = player;
		this.gp = gp;
		getMainImage();
		getListFrames(dirCountFrames, 0, downStill,false);
		getListFrames(dirCountFrames, 1, rightStill,false);
		getListFrames(dirCountFrames, 1, leftStill,true);
		getListFrames(dirCountFrames, 2, upStill,false);
		getListFrames(dirCountFrames, 3, downWalk,false);
		getListFrames(dirCountFrames, 4, rightWalk,false);
		getListFrames(dirCountFrames, 4, leftWalk,true);
		getListFrames(dirCountFrames, 5, upWalk,false);
		upStillAnim = new Animator(gp, upStill, frameRate, false,player.x,player.y);
		downStillAnim = new Animator(gp, downStill, frameRate, false,player.x,player.y);
		rightStillAnim = new Animator(gp, rightStill, frameRate, false,player.x,player.y);
		leftStillAnim = new Animator(gp, leftStill, frameRate, false,player.x,player.y);
		upWalkAnim = new Animator(gp, upWalk, frameRate, false,player.x,player.y);
		downWalkAnim = new Animator(gp, downWalk, frameRate, false,player.x,player.y);
		rightWalkAnim = new Animator(gp, rightWalk, frameRate, false,player.x,player.y);
		leftWalkAnim = new Animator(gp, leftWalk, frameRate, false,player.x,player.y);
	}

	public void getMainImage(){
		try{
			mainImage= ImageIO.read(getClass().getResourceAsStream("/Assets/Player/Player.png"));} catch(IOException e){
				e.printStackTrace();
			}
	}

	public void getListFrames(int count, int id,ArrayList<BufferedImage> list, boolean mirror){
		BufferedImage sprite;
		BufferedImage img = mainImage;
		if(mirror){
			img = Animator.HorizontalFlip(img);
		}
		for (int i = 0;i<=count-1;i++){
			System.out.println("id"+id+"i"+i);
			sprite = img.getSubimage(i*gp.originalTitleSize, id*gp.originalTitleSize, gp.originalTitleSize, gp.originalTitleSize);
			list.add(sprite);
		}

	}


	public void draw(Graphics2D g2){
		if(still){
		switch (dir) {
			case right:
				rightStillAnim.draw(g2);
				break;
			case left:
				leftStillAnim.draw(g2);
				break;
			case down:
				downStillAnim.draw(g2);
				break;
			case up:
				upStillAnim.draw(g2);
				break;
		
			default:
				break;}
		}
		switch (dir) {
			case right:
				rightWalkAnim.draw(g2);
				break;
			case left:
				leftWalkAnim.draw(g2);
				break;
			case down:
				downWalkAnim.draw(g2);
				break;
			case up:
				upWalkAnim.draw(g2);
				break;
		
			default:
				break;}
	}
}
