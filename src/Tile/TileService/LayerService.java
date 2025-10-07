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
		tileSize = gp.tileSize;
		this.name = name;
		this.level = level;
		tileSet = tileService;
		collisionOn = MapLayerEnum.getColByName(name);
		map = new int[gp.maxWorldRow][gp.maxWorldCol];
		loadLevel();
	}
	


	public void loadLevel(){
		String path = "Assets/Levels/"+level+"/"+name+".txt";
		try{
			File is = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(is));
			for(int i = 0; i<=gp.maxWorldCol-1;i++){
				String line = br.readLine();
				String numbers[] = line.split(", ");
				for(int j = 0; j<=gp.maxWorldRow-1;j++){
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

	public void draw(Graphics2D g2){
		int worldCol = 0;
		int worldRow = 0;
		while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
			int worldX = worldCol*gp.tileSize;
			int worldY = worldRow*gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			int tileID = map[worldCol][worldRow];
			g2.drawImage(tileSet.tiles[tileID].img, screenY, screenX, null);
			worldCol ++;
			if(worldCol==gp.maxWorldCol){
				worldCol = 0;
				worldRow++;
			}
		}
	}

}
