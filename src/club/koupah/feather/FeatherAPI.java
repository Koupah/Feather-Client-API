package club.koupah.feather;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import club.koupah.feather.handler.FeatherHandler;

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

	@Override
	public void onEnable() {
		FeatherAPI.instance = this;

		this.handler = new FeatherHandler();
	}

	/**
	 * @return A list of UUID's of all online players running Feather
	 */
	public List<UUID> getFeatherPlayers() {
		return new ArrayList<UUID>(this.handler.getFeatherPlayers());
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
