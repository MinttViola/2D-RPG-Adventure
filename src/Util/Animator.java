package Util;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Main.GamePanel;

public class Animator {
	GamePanel gp;
	ArrayList<BufferedImage> frames;
	double frameRate;
	int x,y,curFrame;
	double curTime;
	double lastTime;
	double timer;

	public Animator(GamePanel gp, ArrayList<BufferedImage> frames,double frameRate){
		this.gp = gp;
		this.frames = frames;
		this.frameRate = frameRate;
		
		lastTime = System.nanoTime();
		curFrame = 0;
		timer = 0;
	}

	public void draw(Graphics2D g2, int x, int y){
		this.x = x;
		this.y = y;
		curTime = System.nanoTime();
		timer += (curTime-lastTime)/1000000000;
		lastTime=curTime;
		if (timer > frameRate)
			{
			curFrame = (curFrame + 1) % frames.size();
			timer -= frameRate;
			}
			g2.drawImage(frames.get(curFrame),x,y,gp.tileSize,gp.tileSize,null);
	}

	public void Res()
	{
		lastTime = System.nanoTime();
		curFrame = 0;
		timer = 0;
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
