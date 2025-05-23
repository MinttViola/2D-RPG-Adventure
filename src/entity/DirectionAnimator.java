package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class DirectionAnimator {
	Entity character;
	GamePanel gp;
	double frameRate =  0.16;
	int dirCount = 4;
	BufferedImage mainImage;
	int size,dirCountFrames,curFrame;
	double curTime,lastTime,timer;
	BufferedImage sprites[][];
	String path;

	public DirectionAnimator(GamePanel gp, Entity character,String state,int dirCountFrames){
		this.character = character;
		this.gp = gp;
		this.mainImage = mainImage;
		this.dirCountFrames = dirCountFrames;
		size = gp.originalTitleSize;
		sprites = new BufferedImage[dirCount][dirCountFrames];
		path = "/Assets/Player/"+state+".png";
		getMainImage();
		getListFrames();
	}

	
	public void getMainImage(){
		try{
			mainImage= ImageIO.read(getClass().getResourceAsStream(path));} catch(IOException e){
				e.printStackTrace();
			}
	}

	public void getListFrames(){
		BufferedImage sprite;
		for(int id = 0;id<=dirCount-1;id++){
		for (int i = 0;i<=dirCountFrames-1;i++){
			sprite = mainImage.getSubimage(i*size, id*size, size, size);
			sprites[id][i] = sprite;
		}
	}
	}

	public void draw(Graphics2D g2,int x, int y){
 	switch (character.dir) {
			case down:
				Anim(g2,character.dir.ordinal(), x, y);
				break;
			case right:
				Anim(g2,character.dir.ordinal(), x, y);
				break;
			case left:
				Anim(g2,character.dir.ordinal(), x, y);
				break;
			case up:
				Anim(g2,character.dir.ordinal(), x, y);
				break;
			default:
				break;}
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
			g2.drawImage(sprites[id][curFrame],x,y,gp.tileSize,gp.tileSize,null); 
	}

}
