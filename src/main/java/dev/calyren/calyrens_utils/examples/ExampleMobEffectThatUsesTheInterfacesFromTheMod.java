package dev.calyren.calyrens_utils.examples;

import dev.calyren.calyrens_utils.CalyrenSUtils;
import dev.calyren.calyrens_utils.util.mob_effects.CustomEffectDuration;
import dev.calyren.calyrens_utils.util.mob_effects.CustomEffectTooltip;
import dev.calyren.calyrens_utils.util.mob_effects.StatusEffectBackground;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;

public class ExampleMobEffectThatUsesTheInterfacesFromTheMod
        extends MobEffect implements CustomEffectDuration, CustomEffectTooltip, StatusEffectBackground {

    public ExampleMobEffectThatUsesTheInterfacesFromTheMod() {
        super(MobEffectCategory.NEUTRAL, 0xFF004F);
    }

    @Override
    public Component getDurationText(MobEffectInstance instance) {
        return Component.literal("Hi!!! This is an Example!")
                .withStyle(style -> style.withColor(0xFFFFFF));
    }

    @Override
    public List<Component> getTooltipLines(MobEffectInstance instance) {
        return List.of(
                Component.literal("This Text will appear when you hover over the Effect!")
                        .withStyle(ChatFormatting.WHITE),
                Component.literal("It also supports multiple lines")
                        .withStyle(ChatFormatting.WHITE),
                Component.literal("And Different Formatting!")
                        .withStyle(ChatFormatting.BLUE)
        );
    }

    @Override
    public Identifier inventorySprite() {
        return Identifier.fromNamespaceAndPath(CalyrenSUtils.MOD_ID, "container/inventory/effect_background_purple");
    }
    @Override
    public Identifier ambientInventorySprite() {
        return Identifier.fromNamespaceAndPath(CalyrenSUtils.MOD_ID, "container/inventory/effect_background_purple.png");
    }

    // The texture files used above can be found in:
    // resources/assets/calyrens_utils/textures/gui/sprites/container/inventory/
}
