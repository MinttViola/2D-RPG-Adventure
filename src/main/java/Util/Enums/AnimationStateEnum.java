package Util.Enums;

public enum AnimationStateEnum {
	Idle("Idle", 0),
	Walk("Walk", 1);

	private final int id;
	private final String state;

	private AnimationStateEnum(String name, int Id){
		state = name;
		this.id = Id;
	}

	public int getId(){
		return id;
	}
	public String getState(){
		return state;
	}
	public static String getStateById(int Id){
		for (AnimationStateEnum state : AnimationStateEnum.values()) {
							if (state.getId() == Id) {
									return state.getState();
							}
					}
		return null;
	}
	public static  int getIdbyState(String state){
		for (AnimationStateEnum id : AnimationStateEnum.values()) {
							if (id.getState() == state) {
									return id.getId();
							}
					}
		return -1;
	}
}
