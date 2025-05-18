package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	int countTileTypes;
	int tileSize;
	int[][] map;

	public TileManager(GamePanel gp, int countTileTypes){
		this.gp = gp;
		tileSize = gp.tileSize;
		map = new int[gp.maxWorldRow][gp.maxWorldCol];
		loadMap(1);
		this.countTileTypes = countTileTypes;
		tile = new Tile[countTileTypes];
		
		getTileImage(0, "Grass",false);
		getTileImage(1, "Path",false);
		getTileImage(2, "Water",true);
		getTileImage(3, "Stone",true);

	}
	
	public void loadMap(int lvl){
		String path = "/Assets/Maps/lvl"+lvl+".txt";
		try{
			InputStream is = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			for(int i = 0; i<=gp.maxWorldRow-1;i++){
				String line = br.readLine();
				String numbers[] = line.split(" ");
				for(int j = 0; j<=gp.maxWorldCol-1;j++){
					int num = Integer.parseInt(numbers[j]);
					map[i][j] = num;
				}
			}
			br.close();
		}catch(Exception e){}
	}

	public void getTileImage(int id,String name,boolean coll){
		tile[id] = new Tile(id, name, coll);
	}

	public void draw(Graphics2D g2){
			for(int i = 0; i<=gp.maxWorldRow-1;i++){
				for(int j = 0; j<=gp.maxWorldCol-1;j++){
					int screenX = (i*gp.tileSize)-gp.player.worldX+gp.player.screenX;
					int screenY = (j*gp.tileSize)-gp.player.worldY+gp.player.screenY;
					if(((i+2)*gp.tileSize)>gp.player.worldX - gp.player.screenX&&((i-2)*gp.tileSize)<gp.player.worldX+gp.player.screenX&&((j+2)*gp.tileSize)>gp.player.worldY - gp.player.screenY&&((j-2)*gp.tileSize)<gp.player.worldY+gp.player.screenY){
					int tileID = map[i][j];
					g2.drawImage(tile[tileID].img, screenX, screenY,tileSize,tileSize, null);}
				}
			}
	}

}
