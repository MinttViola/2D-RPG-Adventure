package Util;

public enum TypesOfSuperObj {
	KEY("key",2),
	CHEST("lootchest",2),
	CRYSTAL("crystal",3);

	private final String name;

	TypesOfSuperObj(String name,int statusCount){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}
