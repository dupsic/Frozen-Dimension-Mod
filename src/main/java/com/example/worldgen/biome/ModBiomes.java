
package com.example.worldgen.biome;

import com.example.FrozenDimention;
import com.example.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
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
                    Registries.BIOME, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID,"frozen_forest"));



    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(FROZEN_FOREST, frozenForest(context));
    }

    private static Biome frozenForest(BootstrapContext<Biome> context) {

        HolderGetter<PlacedFeature> features =
                context.lookup(Registries.PLACED_FEATURE);

        HolderGetter<ConfiguredWorldCarver<?>> carvers =
                context.lookup(Registries.CONFIGURED_CARVER);

        BiomeGenerationSettings.Builder generation =
                new BiomeGenerationSettings.Builder(features, carvers);



        BiomeDefaultFeatures.addDefaultOres(generation);

        generation.addFeature(
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ModPlacedFeatures.CRYONITE_ORE_PLACED_KEY
        );

        generation.addFeature(
                net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.ICEBLOOM_PLACED_KEY
        );
        MobSpawnSettings.Builder spawns =
                new MobSpawnSettings.Builder();

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
}
