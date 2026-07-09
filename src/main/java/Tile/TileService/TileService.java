package Tile.TileService;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import Util.ImageUtil;
import Util.ScaleAssetsUtil;
import Util.WorkWithFilesUtil;

public class TileService {
	public String name;
	public Tile[] tiles;
	int OriginalTileSize;
	int tileSizeForScale;
	WorkWithFilesUtil fileUtil = new WorkWithFilesUtil();

	public TileService(String name, int originalTileSize, int tileSizeForScale){
		this.name = name;
		this.OriginalTileSize = originalTileSize;
		this.tileSizeForScale = tileSizeForScale;
		setupTiles();
	}



	private final void setupTiles(){

		try{
			URL file = fileUtil.get("Assets/TileSheet/"+name+"_sheet.png");
			BufferedImage img = ImageIO.read(file);
			int height = img.getHeight()/OriginalTileSize;
			int width = (img.getWidth()%OriginalTileSize)+10;
			getId(height,width);
			getImages(img);
		} catch(IOException e){
				e.printStackTrace();
			}

	}

	private void getId(int height,int width){
		int idCount = height*width;
		tiles = new Tile[idCount+2];
		for(int i = 1; i<idCount+1;i++){
			tiles[i] = new Tile();
			tiles[i].id = i; 
		}
		tiles[0] = new Tile();
		tiles[0].id = 0;
	}

	private void getImages(BufferedImage originalImg){
		for(int i = 1; i<tiles.length-2;i++){
			int x = ((tiles[i].id-1)%10)*16;
			int y = ((tiles[i].id-1)/10)*16;
			BufferedImage img = originalImg.getSubimage(x, y, OriginalTileSize, OriginalTileSize);
			tiles[i].img = ScaleAssetsUtil.ScaleImage(tileSizeForScale, tileSizeForScale, img);
		}
		int x=7*16;
		int y=0;
		BufferedImage img = originalImg.getSubimage(x, y, OriginalTileSize, OriginalTileSize);
		img = ImageUtil.TransperentImg(img);
		tiles[0].img = ScaleAssetsUtil.ScaleImage(tileSizeForScale, tileSizeForScale, img);
	}
}
