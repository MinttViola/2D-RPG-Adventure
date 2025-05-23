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
	public int curFrame;
	int curStatus=0;
	double curTime,lastTime,timer;
	int[][] statusCount;


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
		loopFind(2);
		lastTime = System.nanoTime();
		curFrame = 0;
		timer = 0;
	}

	public void nextStatus(){
		if(curStatus+1<=statusCount.length){
			curStatus+=1;
		}
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
		statusCount = new int[originalSpriteSheet.getHeight()/size][2];
		statusCount[0][0] = originalSpriteSheet.getWidth()/size;
		BufferedImage[][] sprites = new BufferedImage[statusCount.length][statusCount[0][0]];
		for(int i = 0;i<=statusCount.length-1;i++){
			for(int j = 0;j<=statusCount[0][0]-1;j++){
			BufferedImage sprite = originalSpriteSheet.getSubimage(j*size, i*size, size, size);
			if(!zeroSprite(sprite, size)){
			sprites[i][j] = sprite;}
			else{
				statusCount[i][0]=j;
				break;}
			}
	}
	return sprites;
	}

	public void loopFind(int num){
		for(int i=0;i<statusCount.length;i++){
			if(i==num-1)
				statusCount[i][1] = 1;
			else
				statusCount[i][1] = 0;
		}
	}

	public boolean zeroSprite(BufferedImage img,int size){
		for(int i = 0;i<=size-1;i++){
			for(int j = 0;j<=size-1;j++){
				int pixel = img.getRGB(j, i);
				int alpha = (pixel >> 24) & 0xff;
				if (alpha != 0) {
						return false;
				}	
			}}
		return true;
	}
	
	public void getMainImage(String path){
		try{
			originalSpriteSheet= ImageIO.read(getClass().getResourceAsStream(path));} catch(IOException e){
				e.printStackTrace();
			}
	}
	
	public void draw(Graphics2D g2, int x, int y){
		frameCount = statusCount[curStatus][0];
		if(frameCount==1){
			g2.drawImage(frames[0][curStatus],x,y,gp.tileSize,gp.tileSize,null);
		}
		if(timer>(frameRate*2)){
			timer =0;
		}
		curTime = System.nanoTime();
		timer += (curTime-lastTime)/1000000000;
		lastTime=curTime;

		g2.drawImage(frames[curStatus][curFrame],x,y,gp.tileSize,gp.tileSize,null);

		if (timer > frameRate)
		{
		curFrame = (curFrame + 1) % frameCount;
		timer -= frameRate;
		}
		if (statusCount[curStatus][1]==1&&curFrame==0)
			nextStatus();
	}

}
