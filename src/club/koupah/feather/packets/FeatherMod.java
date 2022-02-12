package club.koupah.feather.packets;

/**
 * @author Koupah
 * @createdAt 11:26:36 pm on 12 Feb 2022
 */

public enum FeatherMod {

	Animations,
	Armor_Status,
	Auto_Text,
	Block_Overlay,
	Boss_Bar,
	CPS,
	Clear_Water,
	Combo_Display,
	Coordinates,
	Custom_Crosshair,
	Direction,
	Discord("discordrp"),
	FOV_Changer,
	FPS,
	Glint,
	Hitbox,
	Hypixel,
	Item_Counter,
	Item_Info,
	Item_Physic,
	Keystrokes,
	Motion_Blur,
	Nick_Hider,
	Oof_Mod,
	Pack_Display("packdisplay"),
	Particles,
	Perspective,
	Ping,
	Potion_Effects,
	Reach_Display,
	Saturation,
	Scoreboard,
	Screenshot,
	Scrollable_Tooltips,
	Server_Address,
	Snaplook,
	Time,
	Time_Changer,
	Toggle_Sprint,
	Voice,
	Waypoints,
	Weather_Changer("weatherchanger"),
	Zoom;

	private String internalName;

	FeatherMod() {
		// Hacky little way to turn mod name to internal name
		boolean first = true;
		for (String part : name().replace("_", " ").toLowerCase().split(" ")) {
			if (first) {
				internalName = part;
				first = false;
			} else {
				internalName += String.valueOf(part.charAt(0)).toUpperCase() + part.substring(1);
			}
		}
	}

	FeatherMod(String internalName) {
		this.internalName = internalName;
	}

	public String getInternalName() {
		return this.internalName;
	}
}
