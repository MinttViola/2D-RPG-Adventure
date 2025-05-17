package entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.main;

public class DirectionAnomator {
	Entity character;
	GamePanel gp;
	Direction dir = Direction.down;
	double frameRate =  0.16;
	int dirCount = 8;
	BufferedImage mainImage;
	boolean still;
	int size,dirCountFrames,curFrame;
	double curTime,lastTime,timer;
	BufferedImage sprites[][];

	public DirectionAnomator(GamePanel gp, Entity character,BufferedImage mainImage,int dirCountFrames){
		still=true;
		this.character = character;
		this.gp = gp;
		this.mainImage = mainImage;
		this.dirCountFrames = dirCountFrames;
		size = gp.originalTitleSize;
		sprites = new BufferedImage[dirCount][dirCountFrames];
		getListFrames();
	}

	public void getListFrames(){
		int count=0;
		BufferedImage sprite;
		for(int id = 0;id<=dirCount-1;id++){
			if(id==2||id==6)
			{count++;}
		for (int i = 0;i<=dirCountFrames-1;i++){
			sprite = mainImage.getSubimage(i*size, (id-count)*size, size, size);
			if(id==2||id==6)
			{sprite = HorizontalFlip(sprite);}
			sprites[id][i] = sprite;
		}
	}
	}

	public void draw(Graphics2D g2){
		if(still){
 	switch (dir) {
			case down:
				Anim(g2,0);
				break;
			case right:
				Anim(g2,1);
				break;
			case left:
				Anim(g2,2);
				break;
			case up:
				Anim(g2,3);
				break;
			default:
				break;}
		}else{
		switch (dir) {
			case down:
				Anim(g2,4);
				break;
			case right:
				Anim(g2,5);
				break;
			case left:
				Anim(g2,6);
				break;
			case up:
				Anim(g2,7);
				break;
			default:
				break;}}
	}
	
	public void Anim(Graphics2D g2, int id){
		if(timer>(frameRate*2)){
			timer =0;
		}
		int x = character.x;
		int y = character.y;
		curTime = System.nanoTime();
		timer += (curTime-lastTime)/1000000000;
		lastTime = curTime;
		if (timer > frameRate)
			{
			curFrame = (curFrame + 1) % dirCountFrames;
			timer=0;
			}
			g2.drawImage(sprites[id][curFrame],x,y,gp.tileSize,gp.tileSize,null); 
	}

	public static BufferedImage HorizontalFlip(BufferedImage img){
		//flip img y 
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getHeight(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		img = op.filter(img, null);
		return img;
	}

}
