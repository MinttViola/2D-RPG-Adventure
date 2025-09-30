package Tile;

import Main.GamePanel;
import Service.ScaleAssetsService;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileService {

	GamePanel gp;
	public Tile[] tile;
	public TileSet[] tileSets;
	int countTileTypes;
	int tileSize;
	public int[][] map;

	public TileService(GamePanel gp, int countTileTypes){
		this.gp = gp;
		tileSize = gp.tileSize;
		map = new int[gp.maxWorldRow][gp.maxWorldCol];
		loadMap(1);
		this.countTileTypes = countTileTypes;
		tile = new Tile[countTileTypes];
		tileSets = new TileSet[12];
		
		getTileImage(0, "Grass",false);
		getTileImage(1, "Path",false);
		getTileImage(2, "Water",true);
		getTileImage(3, "Stone",true);
		getTileSets("SolidTiles", 1, 4);
		getTileSets("Hill1", 10, 18);
		getTileSets("Hill2", 20, 28);
		getTileSets("HillRise", 30, 35);
		getTileSets("Decor1", 40, 49);
		getTileSets("DecorNatureWOCol",50, 56);
		getTileSets("DecorNatureWithCol", 60, 67);
		getTileSets("PathAngleSmall", 70, 78);
		getTileSets("Path", 80, 86);
		getTileSets("PathEnd", 90, 94);
		getTileSets("PathAngleBig", 100, 108);
		getTileSets("Cliff", 110, 114);


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

	public void TestMap(int[][] map){
		for(int i = 0; i<=gp.maxWorldCol-1;i++){
			for(int j = 0; j<=gp.maxWorldRow-1;j++){
				System.out.print(map[i][j]);
			}System.out.println();}
	}


	public void getTileImage(int id,String name,boolean coll){
		tile[id] = new Tile(id, name, coll);
		tile[id].img = ScaleAssetsService.ScaleImage(gp.tileSize, gp.tileSize, tile[id].img);
	}
	public void getTileSets(String name, int firstId,int lastId){
		int id = firstId/10;
		tileSets[id] = new TileSet(firstId, lastId, name, gp.originalTitleSize);
	}

	public void draw(Graphics2D g2){
			for(int i = 0; i<=gp.maxWorldRow-1;i++){
				int screenX = (i*gp.tileSize)-gp.player.worldX+gp.player.screenX;
				for(int j = 0; j<=gp.maxWorldCol-1;j++){
					int screenY = (j*gp.tileSize)-gp.player.worldY+gp.player.screenY;
					if(((i+2)*gp.tileSize)>gp.player.worldX - gp.player.screenX&&((i-2)*gp.tileSize)<gp.player.worldX+gp.player.screenX&&((j+2)*gp.tileSize)>gp.player.worldY - gp.player.screenY&&((j-2)*gp.tileSize)<gp.player.worldY+gp.player.screenY){
					int tileID = map[i][j];
					g2.drawImage(tile[tileID].img, screenX, screenY, null);}
				}
				
			}
	}

}
