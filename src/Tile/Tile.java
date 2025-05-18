package Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {

	public int id;
	public String name;
	public BufferedImage img;
	public boolean collision = false;
	public Tile(int id, String name, boolean col){
		collision = col;
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

}
