package Tile;

public enum TileLayersEnum {

	KEY("key",2),
	CHEST("lootchest",2),
	CRYSTAL("crystal",3);

	private final String name;

	TileLayersEnum(String name,int statusCount){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
