package club.koupah.feather.packets.impl;

import java.util.Collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import club.koupah.feather.packets.FeatherPacket;
import club.koupah.feather.packets.PacketType;

/**
 * @author Koupah
 * @createdAt 11:49:08 pm on 12 Feb 2022
 */

public class FCDisableMods extends FeatherPacket {

	Collection<String> disabledMods;

	public FCDisableMods(Collection<String> modNames) {
		super(PacketType.Clientbound.DISABLE_MODS);
		this.disabledMods = modNames;
	}

	public void addDisabledMod(String internalName) {
		this.disabledMods.add(internalName);
	}

	@Override
	public JsonObject serialize() {
		JsonArray array = new JsonArray();

		for (String internalName : disabledMods)
			array.add(new JsonPrimitive(internalName));

		JsonObject mods = new JsonObject();
		mods.add("mods", array);

		return mods;
	}

}
