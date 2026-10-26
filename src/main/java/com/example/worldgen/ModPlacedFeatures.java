package com.example.worldgen;

import com.example.FrozenDimention;
import com.example.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> ICEBLOOM_PLACED_KEY = registerKey("icebloom_placed");
    public static final ResourceKey<PlacedFeature> RARE_ICEBLOOM_TREE_PLACED_KEY = registerKey("rare_icebloom_tree");
    public static final ResourceKey<PlacedFeature> CRYONITE_ORE_PLACED_KEY = registerKey("cryonite_ore_placed");
    public static final ResourceKey<PlacedFeature> FROZEN_COAL_PLACED_KEY = registerKey("frozen_coal");
    public static final ResourceKey<PlacedFeature> FROZEN_IRON_PLACED_KEY = registerKey("frozen_iron");
    public static final ResourceKey<PlacedFeature> FROZEN_COPPER_PLACED_KEY = registerKey("frozen_copper");
    public static final ResourceKey<PlacedFeature> FROZEN_GOLD_PLACED_KEY = registerKey("frozen_gold");
    public static final ResourceKey<PlacedFeature> FROZEN_REDSTONE_PLACED_KEY = registerKey("frozen_redstone");
    public static final ResourceKey<PlacedFeature> FROZEN_DIAMOND_PLACED_KEY = registerKey("frozen_diamond");
    public static final ResourceKey<PlacedFeature> FROZEN_EMERALD_PLACED_KEY = registerKey("frozen_emerald");
    public static final ResourceKey<PlacedFeature> FROZEN_LAPIS_PLACED_KEY = registerKey("frozen_lapis");
    public static final ResourceKey<PlacedFeature> FROZEN_BERRIES_PLACED_KEY = registerKey("frozen_berries");

    public static final ResourceKey<PlacedFeature> RARE_ICE_SPIKE = ResourceKey.create(
                    Registries.PLACED_FEATURE,
                    Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "rare_ice_spike")
            );
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ICEBLOOM_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ICEBLOOM_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.ICEBLOOM_SAPLING));

        register(context, RARE_ICEBLOOM_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ICEBLOOM_KEY),
                VegetationPlacements.treePlacement(RarityFilter.onAverageOnceEvery(8),
                        ModBlocks.ICEBLOOM_SAPLING
                ));

        register(context, FROZEN_BERRIES_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_BERRIES_KEY),
                List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));

        register(context, CRYONITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CRYONITE_ORE_KEY),
                List.of(
                        CountPlacement.of(8),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(32)),
                        BiomeFilter.biome()
                )
        );

        registerOre(context, FROZEN_COAL_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_COAL_KEY),
                20,
                HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(0),
                        VerticalAnchor.absolute(192)
                ));

        registerOre(context, FROZEN_IRON_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_IRON_KEY),
                10,
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-24),
                        VerticalAnchor.absolute(56)
                ));

        registerOre(context, FROZEN_COPPER_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_COPPER_KEY),
                16,
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-16),
                        VerticalAnchor.absolute(112)
                ));

        registerOre(context, FROZEN_GOLD_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_GOLD_KEY),
                4,
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(32)
                ));

        registerOre(context, FROZEN_REDSTONE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_REDSTONE_KEY),
                4,
                HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(15)
                ));

        registerOre(context, FROZEN_DIAMOND_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_DIAMOND_KEY),
                7,
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-80),
                        VerticalAnchor.absolute(80)
                ));

        registerOre(context, FROZEN_EMERALD_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_EMERALD_KEY),
                100,
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-16),
                        VerticalAnchor.absolute(480)
                ));

     registerOre(context, FROZEN_LAPIS_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FROZEN_LAPIS_KEY),
                100,
                HeightRangePlacement.triangle(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(15)
                ));

        context.register(
                RARE_ICE_SPIKE,
                new PlacedFeature(
                        configuredFeatures.getOrThrow(MiscOverworldFeatures.ICE_SPIKE),
                        List.of(
                                RarityFilter.onAverageOnceEvery(16),
                                InSquarePlacement.spread(),
                                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                                BiomeFilter.biome()
                        )
                )
        );
    }






    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, name));
    }

    private static void registerOre(
            BootstrapContext<PlacedFeature> context,
            ResourceKey<PlacedFeature> placedKey,
            Holder<ConfiguredFeature<?, ?>> configuredFeature,
            int count,
            HeightRangePlacement height
    ) {
        register(context, placedKey, configuredFeature, List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                height,
                BiomeFilter.biome())
        );
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuraion, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuraion, List.copyOf(modifiers)));
    }


}
