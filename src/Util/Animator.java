package Util;

import Main.GamePanel;
import Service.ScaleAssetsService;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Animator {
	GamePanel gp;
	BufferedImage originalSpriteSheet;
	BufferedImage[][] frames;
	double frameRate;
	int frameCount;
	public int curFrame;
	public int curStatus=0;
	double curTime,lastTime,timer;
	int[][] statusCount;


	
	public Animator(GamePanel gp, String path,double frameRate){
		frameCount =100;
		this.gp = gp;
		this.frameRate = frameRate;
		frames = getListFrames(path);
		loopFind(2);
		lastTime = System.nanoTime();
		curFrame = 0;
		timer = 0;
	}

	public void nextStatus(){
		if(curStatus+1<statusCount.length){
			curStatus+=1;
		}else{
			curStatus = (-1);
		}
	}

	public void Res()
	{
		lastTime = System.nanoTime();
		curFrame = 0;
		timer = 0;
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
			sprite = ScaleAssetsService.ScaleImage(gp.tileSize, gp.tileSize, sprite);
			sprites[i][j] = sprite;}
			else{
				frameCount =j;
				statusCount[i][0]=j;
				break;}
			if(j==statusCount[0][0]-1){
				statusCount[i][0]=j;
			}
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
			File file = new File(path);
			originalSpriteSheet= ImageIO.read(file);
		} catch(IOException e){
				e.printStackTrace();
			}
	}
	
	public void draw(Graphics2D g2, int x, int y){
		if(curStatus == -1){
			return;
		}
		frameCount = statusCount[curStatus][0];
		if(frameCount==1){
			g2.drawImage(frames[0][curStatus],y,x,null);
		}
		if (statusCount[curStatus][1]==1)
			frameRate=0.3;
		if(timer>(frameRate*2)){
			timer =0;
		}
		curTime = System.nanoTime();
		timer += (curTime-lastTime)/1000000000;
		lastTime=curTime;

		g2.drawImage(frames[curStatus][curFrame],x,y,null);

		if (timer > frameRate)
		{
		curFrame = (curFrame + 1) % frameCount;
		timer -= frameRate;
		}
		if (statusCount[curStatus][1]==1&&curFrame==0)
			nextStatus();
	}

}
