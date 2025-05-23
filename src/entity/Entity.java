package entity;

import java.awt.Rectangle;

import Util.Direction;

public class Entity {
	public int worldX,worldY;
	public int speed;
	public Rectangle solidArea;
	public boolean collisionOn = false;
	public int solidAreaMultiplier = 4;
	public Direction dir = Direction.down;
	public boolean isPlayer = false;
}
