package club.koupah.feather.packets;

/**
 * @author Koupah
 * @createdAt 10:04:22 pm on 12 Feb 2022
 */

public class PacketType {

	public static enum Serverbound {
		CLIENT_HELLO;

		public static PacketType.Serverbound getType(String name) {
			return PacketType.Serverbound.valueOf(name);
		}
	}

}
