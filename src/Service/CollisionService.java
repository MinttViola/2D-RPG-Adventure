package Service;

import Entity.Entity;
import Main.GamePanel;
import Util.Coordinate;
import java.awt.Rectangle;

public class CollisionService {
	GamePanel gp;
	Coordinate one,two,zero;

	public CollisionService(GamePanel gp){
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

		switch (entity.direction) {
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
			checkEntity(entity);
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
		if(entity.isPlayer)
		for(int i =0;i<gp.layersCount;i++){
			if(gp.layersS[i].collisionOn){
			int tileOne = gp.layersS[i].map[(one.x/gp.tileSize)][(one.y/gp.tileSize)];		
			int tileTwo = gp.layersS[i].map[(two.x/gp.tileSize)][(two.y/gp.tileSize)];
			if(tileOne !=0||tileTwo!=0){
				entity.collisionOn = false;
			}
			}
		}
	}

	public void checkSuperObj(Entity entity){
		if(!entity.isPlayer)
			return;
		for(int i = 0; i<=gp.obj.length-1;i++){
			Rectangle col = new Rectangle(zero.x, zero.y, gp.tileSize, gp.tileSize);
			if(gp.obj[i]!=null){
				if(col.intersects(gp.obj[i].solidArea)){
					if(gp.obj[i].collision == true){
					entity.collisionOn = false;}
				gp.player.itemIteract(gp.obj[i]);
				}
			}
		}
	}

	public void checkEntity(Entity entity){
		if(entity.isPlayer)
		for(int i = 0; i<=gp.npc.length-1;i++){
			Rectangle col = new Rectangle(zero.x, zero.y, gp.tileSize, gp.tileSize);
			if(gp.npc[i]!=null){
				if(col.intersects(gp.npc[i].solidArea)){
					gp.npc[i].playerInteract();
					gp.player.NPCIteract(gp.npc[i]);
				}
			}
		}
	} 
}
