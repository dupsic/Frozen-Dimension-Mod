package com.example.datagen;

import com.example.block.ModBlocks;
import com.example.item.ModItems;
import com.example.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {


        tag(ModTags.Items.ICEBLOOM_LOGS)
                .add(ModItems.getRK(ModBlocks.ICEBLOOM_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.ICEBLOOM_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_ICEBLOOM_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_ICEBLOOM_LOG.asItem()));

        tag(ModTags.Items.WILLOW_LOGS)
                .add(ModItems.getRK(ModBlocks.WILLOW_LOG.asItem()))
                .add(ModItems.getRK(ModBlocks.WILLOW_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_WILLOW_WOOD.asItem()))
                .add(ModItems.getRK(ModBlocks.STRIPPED_WILLOW_LOG.asItem()));


        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.ICEBLOOM_LOGS)
                .addTag(ModTags.Items.WILLOW_LOGS);

        tag(ItemTags.PLANKS)
                .add(ModItems.getRK(ModBlocks.ICEBLOOM_PLANKS.asItem()))
                .add(ModItems.getRK(ModBlocks.WILLOW_PLANKS.asItem()));

        tag(ItemTags.LEAVES)
                .add(ModItems.getRK(ModBlocks.ICEBLOOM_LEAVES.asItem()))
                .add(ModItems.getRK(ModBlocks.WILLOW_LEAVES.asItem()));

        tag(ItemTags.SAPLINGS)
                .add(ModItems.getRK(ModBlocks.ICEBLOOM_SAPLING.asItem()))
                .add(ModItems.getRK(ModBlocks.WILLOW_SAPLING.asItem()));

    }

}
