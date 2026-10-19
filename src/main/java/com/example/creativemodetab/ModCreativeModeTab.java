package com.example.creativemodetab;

import com.example.FrozenDimention;
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

                        output.accept(ModItems.RAW_CRYONITE);

                    })
                    .build());

    public static void registerModCreativeModeTabs() {
        FrozenDimention.LOGGER.info("Registering Creative Mode Tabs for " + FrozenDimention.MOD_ID);
    }
}
