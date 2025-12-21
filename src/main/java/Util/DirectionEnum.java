package Util;

public enum DirectionEnum {
	up,
	left,
	right,
	down;

	public static DirectionEnum getOppositeSide(DirectionEnum direction){
		switch(direction){
			case up:
			return DirectionEnum.down;
			case down:
			return DirectionEnum.up;
			case right:
			return DirectionEnum.left;
			case left:
			return DirectionEnum.right;
			}
			return null;
	}
}
