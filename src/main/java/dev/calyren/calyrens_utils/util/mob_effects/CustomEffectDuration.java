package dev.calyren.calyrens_utils.util.mob_effects;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;


public interface CustomEffectDuration {
    Component getDurationText(MobEffectInstance instance);
}
