package club.koupah.feather.packets;

import java.nio.charset.StandardCharsets;

import com.google.gson.JsonObject;

/**
 * @author Koupah
 * @createdAt 11:41:47 pm on 12 Feb 2022
 */

public abstract class FeatherPacket {

	PacketType.Clientbound packetType;

	public FeatherPacket(PacketType.Clientbound type) {
		this.packetType = type;
	}

	public byte[] getBytes() {
		return toJson().toString().getBytes(StandardCharsets.UTF_8);
	}
	
	public JsonObject toJson() {
		JsonObject obj = new JsonObject();
		obj.addProperty("packetType", this.packetType.name());
		obj.add("payload", serialize());
		return obj;
	}

	public abstract JsonObject serialize();

}
