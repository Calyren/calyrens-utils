package dev.calyren.calyrens_utils;

import dev.calyren.calyrens_utils.examples.ExampleMobEffectThatUsesTheInterfacesFromTheMod;
import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import net.minecraft.world.effect.MobEffect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalyrenSUtils implements ModInitializer {
	public static final String MOD_ID = "calyrens_utils";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Holder<MobEffect> EXAMPLE_EFFECT =
			Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(MOD_ID, "example"), new ExampleMobEffectThatUsesTheInterfacesFromTheMod());

	@Override public void onInitialize() {}

//	public static Identifier id(String path) {
//		return Identifier.fromNamespaceAndPath(MOD_ID, path);
//	}
}
