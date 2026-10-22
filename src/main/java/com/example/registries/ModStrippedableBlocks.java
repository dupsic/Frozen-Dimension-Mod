package com.example.registries;

import com.example.block.ModBlocks;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class ModStrippedableBlocks {
    public static void registerStrippableBlocks() {
        StrippableBlockRegistry.register(ModBlocks.ICEBLOOM_LOG, ModBlocks.STRIPPED_ICEBLOOM_LOG);
        StrippableBlockRegistry.register(ModBlocks.ICEBLOOM_WOOD, ModBlocks.STRIPPED_ICEBLOOM_WOOD);
    }
}
