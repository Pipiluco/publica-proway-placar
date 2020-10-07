package com.pifrans.messages.enums;

public enum EnumThemesPrimefaces {
	NOVA_LIGHT(1, "Nova Light", "nova-light"), 
	NOVA_DARK(2, "Nova Dark", "nova-dark"),
	NOVA_COLORED(3, "Nova Colored", "nova-colored"), 
	LUNA_BLUE(4, "Luna Blue", "luna-blue"),
	LUNA_AMBER(5, "Luna Amber", "luna-amber"), 
	LUNA_GREEN(6, "Luna Green", "luna-green"),
	LUNA_PINK(7, "Luna Pink", "luna-pink"), 
	OMEGA(8, "Omega", "omega");

	private int id;
	private String displayName;
	private String name;

	private EnumThemesPrimefaces(int id, String displayName, String name) {
		this.id = id;
		this.displayName = displayName;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getName() {
		return name;
	}

	public static EnumThemesPrimefaces toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (EnumThemesPrimefaces x : EnumThemesPrimefaces.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("ID inválido: " + id);
	}
}
