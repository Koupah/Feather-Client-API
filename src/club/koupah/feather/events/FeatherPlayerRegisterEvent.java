package club.koupah.feather.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event that is fired whenever a player logs in with the Feather Client and
 * completes the registration process, of which is simply sending the hello
 * packet.
 * 
 * @author Koupah
 * @createdAt 9:08:31 pm on 12 Feb 2022
 */

public class FeatherPlayerRegisterEvent extends Event {

	Player player;

	public FeatherPlayerRegisterEvent(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return this.player;
	}

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
