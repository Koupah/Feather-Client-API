package club.koupah.feather;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import club.koupah.feather.handler.FeatherHandler;
import club.koupah.feather.packets.FeatherMod;
import club.koupah.feather.packets.FeatherPacket;
import club.koupah.feather.packets.impl.FCDisableMods;

/**
 * @author Koupah
 * @createdAt 8:00:28 pm on 12 Feb 2022
 */

public class FeatherAPI extends JavaPlugin {

	/**
	 * Fixed instance that can be accessed via {@link #getInstance()} both
	 * internally & by other plugins
	 */
	private static FeatherAPI instance;

	private FeatherHandler handler;

	private Set<FeatherMod> disabledMods;

	@Override
	public void onEnable() {
		FeatherAPI.instance = this;
		this.handler = new FeatherHandler();
		this.disabledMods = new HashSet<FeatherMod>();
	}

	public static List<FeatherMod> getDisabledMods() {
		return new ArrayList<FeatherMod>(FeatherAPI.getInstance().disabledMods);
	}

	// return true if successful
	public static boolean addDisabledMod(FeatherMod mod) {
		boolean updated = getInstance().disabledMods.add(mod);

		if (updated) {
			List<String> disabled = FeatherAPI.getInstance().disabledMods.stream().map(m -> m.getInternalName())
					.collect(Collectors.toList());
			sendFeatherPacketToAll(new FCDisableMods(disabled));
		}

		return updated;
	}

	public static boolean removeDisabledMod(FeatherMod mod) {
		boolean updated = getInstance().disabledMods.remove(mod);

		if (updated) {
			List<String> disabled = FeatherAPI.getInstance().disabledMods.stream().map(m -> m.getInternalName())
					.collect(Collectors.toList());
			sendFeatherPacketToAll(new FCDisableMods(disabled));
		}

		return updated;
	}

	/**
	 * 
	 * @param player
	 * @param packet
	 */
	public static void sendFeatherPacket(Player player, FeatherPacket packet) {
		Bukkit.broadcastMessage(packet.toJson().toString());
		player.sendPluginMessage(FeatherAPI.getInstance(), FeatherHandler.CHANNEL, packet.getBytes());
	}

	public static void sendFeatherPacketToAll(FeatherPacket packet) {
		byte[] bytes = packet.getBytes();

		for (UUID uuid : getFeatherPlayers()) {
			Player player = Bukkit.getPlayer(uuid);
			if (player != null)
				player.sendPluginMessage(FeatherAPI.getInstance(), FeatherHandler.CHANNEL, bytes);
		}
	}

	/**
	 * @return A list of UUID's of all online players running Feather
	 */
	public static List<UUID> getFeatherPlayers() {
		return new ArrayList<UUID>(getInstance().handler.getFeatherPlayers());
	}

	/**
	 * Check if a player is on the Feather Client
	 * 
	 * @param player The player to check
	 * @return Whether the player is running Feather Client or not
	 */
	public static boolean isOnFeather(Player player) {
		return isOnFeather(player.getUniqueId());
	}

	/**
	 * Check if a player is on the Feather Client
	 * 
	 * @param player The uuid of the player to check
	 * @return Whether the player is running Feather Client or not
	 */
	public static boolean isOnFeather(UUID uuid) {
		return getInstance().handler.getFeatherPlayers().contains(uuid);
	}

	/**
	 * @return Instance of FeatherAPI
	 */
	public static FeatherAPI getInstance() {
		return FeatherAPI.instance;
	}
}
