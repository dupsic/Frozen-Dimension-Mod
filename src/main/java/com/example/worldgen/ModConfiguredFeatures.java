package com.example.worldgen;

import com.example.FrozenDimention;
import com.example.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.debugchart.RemoteDebugSampleType;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ICEBLOOM_KEY = registerKey("icebloom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYONITE_ORE_KEY = registerKey("cryonite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_COAL_KEY = registerKey("frozen_coal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_IRON_KEY = registerKey("frozen_iron");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_DIAMOND_KEY = registerKey("frozen_diamond");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_GOLD_KEY = registerKey("frozen_gold");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_EMERALD_KEY = registerKey("frozen_emerald");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_REDSTONE_KEY = registerKey("frozen_redstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_COPPER_KEY = registerKey("frozen_copper");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROZEN_LAPIS_KEY = registerKey("frozen_lapis");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {


        register(context, ICEBLOOM_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.ICEBLOOM_LOG),
                    new CherryTrunkPlacer(7,2, 2, UniformInt.of(1, 2), UniformInt.of(2, 4), UniformInt.of(-4, -2), UniformInt.of(-1, 1)),

                BlockStateProvider.simple(ModBlocks.ICEBLOOM_LEAVES),
                new CherryFoliagePlacer(
                        ConstantInt.of(3), ConstantInt.of(0),ConstantInt.of(4),0.25f,0.5f,0.25f,0.5f
                ),

                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.DIRT)).build());




        registerOre(context, CRYONITE_ORE_KEY, ModBlocks.CRYONITE_ORE, 7);
        registerOre(context, FROZEN_COAL_KEY, ModBlocks.FROZEN_COAL_ORE, 17);
        registerOre(context, FROZEN_IRON_KEY, ModBlocks.FROZEN_IRON_ORE, 10);
        registerOre(context, FROZEN_DIAMOND_KEY, ModBlocks.FROZEN_DIAMOND_ORE, 4);
        registerOre(context, FROZEN_GOLD_KEY, ModBlocks.FROZEN_GOLD_ORE, 3);
        registerOre(context, FROZEN_EMERALD_KEY, ModBlocks.FROZEN_EMERALD_ORE, 9);
        registerOre(context, FROZEN_REDSTONE_KEY, ModBlocks.FROZEN_REDSTONE_ORE, 9);
        registerOre(context, FROZEN_COPPER_KEY, ModBlocks.FROZEN_COPPER_ORE, 8);
        registerOre(context, FROZEN_LAPIS_KEY, ModBlocks.FROZEN_LAPIS_ORE, 7);

    }



    private static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, name));
    }

    private static void registerOre(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            Block oreBlock,
            int veinSize
    ) {
        List<OreConfiguration.TargetBlockState> targets = List.of(
                OreConfiguration.target(
                        new BlockMatchTest(ModBlocks.LIGHT_BLUE_STONE),
                        oreBlock.defaultBlockState()
                )
        );

        register(context, key, Feature.ORE, new OreConfiguration(targets, veinSize));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuraion) {
        context.register(key, new ConfiguredFeature(feature, configuraion));
    }

}
