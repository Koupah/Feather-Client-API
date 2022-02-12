package club.koupah.feather.handler;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.Messenger;

import club.koupah.feather.FeatherAPI;
import club.koupah.feather.events.FeatherPlayerRegisterEvent;
import club.koupah.feather.listener.FeatherClientListener;

/**
 * @author Koupah
 * @createdAt 8:07:59 pm on 12 Feb 2022
 */

public class FeatherHandler {

	// The channel all Feather Client messages are sent through
	public static final String CHANNEL = "feather:client";

	// Players we're waiting on to send the hello packet
	private Set<UUID> awaitingHello;

	// Players that have registered the channel and have sent the hello packet
	private Set<UUID> featherPlayers;

	private FeatherClientListener listener;

	public FeatherHandler() {
		this.awaitingHello = new HashSet<UUID>();
		this.featherPlayers = new HashSet<UUID>();
		this.listener = new FeatherClientListener(this);

		Messenger messenger = Bukkit.getServer().getMessenger();
		messenger.registerOutgoingPluginChannel(FeatherAPI.getInstance(), CHANNEL);
		messenger.registerIncomingPluginChannel(FeatherAPI.getInstance(), CHANNEL, listener);

		Bukkit.getPluginManager().registerEvents(listener, FeatherAPI.getInstance());
	}

	public Set<UUID> getFeatherPlayers() {
		return this.featherPlayers;
	}

	public void addAwaitingPlayer(UUID uuid) {
		this.awaitingHello.add(uuid);
	}

	public void onHelloReceived(Player player) {
		UUID uuid = player.getUniqueId();

		// If we were waiting for this player to send hello
		if (this.awaitingHello.remove(uuid)) {
			this.featherPlayers.add(uuid);
			Bukkit.getPluginManager().callEvent(new FeatherPlayerRegisterEvent(player));
		}
	}

	public void removePlayer(UUID uuid) {
		this.awaitingHello.remove(uuid);
		this.featherPlayers.remove(uuid);
	}

}
