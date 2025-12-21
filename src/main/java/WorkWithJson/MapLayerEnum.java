package WorkWithJson;

public enum MapLayerEnum {
	BASE(0, "Base", false),
	HILL(1, "Hill", true),
	DECORATION_WO_COL(2, "DecorationWOCol", false),
	DECORATION_WITH_COL(3, "DecorationWithCol", true); 

	private final int order;
	private final String name;
	private final boolean collision;

	private MapLayerEnum(int order, String name, boolean collision) {
			this.order = order;
			this.name = name;
			this.collision = collision;
	}

	public String getName() {
			return name;
	}

	public int getOrder() {
			return order;
	}

	public boolean getCollision() {
			return collision;
	}

	public static String getNameByOrder(int order) {
			for (MapLayerEnum layer : MapLayerEnum.values()) {
					if (layer.getOrder() == order) {
							return layer.getName();
					}
			}
			return null;
	}
	public static boolean  getColByName(String name) {
			for (MapLayerEnum layer : MapLayerEnum.values()) {
					if (layer.getName() == name) {
							return layer.getCollision();
					}
			}
			return false;
	}
}
