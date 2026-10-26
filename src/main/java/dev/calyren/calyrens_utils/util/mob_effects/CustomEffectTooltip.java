package dev.calyren.calyrens_utils.util.mob_effects;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;

public interface CustomEffectTooltip {
    List<Component> getTooltipLines(MobEffectInstance instance);
}