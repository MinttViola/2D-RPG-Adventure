package Tile.TileService;

import Main.GamePanel;
import WorkWithJson.MapLayerEnum;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LayerService {
	GamePanel gp;
	int tileSize;
	String name;
	TileService tileSet;
	int level;
	public int[][] map;
	public boolean collisionOn;

	public LayerService(GamePanel gp, int level, String name, TileService tileService){
		this.gp = gp;
		tileSize = gp.getTileSize();
		this.name = name;
		this.level = level;
		tileSet = tileService;
		collisionOn = MapLayerEnum.getColByName(name);
		map = new int[gp.getMaxWorldRow()][gp.getMaxWorldCol()];
		loadLevel();
	}
	


	public void loadLevel(){
		String path = "Assets/Levels/"+level+"/"+name+".txt";
		try{
			File is = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(is));
			for(int i = 0; i<=gp.getMaxWorldCol()-1;i++){
				String line = br.readLine();
				String numbers[] = line.split(", ");
				for(int j = 0; j<=gp.getMaxWorldRow()-1;j++){
					int num = Integer.parseInt(numbers[j]);
					map[i][j] = num;
				}
			}
			br.close();
		}catch(Exception e){}
	}

	public void TestMap(int[][] map){
		for(int i = 0; i<=gp.getMaxWorldCol()-1;i++){
			for(int j = 0; j<=gp.getMaxWorldRow()-1;j++){
				System.out.print(map[i][j]);
			}System.out.println();}
	}

	public void draw(Graphics2D g2){
		int worldCol = 0;
		int worldRow = 0;
		while(worldCol<gp.getMaxWorldCol() && worldRow<gp.getMaxWorldRow()){
			int worldX = worldCol*gp.getTileSize();
			int worldY = worldRow*gp.getTileSize();
			int screenX = gp.player.xPlaceIfCanSee(worldX);
			int screenY = gp.player.yPlaceIfCanSee(worldY);
			
			int tileID = map[worldCol][worldRow];
			g2.drawImage(tileSet.tiles[tileID].img, screenY, screenX, null);
			worldCol ++;
			if(worldCol==gp.getMaxWorldCol()){
				worldCol = 0;
				worldRow++;
			}
		}
	}

}
