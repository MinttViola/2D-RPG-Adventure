package Tile.TileService;

import java.awt.Graphics2D;
import WorkWithJson.ModelsForJson.LayerModel;

import Main.GamePanel;
public class LayerService {
GamePanel gp;
int tileSize;
String name;
TileService tileSet;
public int[][] map;
public boolean collisionOn;

public LayerService(GamePanel gp, LayerModel layerModel, TileService tileService) {
		this.gp = gp;
		this.tileSize = gp.getTileSize();
		this.name = layerModel.getName();
		this.tileSet = tileService;
		this.collisionOn = layerModel.isVisible();
		this.map = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
		convert1DTo2D(layerModel.getData(), layerModel.getWidth(), layerModel.getHeight());
}

private void convert1DTo2D(int[] data, int width, int height) {
	for (int row = 0; row < height; row++) {
		for (int col = 0; col < width; col++) {
			int index = row * width + col;
			if (index < data.length && col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
					map[col][row] = data[index];
			}
		}
	}
}

public void draw(Graphics2D g2) {
	for (int worldRow = 0; worldRow < gp.getMaxWorldRow(); worldRow++) {
		for (int worldCol = 0; worldCol < gp.getMaxWorldCol(); worldCol++) {
			int tileID = map[worldCol][worldRow];
			int worldX = worldCol * gp.getTileSize();
			int worldY = worldRow * gp.getTileSize();
			int screenX = gp.getPlayer().xPlaceIfCanSee(worldX);
			int screenY = gp.getPlayer().yPlaceIfCanSee(worldY);
			g2.drawImage(tileSet.tiles[tileID].img, screenX, screenY, null);
		}
	}
}




	/*
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
		try (
			InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
				if (is == null) {
						throw new RuntimeException("Level file not found: " + path);
				}
				try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
						for (int i = 0; i < gp.getMaxWorldCol(); i++) {
								String line = br.readLine();
								String[] numbers = line.split(",\\s*");
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
			int screenX = gp.getPlayer().xPlaceIfCanSee(worldX);
			int screenY = gp.getPlayer().yPlaceIfCanSee(worldY);
			
			int tileID = map[worldCol][worldRow];
			g2.drawImage(tileSet.tiles[tileID].img, screenY, screenX, null);
			worldCol ++;
			if(worldCol==gp.getMaxWorldCol()){
				worldCol = 0;
				worldRow++;
			}
		}
	}
*/
}
