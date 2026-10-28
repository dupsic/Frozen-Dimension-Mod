package com.example.registries;

import com.example.block.ModBlocks;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class ModFlammableBlocks {
    public static void registerFlammableBlocks() {

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ICEBLOOM_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_ICEBLOOM_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ICEBLOOM_PLANKS, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_ICEBLOOM_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ICEBLOOM_WOOD, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ICEBLOOM_LEAVES, 30, 60);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.WILLOW_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_WILLOW_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.WILLOW_PLANKS, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_WILLOW_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.WILLOW_WOOD, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.WILLOW_LEAVES, 30, 60);


    }
}
