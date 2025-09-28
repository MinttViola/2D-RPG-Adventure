package Util;

public enum TypesOfSuperObjEnum {
	KEY("key",2),
	CHEST("lootchest",2),
	CRYSTAL("crystal",3);

	private final String name;

	TypesOfSuperObjEnum(String name,int statusCount){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
