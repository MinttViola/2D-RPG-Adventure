package Entity;

import Util.Direction;
import java.awt.Rectangle;

public class Entity {
	public String name;
	public int worldX,worldY;
	public int speed;
	public Rectangle solidArea;
	public boolean collisionOn = false;
	public int solidAreaMultiplier = 4;
	public Direction dir = Direction.down;
	public boolean isPlayer = false;
}
