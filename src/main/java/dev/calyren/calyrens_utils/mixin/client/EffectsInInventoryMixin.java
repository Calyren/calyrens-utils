package dev.calyren.calyrens_utils.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.renderpearl.api.pipeline.RenderPipeline;
import dev.calyren.calyrens_utils.util.mob_effects.CustomEffectDuration;
import dev.calyren.calyrens_utils.util.mob_effects.CustomEffectTooltip;
import dev.calyren.calyrens_utils.util.mob_effects.StatusEffectBackground;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(value = EffectsInInventory.class, priority = 2139999999)
public class EffectsInInventoryMixin {

    @Shadow @Final private static Identifier EFFECT_BACKGROUND_SPRITE;
    @Shadow @Final private static Identifier EFFECT_BACKGROUND_AMBIENT_SPRITE;

    @Shadow
    @Final
    private AbstractContainerScreen<?> screen;
    @Unique
    private MobEffectInstance kaleidoscope$currentEffect;

    @Inject(method = "extractEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/EffectsInInventory;extractBackground(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/Component;IIZI)I"))
    private void captureEffect(CallbackInfo ci,
                               @Local MobEffectInstance mobEffectInstance) {
        this.kaleidoscope$currentEffect = mobEffectInstance;
    }

    @WrapOperation(method = "extractEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectUtil;formatDuration(Lnet/minecraft/world/effect/MobEffectInstance;FF)Lnet/minecraft/network/chat/Component;"))
    private Component customDuration(MobEffectInstance instance, float durationScale, float tickRate, Operation<Component> original) {
        MobEffect effect = instance.getEffect().value();
        if (effect instanceof CustomEffectDuration custom) {
            return custom.getDurationText(instance);
        }
        return original.call(instance, durationScale, tickRate);
    }

    @WrapOperation(method = "extractBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/renderpearl/api/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void customEffectBackground(GuiGraphicsExtractor instance, RenderPipeline pipeline,
                                        Identifier texture, int x, int y, int width, int height,
                                        Operation<Void> original) {
        if (this.kaleidoscope$currentEffect != null
                && this.kaleidoscope$currentEffect.getEffect().value() instanceof StatusEffectBackground bg) {
            if (texture.equals(EFFECT_BACKGROUND_SPRITE)) {
                texture = bg.inventorySprite();
            } else if (texture.equals(EFFECT_BACKGROUND_AMBIENT_SPRITE)) {
                texture = bg.ambientInventorySprite();
            }
        }
        original.call(instance, pipeline, texture, x, y, width, height);
    }

    @Inject(method = "extractText", at = @At("HEAD"))
    private void kaleidoscope$alwaysShowTooltip(GuiGraphicsExtractor guiGraphics, Component component, Component component2,
                                                Font font, int i, int j, int k, int l, int m, int n,
                                                CallbackInfo ci) {
        if (!(m >= i && m <= i + k && n >= j && n <= j + l)) return;

        if (this.kaleidoscope$currentEffect == null) return;
        MobEffect effect = this.kaleidoscope$currentEffect.getEffect().value();

        List<Component> tooltipLines = new java.util.ArrayList<>();

        if (effect instanceof CustomEffectTooltip tooltipEffect) {
            tooltipLines.addAll(tooltipEffect.getTooltipLines(this.kaleidoscope$currentEffect));
        }

        guiGraphics.setTooltipForNextFrame(this.screen.getFont(), tooltipLines, Optional.empty(), m, n);
    }
}