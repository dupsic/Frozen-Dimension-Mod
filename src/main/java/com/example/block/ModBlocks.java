package com.example.block;

import com.example.FrozenDimension;
import com.example.block.custom.FrozenBerriesBlock;
import com.example.block.custom.FrozenPortalBlock;
import com.example.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {

    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }


    public static final Block CRYONITE_ORE = registerBlock("cryonite_ore",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops().strength(3f).sound(SoundType.STONE)));

    public static final Block DARK_BLUE_STONE = registerBlock("dark_blue_stone",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final Block LIGHT_BLUE_STONE = registerBlock("light_blue_stone",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)));
// ores
    public static final Block FROZEN_IRON_ORE = registerBlock("frozen_iron_ore",
            properties -> new DropExperienceBlock(ConstantInt.of(0),
                    properties.requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final Block FROZEN_LAPIS_ORE = registerBlock("frozen_lapis_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2,5),
                    properties.requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final Block FROZEN_EMERALD_ORE = registerBlock("frozen_emerald_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3,7),
                    properties.requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final Block FROZEN_DIAMOND_ORE = registerBlock("frozen_diamond_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3,7),
                    properties.requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final Block FROZEN_REDSTONE_ORE = registerBlock("frozen_redstone_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3,7),
                    properties.requiresCorrectToolForDrops().randomTicks().strength(3.0F, 3.0F)));

    public static final Block FROZEN_COPPER_ORE = registerBlock("frozen_copper_ore",
            properties -> new Block(properties.strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final Block FROZEN_GOLD_ORE = registerBlock("frozen_gold_ore",
            properties -> new DropExperienceBlock(ConstantInt.of(0),
                    properties.strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final Block FROZEN_COAL_ORE = registerBlock("frozen_coal_ore",
            properties -> new DropExperienceBlock(UniformInt.of(0,2),
                    properties.strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final Block RAW_CRYONITE_BLOCK = registerBlock("raw_cryonite_block",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops().strength(5.0F, 6.0F)));

    public static final Block CRYONITE_BLOCK = registerBlock("cryonite_block",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL).strength(5.0F, 6.0F)));

    public static final Block FROZEN_GRASS = registerBlock("frozen_grass",
            properties -> new Block(properties.strength(1.5f)
                    .sound(SoundType.GRASS)));

    public static final Block FROZEN_DIRT = registerBlock("frozen_dirt",
            properties -> new Block(properties.strength(1f)
                    .sound(SoundType.GRAVEL)));

    public static final Block FROZEN_BERRIES_BUSH = registerBlockWithoutBlockItem("frozen_berries_bush",
            properties -> new FrozenBerriesBlock(properties
                    .noCollision()
                    .randomTicks()
                    .sound(SoundType.SWEET_BERRY_BUSH)));

    public static final Block ICEBLOOM_LOG = registerBlock("icebloom_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block ICEBLOOM_WOOD = registerBlock("icebloom_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block STRIPPED_ICEBLOOM_LOG = registerBlock("stripped_icebloom_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block STRIPPED_ICEBLOOM_WOOD = registerBlock("stripped_icebloom_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block ICEBLOOM_PLANKS = registerBlock("icebloom_planks",
            properties -> new Block(properties.strength(2f).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block ICEBLOOM_LEAVES = registerBlock("icebloom_leaves",
            properties -> new UntintedParticleLeavesBlock(0.1f, ParticleTypes.SNOWFLAKE, properties.mapColor(MapColor.PLANT)
                    .strength(0.2F)
                    .randomTicks()
                    .sound(SoundType.AZALEA_LEAVES)
                    .noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating(Blocks::never)
                    .isViewBlocking(Blocks::never)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(Blocks::never)));

    public static final Block ICEBLOOM_SAPLING = registerBlock("icebloom_sapling",
            properties -> new SaplingBlock(ModTreeGrowers.ICEBLOOM,
                    properties.mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));

    public static final Block POTTED_ICEBLOOM_SAPLING = registerBlockWithoutBlockItem("potted_icebloom_sapling",
            properties -> new FlowerPotBlock(ICEBLOOM_SAPLING,
                    properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));


    public static final Block WILLOW_LOG = registerBlock("willow_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block WILLOW_WOOD = registerBlock("willow_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block STRIPPED_WILLOW_LOG = registerBlock("stripped_willow_log",
            properties -> new RotatedPillarBlock(properties.strength(2f).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block STRIPPED_WILLOW_WOOD = registerBlock("stripped_willow_wood",
            properties -> new RotatedPillarBlock(properties.strength(2f).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block WILLOW_PLANKS = registerBlock("willow_planks",
            properties -> new Block(properties.strength(2f).sound(SoundType.WOOD).ignitedByLava()));

    public static final Block WILLOW_LEAVES = registerBlock("willow_leaves",
            properties -> new UntintedParticleLeavesBlock(0.1f, ParticleTypes.SNOWFLAKE, properties.mapColor(MapColor.PLANT)
                    .strength(0.2F)
                    .randomTicks()
                    .sound(SoundType.AZALEA_LEAVES)
                    .noOcclusion()
                    .isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating(Blocks::never)
                    .isViewBlocking(Blocks::never)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(Blocks::never)));

    public static final Block WILLOW_SAPLING = registerBlock("willow_sapling",
            properties -> new SaplingBlock(ModTreeGrowers.WILLOW,
                    properties.mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));

    public static final Block POTTED_WILLOW_SAPLING = registerBlockWithoutBlockItem("potted_willow_sapling",
            properties -> new FlowerPotBlock(ICEBLOOM_SAPLING,
                    properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final Block ICEBLOOM_FLOWER = registerBlock("icebloom_flower",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0.35F,
                    properties.mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    public static final Block POTTED_ICEBLOOM_FLOWER = registerBlockWithoutBlockItem("potted_icebloom_flower",
            properties -> new FlowerPotBlock(ICEBLOOM_FLOWER,
                    properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final Block WASTELAND_FLOWER = registerBlock("wasteland_flower",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0.35F,
                    properties.mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY)));

    public static final Block POTTED_WASTELAND_FLOWER = registerBlockWithoutBlockItem("potted_wasteland_flower",
            properties -> new FlowerPotBlock(WASTELAND_FLOWER,
                    properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final Block MAGIC_FLOWER = registerBlock("magic_flower",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0.35F,
                    properties.mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel(statex -> 9).pushReaction(PushReaction.DESTROY)));

    public static final Block POTTED_MAGIC_FLOWER = registerBlockWithoutBlockItem("potted_magic_flower",
            properties -> new FlowerPotBlock(MAGIC_FLOWER,
                    properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));


    public static final Block FROZEN_PORTAL = registerBlock("frozen_portal",
            properties -> new FrozenPortalBlock(properties.strength(3f)));








    // reģistrē jaunu bloku spēlē
    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(FrozenDimension.MOD_ID, name))));
        registerBlockitem(name, toRegister);

        // Pievieno bloku reģistram,
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(FrozenDimension.MOD_ID, name), toRegister);
    }


    // reģistrē bloku kā priekšmetu inventory
    // BlockItem ir bloks kurš ir inventory nevis tad kurs stav pasaule
    private static void registerBlockitem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(FrozenDimension.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(FrozenDimension.MOD_ID, name)))));
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(FrozenDimension.MOD_ID, name))));
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(FrozenDimension.MOD_ID, name), toRegister);
    }


    public static void registerModBlocks() {
        FrozenDimension.LOGGER.info("Registering Mod Blocks for " + FrozenDimension.MOD_ID);
    }
}
