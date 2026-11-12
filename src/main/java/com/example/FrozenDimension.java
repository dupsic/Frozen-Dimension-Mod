package com.example;

import com.example.block.ModBlocks;
import com.example.block.custom.FrozenPortalIgnition;
import com.example.creativemodetab.ModCreativeModeTab;
import com.example.entity.ModEntities;
import com.example.entity.custom.FrozenZombieEntity;
import com.example.item.ModItems;
import com.example.item.custom.*;
import com.example.registries.ModFlammableBlocks;
import com.example.registries.ModFuels;
import com.example.registries.ModStrippedableBlocks;
import com.example.worldgen.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrozenDimension implements ModInitializer {
	public static final String MOD_ID = "frozen-dimension";



	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		CryonitePickaxe.register();
		CryoniteShovel.register();
		CryoniteAxe.register();
		CryoniteHoe.register();
		CryoniteStaff.register();

		ModBlocks.registerModBlocks();

		ModCreativeModeTab.registerModCreativeModeTabs();

		ModFuels.registerFuels();
		ModFlammableBlocks.registerFlammableBlocks();
		ModStrippedableBlocks.registerStrippableBlocks();

		ModWorldGeneration.generateModWorldGen();

		ModEntities.registerModEntities();

		FabricDefaultAttributeRegistry.register(ModEntities.FROZENZOMBIE, FrozenZombieEntity.createAttributes());
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


		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {

			if (world.isClientSide()) {
				return InteractionResult.PASS;
			}

				if (!player.getItemInHand(hand).is(Items.FLINT_AND_STEEL)) {
				return InteractionResult.PASS;
			}

			if (!(world instanceof ServerLevel serverLevel)) {
				return InteractionResult.PASS;
			}

			BlockPos targetPos = hitResult.getBlockPos().relative(hitResult.getDirection());

			if (FrozenPortalIgnition.tryActivate(serverLevel, targetPos)) {
				return InteractionResult.SUCCESS;
			}

			return InteractionResult.PASS;
		});

	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
