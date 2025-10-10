package Util;

public enum DirectionEnum {
	up,
	left,
	right,
	down;

	public static DirectionEnum getOppositeSide(DirectionEnum direction){
		switch(direction){
			case DirectionEnum.up:
			return DirectionEnum.down;
			case DirectionEnum.down:
			return DirectionEnum.up;
			case DirectionEnum.right:
			return DirectionEnum.left;
			case DirectionEnum.left:
			return DirectionEnum.right;
			}
			return null;
	}
}
