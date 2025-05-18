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
		enityColPos[0]= new Coordinate(entity.solidArea.x,entity.solidArea.y);
		enityColPos[1] = new Coordinate(entity.solidArea.x+entity.solidArea.width,entity.solidArea.y);
		enityColPos[2] = new Coordinate(entity.solidArea.x,entity.solidArea.y+entity.solidArea.height);
		enityColPos[3] = new Coordinate(entity.solidArea.x+entity.solidArea.width,entity.solidArea.y+entity.solidArea.height);
		switch (entity.dir) {
			case down:
				break;
			case right:
				break;
			case left:
				break;
			case up:
				break;
			default:
				break;}
	}

	public void checkCol(){
		
	}


}
