package dev.deathnote.item.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;

public class DeathNoteItem extends Item {
    public static final Logger LOGGER = LoggerFactory.getLogger(DeathNoteItem.class);

    public DeathNoteItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack writableBook = user.getStackInHand(Hand.MAIN_HAND);

        if (!world.isClient) return TypedActionResult.success(user.getStackInHand(hand));

        MinecraftClient.getInstance().setScreen(new CustomBookEditScreen(user, writableBook, hand));
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    public static class CustomBookEditScreen extends BookEditScreen {
        private final ItemStack bookStack;
        private static final Field pagesField;

        static {
            try {
                pagesField = BookEditScreen.class.getDeclaredField("pages");
                pagesField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }

        public CustomBookEditScreen(PlayerEntity player, ItemStack book, Hand hand) {
            super(player, book, hand);
            this.bookStack = book;
        }

        @Override
        public void removed() {
            super.removed();

            try {
                @SuppressWarnings("unchecked")
                List<String> bookText = (List<String>) pagesField.get(this);

                if (!bookText.isEmpty()) {
                    String victimName = bookText.get(bookText.size() - 1).trim();
                    if (!victimName.isEmpty()) {
                        MinecraftClient client = MinecraftClient.getInstance();
                        if (client.getNetworkHandler() != null) {
                            // Schedule effects and death with delays
                            client.execute(() -> {
                                // Initial effect
                                client.getNetworkHandler().sendCommand("title " + victimName + " times 20 60 20");
                                client.getNetworkHandler().sendCommand("title " + victimName + " subtitle {\"text\":\"Death Note claims another soul...\",\"color\":\"red\"}");
                                client.getNetworkHandler().sendCommand("title " + victimName + " title {\"text\":\"Your name was written\",\"color\":\"dark_red\"}");

                                // Schedule death effect after 5 seconds
                                new Thread(() -> {
                                    try {
                                        Thread.sleep(5000);
                                        client.execute(() -> {
                                            // Death effects
                                            client.getNetworkHandler().sendCommand("execute at " + victimName + " run particle minecraft:smoke ~ ~1 ~ 0.5 0.5 0.5 0.1 100");
                                            client.getNetworkHandler().sendCommand("kill " + victimName);
                                        });
                                    } catch (InterruptedException e) {
                                        LOGGER.error("Death Note effect interrupted", e);
                                    }
                                }).start();
                            });
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Failed to process Death Note effect", e);
            }
        }
    }
}
