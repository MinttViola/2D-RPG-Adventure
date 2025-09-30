package Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TileSet {
	public int[] id;
	public String name;
	public BufferedImage[] tileSheet;
	public boolean collision = false;
	int size;
	public TileSet(int firstId, int lastId, String name, int size){
		this.name = name;
		this.size = size;
		getId(firstId, lastId);
		getImage();
	}

	private final void getImage(){
		try{
			BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/Assets/TileSheet/sheet.png"));
			int height = (id[0]/10)*size;
			int width = id.length*size;
			img = img.getSubimage(0, height, width, size);
			cutImage(img);
		} catch(IOException e){
				e.printStackTrace();
			}

	}

	private void cutImage(BufferedImage originalImg){
		tileSheet = new BufferedImage[id.length];
		for(int i = 0; i< id.length;i++){
			tileSheet[i] = originalImg.getSubimage(i*size, 0, size, size);
		}
	}

	private void getId(int firstId, int lastId){
		int idCount = lastId-firstId;
		id = new int[idCount+1];
		for(int i = 0; i<idCount+1;i++){
			id[i]= firstId+i;
		}
	}

}
