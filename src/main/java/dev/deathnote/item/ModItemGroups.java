//ModItemGroups.java
package dev.deathnote.item;

import dev.deathnote.DeathNote;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup DEATH_NOTE_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(DeathNote.MOD_ID, "death_note_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SHINIGAMI_APPLE)) //replace with death note
                    .displayName(Text.translatable("itemgroup.deathnote.death_note_items"))
                    .entries((displayContext, entries) -> {
                            entries.add(ModItems.SHINIGAMI_APPLE);
                            entries.add(ModItems.POTATO_CHIP);
                            entries.add(ModItems.DEATH_NOTE_ITEM);
                    }).build());

    public static void registerItemGroups() {
        DeathNote.LOGGER.info("Registering Mod Item Groups for " + DeathNote.MOD_ID);
    }
}
