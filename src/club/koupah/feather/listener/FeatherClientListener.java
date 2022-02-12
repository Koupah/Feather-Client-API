package club.koupah.feather.listener;

import java.nio.charset.StandardCharsets;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import club.koupah.feather.handler.FeatherHandler;
import club.koupah.feather.packets.PacketType;

/**
 * @author Koupah
 * @createdAt 8:05:09 pm on 12 Feb 2022
 */

public class FeatherClientListener implements Listener, PluginMessageListener {

	FeatherHandler handler;

	public FeatherClientListener(FeatherHandler handler) {
		this.handler = handler;
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		this.handler.removePlayer(event.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onRegister(PlayerRegisterChannelEvent event) {
		if (!event.getChannel().equals(FeatherHandler.CHANNEL))
			return;

		this.handler.addAwaitingPlayer(event.getPlayer().getUniqueId());
	}

	@EventHandler
	public void onUnregister(PlayerUnregisterChannelEvent event) {
		if (!event.getChannel().equals(FeatherHandler.CHANNEL))
			return;

		this.handler.removePlayer(event.getPlayer().getUniqueId());
	}

	@Override
	public void onPluginMessageReceived(String arg0, Player arg1, byte[] arg2) {
		JsonObject json = new Gson().fromJson(new String(arg2, StandardCharsets.UTF_8), JsonObject.class);
		if (json.has("packetType") && PacketType.Serverbound
				.getType(json.get("packetType").getAsString()) == PacketType.Serverbound.CLIENT_HELLO) {
			this.handler.onHelloReceived(arg1);
		}
	}

}
