package com.example.datagen;

import com.example.block.ModBlocks;
import com.example.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {

    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
//        dropSelf(ModBlocks.DARK_CRYONITE_ORE);
        dropSelf(ModBlocks.FROZEN_DIRT);
        dropSelf(ModBlocks.LIGHT_BLUE_STONE);
        dropSelf(ModBlocks.DARK_BLUE_STONE);
        dropSelf(ModBlocks.RAW_CRYONITE_BLOCK);
        dropSelf(ModBlocks.CRYONITE_BLOCK);

        add(ModBlocks.FROZEN_GRASS, block -> createSingleItemTableWithSilkTouch(block, ModBlocks.FROZEN_DIRT));

        add(ModBlocks.DARK_CRYONITE_ORE, createOreDrop(ModBlocks.DARK_CRYONITE_ORE, ModItems.RAW_CRYONITE));


        add(ModBlocks.DARK_CRYONITE_ORE, createOreDrop(ModBlocks.DARK_CRYONITE_ORE, ModItems.RAW_CRYONITE));
    }
}


