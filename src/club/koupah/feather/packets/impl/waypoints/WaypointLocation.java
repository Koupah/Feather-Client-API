package club.koupah.feather.packets.impl.waypoints;

import org.bukkit.Location;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author Koupah
 * @createdAt 12:37:02 am on 13 Feb 2022
 */

public class WaypointLocation {

	private final int x;
	private final int y;
	private final int z;
	
	public WaypointLocation(Location loc) {
		this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}
	
	public WaypointLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public JsonObject toJson() {
		JsonObject obj = new JsonObject();
		obj.addProperty("x", x);
		obj.addProperty("y", x);
		obj.addProperty("z", x);
		return obj;
	}
}
