package net.teamfruit.tcpatch;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.client.config.IConfigElement;
import net.minecraftforge.common.config.ConfigElement;
import thaumcraft.common.config.Config;

public class TCHook {
	@CoreInvoke
	public static void syncConfigurable() {
		TCPotions.instance.syncConfig();
	}

	@CoreInvoke
	public static void initPotions() {
		TCPotions.instance.initPotions();
	}

	@SuppressWarnings("rawtypes")
	@CoreInvoke
	public static void getConfigElements(final List<IConfigElement> list) {
		list.add(new ConfigElement(Config.config.getCategory("potion_ids")));
	}

	@CoreInvoke
	public static int nextInt(final Random random, final int bound) {
		return random.nextInt(Math.max(bound, 1));
	}
}
