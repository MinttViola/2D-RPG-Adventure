package Main;

import Util.Coordinate;
import entity.Entity;

public class CollisionChecker {
	GamePanel gp;

	public CollisionChecker(GamePanel gp){
		this.gp = gp;
	}

	public void checkTiles(Entity entity){
		//a(0)        b+ay(1)
		//
		//b-ax(2)     b(3)
		Coordinate[] enityColPos = new Coordinate[4];//enity collision position;
		enityColPos[0]= new Coordinate(entity.solidArea.x+entity.worldX,entity.solidArea.y+entity.worldY);
		enityColPos[1] = new Coordinate(entity.solidArea.x+entity.solidArea.width+entity.worldX,entity.solidArea.y+entity.worldY);
		enityColPos[2] = new Coordinate(entity.solidArea.x+entity.worldX,entity.solidArea.y+entity.solidArea.height+entity.worldY);
		enityColPos[3] = new Coordinate(entity.solidArea.x+entity.solidArea.width+entity.worldX,entity.solidArea.y+entity.solidArea.width+entity.worldY);

		switch (entity.dir) {
			case down:
			checkCol(enityColPos[2], enityColPos[3], false, entity);
				break;
			case right:
			checkCol(enityColPos[1], enityColPos[3], true, entity);
				break;
			case left:
			checkCol(enityColPos[0], enityColPos[2], false, entity);
				break;
			case up:
			checkCol(enityColPos[0], enityColPos[1], true, entity);
				break;
			default:
				break;}
	}

	public void checkCol(Coordinate one, Coordinate two, boolean dir, Entity entity){
		if(one.y==two.y){
			if(dir){
			one.y += entity.speed;
			two.y += entity.speed;
			}else{
			one.y -= entity.speed;
			two.y -= entity.speed;
			}
		}else{
			if(dir){
			one.x += entity.speed;
			two.x += entity.speed;
			}else{
			one.x -= entity.speed;
			two.x -= entity.speed;
			}}
		int tileOne = gp.tileM.map[(one.x/gp.tileSize)][(one.y/gp.tileSize)];
		int tileTwo = gp.tileM.map[(two.x/gp.tileSize)][(two.y/gp.tileSize)];
		if(gp.tileM.tile[tileOne].collision == true ||gp.tileM.tile[tileTwo].collision == true){
			entity.collisionOn = false;
		}
	}


}
