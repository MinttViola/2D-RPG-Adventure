package Util;

import Main.GamePanel;

public enum CollisionType {
	noneCol,onePart,twoParts,ThreeParts,fullCol;

	boolean[][] col;

	public boolean[][]GiveATypeForCorner(CollisionType type1,CollisionType type2, Direction dir1, Direction dir2, GamePanel gp)
	{boolean[][] mass = new boolean[gp.colDivisiorforTiles][gp.colDivisiorforTiles];
		return mass;
	}

	public boolean[][]GiveATypeForWall(CollisionType type,Direction dir, GamePanel gp){
		boolean[][] mass = new boolean[gp.colDivisiorforTiles][gp.colDivisiorforTiles];
		int col = gp.colDivisiorforTiles;
		int row = gp.colDivisiorforTiles;
		switch (dir) {
			case up:
				row = 0;
				break;
			case down:
				row = gp.colDivisiorforTiles-1;
				break;
			case right:
				col = gp.colDivisiorforTiles-1;
				break;
			case left:
				col = 0;
				break;
		}
		for(int i = 0; i<=gp.colDivisiorforTiles-1; i++){
			for(int j = 0; j<=gp.colDivisiorforTiles-1; j++){
				mass[i][j] = false;
				if (i ==row ||j==col){
				mass[i][j] = true;
				}
			}
		}
		return mass;
	}
	
}	
