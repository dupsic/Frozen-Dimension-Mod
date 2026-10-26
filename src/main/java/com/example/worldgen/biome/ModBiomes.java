
package com.example.worldgen.biome;

import com.example.FrozenDimention;
import com.example.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomes {
    public static final ResourceKey<Biome> FROZEN_FOREST = ResourceKey.create(
            Registries.BIOME, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frozen_forest"));

    public static final ResourceKey<Biome> FROZEN_WASTELAND = ResourceKey.create(
            Registries.BIOME, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frozen_wasteland"));

    public static final ResourceKey<Biome> FROSTPEAK_MOUNTAINS = ResourceKey.create(
            Registries.BIOME, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frostpeak_mountains"));

    public static final ResourceKey<Biome> FROZEN_MARSH = ResourceKey.create(
            Registries.BIOME, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frozen_marsh"));

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(FROZEN_FOREST, frozenForest(context));
        context.register(FROZEN_WASTELAND, frozenWasteland(context));
        context.register(FROSTPEAK_MOUNTAINS, frostpeakMountains(context));
        context.register(FROZEN_MARSH, frozenMarsh(context));
    }

    private static Biome frozenForest(BootstrapContext<Biome> context) {

        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);

        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);


        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.ICEBLOOM_PLACED_KEY);

        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.CRYONITE_ORE_PLACED_KEY);

        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.FROZEN_COAL_KEY);

        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.FROZEN_IRON_KEY);

        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.FROZEN_COPPER_KEY);

        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.FROZEN_GOLD_KEY);

        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.FROZEN_REDSTONE_KEY);

        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.FROZEN_DIAMOND_KEY);

        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.FROZEN_EMERALD_KEY);

        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

        BiomeSpecialEffects effects =
                new BiomeSpecialEffects.Builder()
                        .waterColor(0x55CBE8)
                        .foliageColorOverride(0x75D9E8)
                        .grassColorOverride(0x83DCEB)
                        .build();

        return new Biome.BiomeBuilder()
                .temperature(-0.7F)
                .downfall(0.8F)
                .hasPrecipitation(true)
                .specialEffects(effects)
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build();
    }

    private static Biome frozenWasteland(BootstrapContext<Biome> context) {

        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);

        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);

        // cryonite ores
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.CRYONITE_ORE_PLACED_KEY);

        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                        .waterColor(0x4ABBD9)
                        .foliageColorOverride(0xA0E8F5)
                        .grassColorOverride(0xC5F4FF)
                        .build();

        return new Biome.BiomeBuilder()
                .temperature(-0.9F)
                .downfall(0.5F)
                .hasPrecipitation(true)
                .specialEffects(effects)
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build();

    }

    private static Biome frostpeakMountains(BootstrapContext<Biome> context) {

        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);

        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);

        // cryonite ores
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.CRYONITE_ORE_PLACED_KEY);

        generation.addFeature(
                GenerationStep.Decoration.SURFACE_STRUCTURES,
                ModPlacedFeatures.RARE_ICE_SPIKE
        );
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                        .waterColor(0x4ABBD9)
                        .foliageColorOverride(0xA0E8F5)
                        .grassColorOverride(0xC5F4FF)
                        .build();

        return new Biome.BiomeBuilder()
                .temperature(-0.9F)
                .downfall(0.5F)
                .hasPrecipitation(true)
                .specialEffects(effects)
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build();

    }

    private static Biome frozenMarsh(BootstrapContext<Biome> context) {

        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);

        HolderGetter<ConfiguredWorldCarver<?>> carvers = context.lookup(Registries.CONFIGURED_CARVER);

        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(features, carvers);

        // cryonite ores
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.CRYONITE_ORE_PLACED_KEY);

        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                        .waterColor(0x4ABBD9)
                        .foliageColorOverride(0xA0E8F5)
                        .grassColorOverride(0xC5F4FF)
                        .build();

        return new Biome.BiomeBuilder()
                .temperature(-0.9F)
                .downfall(0.5F)
                .hasPrecipitation(true)
                .specialEffects(effects)
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build();

    }
}
