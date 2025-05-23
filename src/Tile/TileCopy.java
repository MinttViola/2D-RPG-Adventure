package Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileCopy {
	public GamePanel gp;

	public int id;
	public String name;
	public BufferedImage img;
	public boolean[][] collision = new boolean[4][4];
	public TileCopy(int id, String name, boolean col, GamePanel gp){
		this.gp = gp;
		this.id = id;
		this.name = name;
		getImage();
	}

	public void getImage(){
		try{
			img = ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/"+name+".png"));
		} catch(IOException e){
				e.printStackTrace();
			}

	}

	public void collisionGiver(){

	}

}
