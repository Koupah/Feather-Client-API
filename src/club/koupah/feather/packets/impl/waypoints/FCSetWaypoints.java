package club.koupah.feather.packets.impl.waypoints;

import java.util.Collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import club.koupah.feather.packets.FeatherPacket;
import club.koupah.feather.packets.PacketType;

/**
 * @author Koupah
 * @createdAt 12:39:39 am on 13 Feb 2022
 */

public class FCSetWaypoints extends FeatherPacket {
	Collection<Waypoint> waypoints;

	public FCSetWaypoints(Collection<Waypoint> waypoints) {
		super(PacketType.Clientbound.SET_WAYPOINTS);
		this.waypoints = waypoints;
	}

	@Override
	public JsonObject serialize() {
		JsonArray array = new JsonArray();

		for (Waypoint waypoint : waypoints)
			array.add(waypoint.toJson());

		JsonObject mods = new JsonObject();
		mods.add("mods", array);

		return mods;
	}
}
