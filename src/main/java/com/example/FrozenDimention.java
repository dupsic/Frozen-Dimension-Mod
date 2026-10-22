package com.example;

import com.example.block.ModBlocks;
import com.example.creativemodetab.ModCreativeModeTab;
import com.example.item.ModItems;
import com.example.registries.ModFlammableBlocks;
import com.example.registries.ModFuels;
import com.example.registries.ModStrippedableBlocks;
import com.example.worldgen.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.resources.Identifier;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrozenDimention implements ModInitializer {
	public static final String MOD_ID = "frozen-dimention";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModCreativeModeTab.registerModCreativeModeTabs();

		ModFuels.registerFuels();
		ModFlammableBlocks.registerFlammableBlocks();
		ModStrippedableBlocks.registerStrippableBlocks();

		ModWorldGeneration.generateModWorldGen();
		LOGGER.info("Hello Fabric world!");





		TillableBlockRegistry.register(
				ModBlocks.FROZEN_GRASS,
				HoeItem::onlyIfAirAbove,
				Blocks.FARMLAND.defaultBlockState()
		);

		TillableBlockRegistry.register(
				ModBlocks.FROZEN_DIRT,
				HoeItem::onlyIfAirAbove,
				Blocks.FARMLAND.defaultBlockState()
		);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
