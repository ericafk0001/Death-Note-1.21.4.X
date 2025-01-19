package dev.deathnote.item.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;

@Environment(EnvType.CLIENT)
public class ClientDeathNoteItem {
    public static TypedActionResult<ItemStack> useClient(PlayerEntity user, Hand hand) {
        ItemStack writableBook = user.getStackInHand(Hand.MAIN_HAND);
        MinecraftClient.getInstance().setScreen(new CustomBookEditScreen(user, writableBook, hand));
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}