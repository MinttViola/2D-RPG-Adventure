package Util.Enums;

public enum LanguagesEnum {
	en,
	ru;

	public LanguagesEnum next() {
        LanguagesEnum[] all = values();
        int nextIndex = (this.ordinal() + 1) % all.length;
        return all[nextIndex];
    }
}
