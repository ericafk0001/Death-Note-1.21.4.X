package dev.deathnote.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeathNoteItem extends Item {
    public static final Logger LOGGER = LoggerFactory.getLogger(DeathNoteItem.class);

    public DeathNoteItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            return ClientDeathNoteItem.useClient(user, hand);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}