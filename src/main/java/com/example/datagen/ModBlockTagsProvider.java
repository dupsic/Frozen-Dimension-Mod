package com.example.datagen;

import com.example.block.ModBlocks;
import com.example.item.ModItems;
import com.example.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider{
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.CRYONITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.DARK_BLUE_STONE))
                .add(ModBlocks.getRK(ModBlocks.LIGHT_BLUE_STONE))
                .add(ModBlocks.getRK(ModBlocks.RAW_CRYONITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_COPPER_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_COAL_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_DIAMOND_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_EMERALD_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_GOLD_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_IRON_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_LAPIS_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_REDSTONE_ORE))
                .add(ModBlocks.getRK(ModBlocks.CRYONITE_BLOCK));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.getRK(ModBlocks.CRYONITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.DARK_BLUE_STONE))
                .add(ModBlocks.getRK(ModBlocks.LIGHT_BLUE_STONE))
                .add(ModBlocks.getRK(ModBlocks.RAW_CRYONITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_DIAMOND_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_REDSTONE_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_GOLD_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_EMERALD_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_LAPIS_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_COPPER_ORE))
                .add(ModBlocks.getRK(ModBlocks.CRYONITE_BLOCK));

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.getRK(ModBlocks.FROZEN_COAL_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_IRON_ORE));

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.getRK(ModBlocks.FROZEN_DIRT))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_GRASS));


        tag(ModTags.Blocks.CRYONITE_MINEABLE)
                .add(ModBlocks.getRK(ModBlocks.CRYONITE_ORE));



        tag(ModTags.Blocks.ICEBLOOM_LOGS)
                .add(ModBlocks.getRK(ModBlocks.ICEBLOOM_LOG))
                .add(ModBlocks.getRK(ModBlocks.ICEBLOOM_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_ICEBLOOM_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_ICEBLOOM_LOG));

        tag(ModTags.Blocks.WILLOW_LOGS)
                .add(ModBlocks.getRK(ModBlocks.WILLOW_LOG))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_WILLOW_WOOD))
                .add(ModBlocks.getRK(ModBlocks.STRIPPED_WILLOW_LOG));

        tag(BlockTags.LOGS)
                .addTag(ModTags.Blocks.ICEBLOOM_LOGS)
                .addTag(ModTags.Blocks.WILLOW_LOGS);

        tag(BlockTags.PLANKS)
                .add(ModBlocks.getRK(ModBlocks.ICEBLOOM_PLANKS))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_PLANKS));

        tag(BlockTags.LEAVES)
                .add(ModBlocks.getRK(ModBlocks.ICEBLOOM_LEAVES))
                .add(ModBlocks.getRK(ModBlocks.WILLOW_LEAVES));

        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.getRK(ModBlocks.POTTED_ICEBLOOM_SAPLING))
                .add(ModBlocks.getRK(ModBlocks.POTTED_WILLOW_SAPLING))
                .add(ModBlocks.getRK(ModBlocks.POTTED_ICEBLOOM_FLOWER))
                .add(ModBlocks.getRK(ModBlocks.POTTED_MAGIC_FLOWER))
                .add(ModBlocks.getRK(ModBlocks.POTTED_WASTELAND_FLOWER));

        tag(BlockTags.SUPPORTS_VEGETATION)
                .add(ModBlocks.getRK(ModBlocks.FROZEN_DIRT))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_GRASS));

        tag(BlockTags.STONE_ORE_REPLACEABLES)
                .add(ModBlocks.getRK(ModBlocks.LIGHT_BLUE_STONE));

        tag(ModTags.Blocks.ORES)
                .add(ModBlocks.getRK(ModBlocks.CRYONITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_COAL_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_COPPER_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_DIAMOND_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_EMERALD_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_GOLD_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_IRON_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_LAPIS_ORE))
                .add(ModBlocks.getRK(ModBlocks.FROZEN_REDSTONE_ORE));

        tag(ConventionalBlockTags.ORES)
                .addTag(ModTags.Blocks.ORES);
    }
}
