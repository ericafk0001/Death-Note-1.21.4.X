package dev.deathnote;

import dev.deathnote.item.ModItemGroups;
import dev.deathnote.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeathNote implements ModInitializer {
	public static final String MOD_ID = "deathnote";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Connecting with the Shinigami Realm..");

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}