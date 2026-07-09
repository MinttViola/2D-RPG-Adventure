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
		Coordinate[] enityColPos = new Coordinate[4];
    enityColPos[0] = new Coordinate(entity.solidArea.x + entity.worldX, entity.solidArea.y + entity.worldY);
    enityColPos[1] = new Coordinate(entity.solidArea.x + entity.solidArea.width + entity.worldX, entity.solidArea.y + entity.worldY);
    enityColPos[2] = new Coordinate(entity.solidArea.x + entity.worldX, entity.solidArea.y + entity.solidArea.height + entity.worldY);
    enityColPos[3] = new Coordinate(entity.solidArea.x + entity.solidArea.width + entity.worldX, entity.solidArea.y + entity.solidArea.height + entity.worldY); 
    zero = new Coordinate(0, 0);
    switch (entity.direction) {
			case down:
				nextMove(enityColPos[2], enityColPos[3], true, entity); 
				zero.y = two.y - gp.getTileSize();
				zero.x = two.x - gp.getTileSize();
				break;
			case right:
				nextMove(enityColPos[1], enityColPos[3], true, entity);
				zero.y = two.y - gp.getTileSize();
				zero.x = two.x - gp.getTileSize();
				break;
			case left:
				nextMove(enityColPos[0], enityColPos[2], false, entity);
				zero = one;
				break; 
			case up:
				nextMove(enityColPos[0], enityColPos[1], false, entity); 
				zero = one;
				break;
			default:
				break;
    }
    checkColTiles(entity);
    checkSuperObj(entity);
    checkEntity(entity);
	}

	public void nextMove(Coordinate first, Coordinate second, boolean dir, Entity entity){
    one = first;
    two = second;
    if(one.y == two.y){ 
			if(dir){
					one.y += entity.speed; // Вниз
					two.y += entity.speed;
			} else {
					one.y -= entity.speed; // Вверх
					two.y -= entity.speed;
			}} 
			else { 
			if(dir){
				one.x += entity.speed; // Вправо
				two.x += entity.speed;
			} else {
				one.x -= entity.speed; // Влево
				two.x -= entity.speed;
			}}
	}

	public void checkColTiles(Entity entity){
		if(entity.isPlayer)
		for(int i =0;i<gp.getLayersCount();i++){
			if(gp.getlevelService().getLayerById(i).collisionOn){
			int tileOne = gp.getlevelService().getLayerById(i).map[(one.x/gp.getTileSize())][(one.y/gp.getTileSize())];		
			int tileTwo = gp.getlevelService().getLayerById(i).map[(two.x/gp.getTileSize())][(two.y/gp.getTileSize())];
			if(tileOne !=0||tileTwo!=0){
				entity.collisionOn = false;
			}
			}
		}
	}

	public void checkSuperObj(Entity entity){
		if(!entity.isPlayer)
			return;
		for(int i = 0; i<=gp.getSuperObjArrayLength()-1;i++){
			Rectangle col = new Rectangle(zero.x, zero.y, gp.getTileSize(), gp.getTileSize());
			if(gp.getSuperObj(i)!=null){
				if(col.intersects(gp.getSuperObj(i).solidArea)){
					if(gp.getSuperObj(i).collision == true){
					entity.collisionOn = false;}
				gp.getPlayer().itemIteract(gp.getSuperObj(i));
				}
			}
		}
	}

	public void checkEntity(Entity entity){
		if(entity.isPlayer)
		gp.getlevelService().collisionNPC(zero.x, zero.y);
	} 
}
