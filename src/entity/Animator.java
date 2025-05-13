package entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.ArrayList;

import Main.GamePanel;

public class Animator {
	GamePanel gp;
	ArrayList<BufferedImage> frames;
	float frameRate;
	boolean oneAnimCycle;
	int curFrame;
	float timer;
	boolean stop = false;
	int x, y;

	public Animator(GamePanel gp, ArrayList<BufferedImage> frames,float frameRate,boolean oneAnimCycle, int x, int y){
		this.gp = gp;
		this.frames = frames;
		this.frameRate = frameRate;
		this.oneAnimCycle = oneAnimCycle;
		this.x = x;
		this.y = y;
		Start();
	}

	public void Start(){
		curFrame = 0;
		timer = 0;
	}

	public void draw(Graphics2D g2){
		double drawInterval = 1000000000/gp.FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long curTime;
		long timer = 0;
		int drawCount = 0;
		curTime = System.nanoTime();
		delta +=(curTime-lastTime)/drawInterval;
		timer +=(curTime-lastTime);
		lastTime=curTime;
		if (curFrame == 0 && oneAnimCycle == true)
				curFrame++;
		if (timer > frameRate&&stop == false)
				{
						curFrame = (curFrame + 1) % frames.size();
						g2.drawImage(frames.get(curFrame),x,y,gp.tileSize,gp.tileSize,null);
						timer -= frameRate;
				}
		if (curFrame == 0 && oneAnimCycle == true)
		{
				stop = true;
				oneAnimCycle = false;
		}
	}
	
	public static BufferedImage HorizontalFlip(BufferedImage img){
		//flip img y 
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -img.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		img = op.filter(img, null);
		return img;
	}

}
