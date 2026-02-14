package Util.Enums;

public enum TypesOfSuperObjEnum {
	KEY("key",2),
	CHEST("lootchest",2),
	CRYSTAL("crystal",3),
	HEART("heart",4);

	private final String name;

	TypesOfSuperObjEnum(String name,int statusCount){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
