package com.example.item;

import com.example.FrozenDimention;
import com.example.block.ModBlocks;
import com.example.block.food.ModFoods;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {

    public static final Item RAW_CRYONITE = registerItem("raw_cryonite", Item::new);
    public static final Item FROZEN_BERRIES = registerItem("frozen_berries", properties -> new BlockItem(ModBlocks.FROZEN_BERRIES_BUSH, properties.food(ModFoods.FROZEN_BERRIES, ModFoods.FROZEN_BERRIES_CONSUMABLE)));
    public static final Item FROZEN_COAL = registerItem("frozen_coal", Item::new);
    public static final Item CRYONITE_INGOT = registerItem("cryonite_ingot", Item::new);

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, name)))));

    }

    public static void registerModItems() {
        FrozenDimention.LOGGER.info("Registering Mod Items for " + FrozenDimention.MOD_ID);
    }
}
