package com.example.block;

import com.example.FrozenDimention;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {

    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }
    public static final Block DARK_BLUE_STONE = registerBlock("dark_blue_stone",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final Block DARK_CRYONITE_ORE = registerBlock("dark_cryonite_ore",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));




    // reģistrē jaunu bloku spēlē
    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, name))));
        registerBlockitem(name, toRegister);

        // Pievieno bloku reģistram,
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, name), toRegister);
    }


    // reģistrē bloku kā priekšmetu inventory
    // BlockItem ir bloks kurš ir inventory nevis tad kurs stav pasaule
    private static void registerBlockitem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, name)))));
    }


    public static void registerModBlocks() {
        FrozenDimention.LOGGER.info("Registering Mod Blocks for " + FrozenDimention.MOD_ID);
    }
}
