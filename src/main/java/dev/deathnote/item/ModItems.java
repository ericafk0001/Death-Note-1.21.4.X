//ModItems.java
package dev.deathnote.item;

import dev.deathnote.DeathNote;
import dev.deathnote.item.custom.DeathNoteItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.WritableBookContentComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {

    // Food items
    public static final Item SHINIGAMI_APPLE = registerItem("shinigami_apple", new Item(new Item.Settings().food(ModFoodComponents.SHINIGAMI_APPLE)));
    public static final Item POTATO_CHIP = registerItem("potato_chip", new Item(new Item.Settings().food(ModFoodComponents.POTATO_CHIP)));

    // Custom items
    public static final Item DEATH_NOTE_ITEM = registerItem("death_note_item",
            new DeathNoteItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).component(DataComponentTypes.WRITABLE_BOOK_CONTENT, WritableBookContentComponent.DEFAULT)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DeathNote.MOD_ID, name), item);
    }

    public static void registerModItems() {
        DeathNote.LOGGER.info("Registering Mod Items for " + DeathNote.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(SHINIGAMI_APPLE);
            entries.add(POTATO_CHIP);
            entries.add(DEATH_NOTE_ITEM);
        });
    }
}