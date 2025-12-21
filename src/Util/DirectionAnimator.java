package Util;

import Main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import Entity.Entity;

public class DirectionAnimator {
	Entity character;
	GamePanel gp;
	double frameRate =  0.16;
	int dirCount = 1;
	BufferedImage mainImage;
	int size,dirCountFrames,curFrame;
	double curTime,lastTime,timer;
	BufferedImage sprites[][];
	String path;

	public DirectionAnimator(GamePanel gp, Entity character,String state,int dirCountFrames, int dirCount){
		this.character = character;
		this.dirCount = dirCount;
		this.gp = gp;
		this.dirCountFrames = dirCountFrames;
		size = gp.getOrignalTileSize();
		sprites = new BufferedImage[dirCount][dirCountFrames];
		path = "Assets/"+character.name+"/"+state+".png";
		getMainImage();
		getListFrames();
	}

	
	public void getMainImage(){
		try{
			File file = new File(path);
			mainImage = ImageIO.read(file);
		} catch(IOException e){
				e.printStackTrace();
			}
	}

	public void getListFrames(){
		BufferedImage sprite;
		for(int id = 0;id<=dirCount-1;id++){
		for (int i = 0;i<=dirCountFrames-1;i++){
			sprite = mainImage.getSubimage(i*size, id*size, size, size);
			sprites[id][i] = ScaleAssetsUtil.ScaleImage(gp.getTileSize(), gp.getTileSize(), sprite);
		}
	}
	}

	public void draw(Graphics2D g2,int x, int y){
				Anim(g2,character.direction.ordinal(), y, x);
	}
	
	public void Anim(Graphics2D g2, int id, int x, int y){
		if(timer>(frameRate*2)){
			timer =0;
		}
		curTime = System.nanoTime();
		timer += (curTime-lastTime)/1000000000;
		lastTime = curTime;
		if (timer > frameRate)
			{
			curFrame = (curFrame + 1) % dirCountFrames;
			timer=0;
			}
			g2.drawImage(sprites[id][curFrame],y,x,null); 
	}

}
