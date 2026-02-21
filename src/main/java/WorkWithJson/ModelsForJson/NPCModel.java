package WorkWithJson.ModelsForJson;

import Util.Enums.DirectionEnum;
import Util.Enums.NPCEnum;

public class NPCModel {
	public NPCEnum type;
	public int id;
	public int xStartPosition;
	public int yStartPosition;
	public DirectionEnum direction;
	public int speed;
	public int minX;
	public int maxX;
	public int minY;
	public int maxY;

	public NPCModel(NPCEnum type, int id,	int xStartPosition,	int yStartPosition,	DirectionEnum direction,int speed,int minX,int maxX,int minY,int maxY)
	{
		this.type = type;
		this.id=id;
		this.xStartPosition=xStartPosition;
		this.yStartPosition=yStartPosition;
		this.direction=direction;
		this.speed=speed;
		this.minX=minX;
		this.maxX=maxX;
		this.minY=minY;
		this.maxY=maxY;
	}
}
