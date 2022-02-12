package club.koupah.feather.packets.impl.waypoints;

import org.bukkit.Location;

import com.google.gson.JsonObject;

/**
 * @author Koupah
 * @createdAt 12:38:42 am on 13 Feb 2022
 */

public class Waypoint {

	private String name;
	private ChromaColor color;
	private String world;
	private WaypointLocation location;

	public Waypoint(String name, ChromaColor color, Location location) {
		this(name, color, location.getWorld().getName(), new WaypointLocation(location));
	}
	
	public Waypoint(String name, ChromaColor color, String world, WaypointLocation location) {
		this.name = name;
		this.color = color;
		this.world = world;
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public ChromaColor getColor() {
		return this.color;
	}

	public String getWorld() {
		return this.world;
	}

	public WaypointLocation getLocation() {
		return this.location;
	}

	public JsonObject toJson() {
		JsonObject obj = new JsonObject();
		obj.addProperty("name", this.name);
		obj.add("color", color.toJson());
		obj.addProperty(name, this.world);
		obj.add("location", this.location.toJson());
		return obj;
	}
}
