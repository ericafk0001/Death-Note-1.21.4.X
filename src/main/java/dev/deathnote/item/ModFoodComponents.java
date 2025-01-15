package dev.deathnote.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent SHINIGAMI_APPLE = new FoodComponent.Builder().nutrition(2).saturationModifier(0.2f).alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 10 * 20), 0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 10 * 20), 0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 15 * 20, 5), 0.9f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 15 * 20), 0.9f)
            .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20 * 20), 1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 120 * 20, 5), 0.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 30 * 20, 2), 0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 5 * 20, 1), 0.6f)
            .build();

    public static final FoodComponent POTATO_CHIP = new FoodComponent.Builder().nutrition(4).saturationModifier(0.1f)
            .snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5 * 20), 1f)
            .build();
}
