package com.example.creativemodetab;

import com.example.FrozenDimention;
import com.example.block.ModBlocks;
import com.example.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab FROZEN_DIMENTION_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frozen_dimention"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAW_CRYONITE))
                    .title(Component.translatable("creativemodetab.frozen_dimention.frozen_dimention"))
                    .displayItems((parameters, output) -> {

                        output.accept(ModBlocks.DARK_BLUE_STONE);
                        output.accept(ModBlocks.CRYONITE_ORE);
                        output.accept(ModBlocks.LIGHT_BLUE_STONE);
                        output.accept(ModBlocks.CRYONITE_BLOCK);
                        output.accept(ModBlocks.FROZEN_DIRT);
                        output.accept(ModBlocks.FROZEN_GRASS);
                        output.accept(ModBlocks.FROZEN_COAL_ORE);
                        output.accept(ModBlocks.FROZEN_COPPER_ORE);
                        output.accept(ModBlocks.FROZEN_DIAMOND_ORE);
                        output.accept(ModBlocks.FROZEN_EMERALD_ORE);
                        output.accept(ModBlocks.FROZEN_GOLD_ORE);
                        output.accept(ModBlocks.FROZEN_IRON_ORE);
                        output.accept(ModBlocks.FROZEN_LAPIS_ORE);
                        output.accept(ModBlocks.FROZEN_REDSTONE_ORE);

                        output.accept(ModBlocks.ICEBLOOM_LEAVES);
                        output.accept(ModBlocks.ICEBLOOM_LOG);
                        output.accept(ModBlocks.ICEBLOOM_PLANKS);
                        output.accept(ModBlocks.ICEBLOOM_WOOD);
                        output.accept(ModBlocks.STRIPPED_ICEBLOOM_LOG);
                        output.accept(ModBlocks.STRIPPED_ICEBLOOM_WOOD);
                        output.accept(ModBlocks.ICEBLOOM_SAPLING);

                        output.accept(ModBlocks.FROZEN_PORTAL);
                        output.accept(ModBlocks.ICEBLOOM_FLOWER);
                        output.accept(ModBlocks.MAGIC_FLOWER);
                        output.accept(ModBlocks.WASTELAND_FLOWER);
                        output.accept(ModBlocks.FROZEN_GRASS_FLOWER);

                        output.accept(ModItems.RAW_CRYONITE);
                        output.accept(ModItems.FROZEN_BERRIES);
                        output.accept(ModItems.CRYONITE_INGOT);
                        output.accept(ModItems.FROZEN_COAL);


                        output.accept(ModItems.CRYONITE_SWORD);
                        output.accept(ModItems.CRYONITE_PICKAXE);
                        output.accept(ModItems.CRYONITE_AXE);
                        output.accept(ModItems.CRYONITE_SHOVEL);
                        output.accept(ModItems.CRYONITE_HOE);
                        output.accept(ModItems.CRYONITE_BOW);

                    })
                    .build());

    public static void registerModCreativeModeTabs() {
        FrozenDimention.LOGGER.info("Registering Creative Mode Tabs for " + FrozenDimention.MOD_ID);
    }
}
