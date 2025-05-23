package Main;

import java.awt.Rectangle;

import Util.Coordinate;
import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	Coordinate one,two,zero;

	public CollisionChecker(GamePanel gp){
		this.gp = gp;
	}

	public void checker(Entity entity){
		//a(0)        b+ay(1)
		//
		//b-ax(2)     b(3)
		Coordinate[] enityColPos = new Coordinate[4];//enity collision position;
		enityColPos[0]= new Coordinate(entity.solidArea.x+entity.worldX,entity.solidArea.y+entity.worldY);
		enityColPos[1] = new Coordinate(entity.solidArea.x+entity.solidArea.width+entity.worldX,entity.solidArea.y+entity.worldY);
		enityColPos[2] = new Coordinate(entity.solidArea.x+entity.worldX,entity.solidArea.y+entity.solidArea.height+entity.worldY);
		enityColPos[3] = new Coordinate(entity.solidArea.x+entity.solidArea.width+entity.worldX,entity.solidArea.y+entity.solidArea.width+entity.worldY);
		zero = new Coordinate(0, 0);

		switch (entity.dir) {
			case down:
			nextMove(enityColPos[2], enityColPos[3], false, entity);
			zero.y = two.y - gp.tileSize;
			zero.x = two.x - gp.tileSize;
				break;
			case right:
			nextMove(enityColPos[1], enityColPos[3], true, entity);
			zero.y = two.y - gp.tileSize;
			zero.x = two.x - gp.tileSize;
				break;
			case left:
			nextMove(enityColPos[0], enityColPos[2], false, entity);
			zero = one;
			case up:
			nextMove(enityColPos[0], enityColPos[1], true, entity);
			zero = one;
				break;
			default:
				break;}
			checkColTiles(entity);
			checkSuperObj(entity);

	}

	public void nextMove(Coordinate first, Coordinate second, boolean dir, Entity entity){
		one = first;
		two = second;
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
	}

	public void checkColTiles(Entity entity){
		int tileOne = gp.tileM.map[(one.x/gp.tileSize)][(one.y/gp.tileSize)];
		int tileTwo = gp.tileM.map[(two.x/gp.tileSize)][(two.y/gp.tileSize)];
		if(gp.tileM.tile[tileOne].collision == true ||gp.tileM.tile[tileTwo].collision == true){
			entity.collisionOn = false;
		}
	}

	public void checkSuperObj(Entity entity){
		if(!entity.isPlayer){
			return;
		}
		for(int i = 0; i<=gp.obj.length-1;i++){
			Rectangle col = new Rectangle(zero.x, zero.y, gp.tileSize, gp.tileSize);
			if(gp.obj[i]!=null){
				if(col.intersects(gp.obj[i].solidArea)){
					/*if(gp.obj[i].collision == true){
					entity.collisionOn = false;}*/
				gp.player.itemIteract(gp.obj[i]);
				}
			}
		}
	}


}
