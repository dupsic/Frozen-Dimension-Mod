package com.example.datagen;

import com.example.block.ModBlocks;
import com.example.item.ModItems;
import com.example.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider{
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.DARK_CRYONITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.DARK_BLUE_STONE))
                .add(ModBlocks.getRK(ModBlocks.LIGHT_BLUE_STONE))
                .add(ModBlocks.getRK(ModBlocks.RAW_CRYONITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.CRYONITE_BLOCK));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.getRK(ModBlocks.DARK_CRYONITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.DARK_BLUE_STONE))
                .add(ModBlocks.getRK(ModBlocks.LIGHT_BLUE_STONE))
                .add(ModBlocks.getRK(ModBlocks.RAW_CRYONITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.CRYONITE_BLOCK));

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.getRK(ModBlocks.FROZEN_DIRT))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_GRASS));


        tag(ModTags.Blocks.CRYONITE_MINEABLE)
                .add(ModBlocks.getRK(ModBlocks.DARK_CRYONITE_ORE));


        tag(ModTags.Blocks.ICEBLOOM_LOGS)
                .add(ModBlocks.getRK(ModBlocks.ICEBLOOM_LOG))
                .add(ModBlocks.getRK(ModBlocks.ICEBLOOM_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_ICEBLOOM_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_ICEBLOOM_LOG));

        tag(BlockTags.LOGS)
                .addTag(ModTags.Blocks.ICEBLOOM_LOGS);

        tag(BlockTags.PLANKS)
                .add(ModBlocks.getRK(ModBlocks.ICEBLOOM_PLANKS));

        tag(BlockTags.LEAVES)
                .add(ModBlocks.getRK(ModBlocks.ICEBLOOM_LEAVES));

        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.getRK(ModBlocks.POTTED_ICEBLOOM_SAPLING));

        tag(BlockTags.SUPPORTS_VEGETATION)
                .add(ModBlocks.getRK(ModBlocks.FROZEN_DIRT))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_GRASS));



    }
}
