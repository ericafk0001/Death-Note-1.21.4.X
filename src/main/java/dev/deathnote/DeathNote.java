//DeathNote.java
package dev.deathnote;

import dev.deathnote.item.ModItemGroups;
import dev.deathnote.item.ModItems;
import dev.deathnote.item.custom.CustomBookEditScreen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
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

		PayloadTypeRegistry.playC2S().register(CustomBookEditScreen.DeathNoteEffectPayload.ID, CustomBookEditScreen.DeathNoteEffectPayload.CODEC);

		ServerPlayNetworking.registerGlobalReceiver(CustomBookEditScreen.DeathNoteEffectPayload.ID, (payload, context) -> {
			String victimName = payload.victimName();
			context.server().execute(() -> {
				MinecraftServer server = context.server();
				if (server != null) {
					ServerCommandSource source = server.getCommandSource();
					server.getCommandManager().executeWithPrefix(source, "title " + victimName + " times 20 60 20");
					server.getCommandManager().executeWithPrefix(source, "title " + victimName + " subtitle {\"text\":\"Death Note claims another soul...\",\"color\":\"red\"}");
					server.getCommandManager().executeWithPrefix(source, "title " + victimName + " title {\"text\":\"Your name was written\",\"color\":\"dark_red\"}");
					server.getCommandManager().executeWithPrefix(source, "execute at " + victimName + " run playsound minecraft:entity.lightning_bolt.thunder player @a ~ ~ ~ 1 1");
					new Thread(() -> {
						try {
							Thread.sleep(5000);
							server.getCommandManager().executeWithPrefix(source, "execute at " + victimName + " run particle minecraft:smoke ~ ~1 ~ 0.5 0.5 0.5 0.1 100");
							server.getCommandManager().executeWithPrefix(source, "kill " + victimName);
						} catch (InterruptedException e) {
							LOGGER.error("Death Note effect interrupted", e);
						}
					}).start();
				}
			});
		});
	}
}