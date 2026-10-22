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


        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.ICEBLOOM_LOGS);

        tag(ItemTags.PLANKS)
                .add(ModItems.getRK(ModBlocks.ICEBLOOM_PLANKS.asItem()));
        tag(ItemTags.LEAVES)
                .add(ModItems.getRK(ModBlocks.ICEBLOOM_LEAVES.asItem()));
    }

}
