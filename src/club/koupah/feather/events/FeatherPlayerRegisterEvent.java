package club.koupah.feather.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Event that is fired whenever a player logs in with the Feather Client and
 * completes the registration process, of which is simply sending the hello
 * packet.
 * 
 * @author Koupah
 * @createdAt 9:08:31 pm on 12 Feb 2022
 */

public class FeatherPlayerRegisterEvent extends PlayerEvent {

	private static HandlerList handlerList = new HandlerList();

	public FeatherPlayerRegisterEvent(Player player) {
		super(player);
	}

	public HandlerList getHandlerList() {
		return handlerList;
	}

	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}
}
