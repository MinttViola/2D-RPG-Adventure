package Tile.TileService;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import Main.GamePanel;
import Util.WorkWithFilesUtil;
import WorkWithJson.MapLayerEnum;

public class LayerService {
	GamePanel gp;
	WorkWithFilesUtil fileUtil = new WorkWithFilesUtil();
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
		try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {

				if (is == null) {
						throw new RuntimeException("Level file not found: " + path);
				}

				try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
						for (int i = 0; i < gp.getMaxWorldCol(); i++) {
								String line = br.readLine();
								String[] numbers = line.split(",\\s*"); // убираем пробелы
								for (int j = 0; j < gp.getMaxWorldRow(); j++) {
										int num = Integer.parseInt(numbers[j]);
										map[i][j] = num;
								}
						}
				}

		} catch (Exception e) {
				e.printStackTrace();
		}
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
