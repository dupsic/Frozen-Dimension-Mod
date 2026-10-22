package com.example.worldgen.dimension;

import com.example.FrozenDimention;
import com.example.worldgen.biome.ModBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.*;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.Optional;

public class ModDimensions {
    public static final ResourceKey<LevelStem> FROZEN_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frozen"));
     public static final ResourceKey<Level> FROZEN_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frozen"));
     public static final ResourceKey<DimensionType> FROZEN_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frozen_type"));


     public static void bootstrapType (BootstrapContext<DimensionType> context) {
         var timelines = context.lookup(Registries.TIMELINE);
         var clocks = context.lookup(Registries.WORLD_CLOCK);
         var blocks = context.lookup(Registries.BLOCK);

         context.register(FROZEN_DIM_TYPE, new DimensionType(
                 false, // hasFixedTime
                 true, // hasSkylight
                 false, // hasCeiling
                 false, // hasEnderDragonFight
                 1.0, // coordinateScale
                 -64, // minY
                 384, // height
                 384, // logicalHeight
                 blocks.getOrThrow(BlockTags.INFINIBURN_END), // infiniburn
                 0.0f, // ambientLight
                 new DimensionType.MonsterSettings(ConstantInt.of(0), 0),
                 DimensionType.Skybox.OVERWORLD,
                 CardinalLighting.Type.DEFAULT,
                 EnvironmentAttributeMap.builder()
                         .set(EnvironmentAttributes.FOG_COLOR, 0xFF8FAFC5)
                         .set(EnvironmentAttributes.SKY_COLOR, 0xFF76B4DF)
                         .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0xFFB0C9DB)
                         .set(EnvironmentAttributes.CLOUD_COLOR, 0xFFD5E6F0)
                         .set(EnvironmentAttributes.CLOUD_HEIGHT, 192F)
                         .set(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic.OVERWORLD)
                         .set(EnvironmentAttributes.BED_RULE, BedRule.CAN_SLEEP_WHEN_DARK)
                         .set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, false)
                         .set(EnvironmentAttributes.NETHER_PORTAL_SPAWNS_PIGLINS, false)
                         .set(EnvironmentAttributes.AMBIENT_SOUNDS, AmbientSounds.LEGACY_CAVE_SETTINGS)
                         .build(),
                 timelines.getOrThrow(TimelineTags.IN_OVERWORLD),
                 Optional.of(clocks.getOrThrow(WorldClocks.OVERWORLD))));

     }


    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator chunkGenerator = new NoiseBasedChunkGenerator(new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.FROZEN_FOREST)),
                        noiseGenSettings.getOrThrow(ModNoiseSettings.FROZEN_NOISE));

        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.FROZEN_DIM_TYPE), chunkGenerator);

        context.register(FROZEN_KEY, stem);
    }
}
