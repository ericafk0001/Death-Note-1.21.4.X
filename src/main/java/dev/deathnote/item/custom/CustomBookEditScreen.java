// CustomBookEditScreen.java (client-only class)
package dev.deathnote.item.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;

@Environment(EnvType.CLIENT)
public class CustomBookEditScreen extends BookEditScreen {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBookEditScreen.class);
    private static final Field pagesField;

    static {
        Field tempField = null;
        try {
            for (Field field : BookEditScreen.class.getDeclaredFields()) {
                if (List.class.isAssignableFrom(field.getType())) {
                    tempField = field;
                    tempField.setAccessible(true);
                    break;
                }
            }
            if (tempField == null) {
                throw new NoSuchFieldException("No field of type List found in BookEditScreen");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        pagesField = tempField;
    }

    public CustomBookEditScreen(PlayerEntity player, ItemStack book, Hand hand) {
        super(player, book, hand);
    }

    public record DeathNoteEffectPayload(String victimName) implements CustomPayload {
        public static final CustomPayload.Id<DeathNoteEffectPayload> ID = new CustomPayload.Id<>(Identifier.of("deathnote", "death_note_item"));
        public static final PacketCodec<RegistryByteBuf, DeathNoteEffectPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, DeathNoteEffectPayload::victimName, DeathNoteEffectPayload::new);

        @Override
        public CustomPayload.Id<? extends CustomPayload> getId() {
            return ID;
        }

        public static void send(String victimName) {
            ClientPlayNetworking.send(new DeathNoteEffectPayload(victimName));
        }
    }

    @Override
    public void removed() {
        super.removed();

        try {
            @SuppressWarnings("unchecked")
            List<String> bookText = (List<String>) pagesField.get(this);

            if (!bookText.isEmpty()) {
                String victimName = bookText.getLast().trim();
                if (!victimName.isEmpty()) {
                    DeathNoteEffectPayload.send(victimName);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to process Death Note effect", e);
        }
    }
}