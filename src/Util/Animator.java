package Util;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Animator {
	GamePanel gp;
	BufferedImage originalSpriteSheet;
	BufferedImage[][] frames;
	double frameRate;
	int frameCount;
	int x,y,curFrame,curStatus;
	double curTime;
	double lastTime;
	double timer;
	int statusCount;

	/*public Animator(GamePanel gp, BufferedImage[][] frames,double frameRate){
		this.gp = gp;
		this.frames = frames;
		this.frameRate = frameRate;
		
		lastTime = System.nanoTime();
		curFrame = 0;
		timer = 0;
	}*/
	
	public Animator(GamePanel gp, String path,double frameRate){
		this.gp = gp;
		this.frameRate = frameRate;
		frames = getListFrames(path);
		
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
			curFrame = (curFrame + 1) % frameCount;
			timer -= frameRate;
			}
			g2.drawImage(frames[curFrame][curStatus],x,y,gp.tileSize,gp.tileSize,null);
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
	
	public BufferedImage[][] getListFrames(String path){
		getMainImage(path);
		int size = gp.originalTitleSize;
		frameCount = originalSpriteSheet.getWidth()/size;
		statusCount = originalSpriteSheet.getHeight()/size;
		BufferedImage[][] sprites = new BufferedImage[frameCount][statusCount];
		for(int i = 0;i<=frameCount-1;i++){
			for(int j = 0;j<=statusCount-1;j++){
			BufferedImage sprite = originalSpriteSheet.getSubimage(i*size, j*size, size, size);
			sprites[i][j] = sprite;
			}
	}
	return sprites;
	}
	
	public void getMainImage(String path){
		try{
			originalSpriteSheet= ImageIO.read(getClass().getResourceAsStream(path));} catch(IOException e){
				e.printStackTrace();
			}
	}

}
