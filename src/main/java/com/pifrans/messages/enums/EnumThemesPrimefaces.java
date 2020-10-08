package com.pifrans.messages.enums;

public enum EnumThemesPrimefaces {
	LUNA_AMBER(1, "Luna Amber", "luna-amber"), 
	LUNA_BLUE(2, "Luna Blue", "luna-blue"),
	LUNA_GREEN(3, "Luna Green", "luna-green"),
	LUNA_PINK(4, "Luna Pink", "luna-pink"), 
	NOVA_COLORED(5, "Nova Colored", "nova-colored"),
	NOVA_DARK(6, "Nova Dark", "nova-dark"),
	NOVA_LIGHT(7, "Nova Light", "nova-light"),
	OMEGA(8, "Omega", "omega"),
	
	AFTERDARK(9, "Afterdark", "afterdark"),
	AFTERNOON(10, "Afternoon", "afternoon"),
	AFTERWORK(11, "Afterwork", "afterwork"),
	BLACK_TIE(12, "Black-Tie", "black-tie"),
	BLITZER(13, "Blitzer", "blitzer"),
	BLUESKY(14, "Bluesky", "bluesky"),
	BOOTSTRAP(15, "Bootstrap", "bootstrap"),
	CASABLANCA(16, "Casablanca", "casablanca"),
	CRUZE(17, "Cruze", "cruze"),
	CUPERTINO(18, "Cupertino", "cupertino"),
	DARK_HIVE(19, "Dark-Hive", "dark-hive"),
	DELTA(20, "Delta", "delta"),
	DOT_LUV(21, "Dot-Luv", "dot-luv"),
	EGGPLANT(22, "Eggplant", "eggplant"),
	EXCITE_BIKE(23, "Excite-Bike", "excite-bike"),
	FLICK(24, "Flick", "flick"),
	GLASS_X(25, "Glass-X", "glass-x"),
	HOME(26, "Home", "home"),
	HOT_SNEAKS(27, "Hot-Sneaks", "hot-sneaks"),
	HUMANITY(28, "Humanity", "humanity"),
	LE_FROG(29, "Le-Frog", "le-frog"),
	MIDNIGHT(30, "Midnight", "midnight"),
	MINT_CHOC(31, "Mint-Choc", "mint-choc"),
	OVERCAST(32, "Overcast", "overcast"),
	PEPPER_GRINDER(33, "Pepper-Grinder", "pepper-grinder"),
	REDMOND(34, "Redmond", "redmond"),
	ROCKET(35, "Rocket", "rocket"),
	SAM(36, "Sam", "sam"),
	SMOOTHNESS(37, "Smoothness", "smoothness"),
	SOUTH_STREET(38, "South-Street", "south-street"),
	START(39, "Start", "start"),
	SUNNY(40, "Sunny", "sunny"),
	SWANKY_PURSE(41, "Swanky-Purse", "swanky-purse"),
	TRONTASTIC(42, "Trontastic", "trontastic"),
	UI_DARKNESS(43, "UI-Darkness", "ui-darkness"),
	UI_LIGHTNESS(44, "UI-Lightness", "ui-lightness"),
	VADER(45, "Vader", "vader");


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
