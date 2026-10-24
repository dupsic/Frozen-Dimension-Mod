
package com.example.worldgen.dimension;

import com.example.FrozenDimention;
import com.example.block.ModBlocks;
import com.example.worldgen.surface.ModSurfaceRules;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModNoiseSettings {

    public static final ResourceKey<NoiseGeneratorSettings> FROZEN_NOISE =
            ResourceKey.create(Registries.NOISE_SETTINGS, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frozen_noise"));

    public static void bootstrap(
            BootstrapContext<NoiseGeneratorSettings> context) {

        var densityFunctions = context.lookup(Registries.DENSITY_FUNCTION);
        var noises = context.lookup(Registries.NOISE);
        var noiseRouter = NoiseRouterData.overworld(densityFunctions, noises, true, false);

        NoiseGeneratorSettings settings =
                new NoiseGeneratorSettings(
                        NoiseSettings.create(-64, 384, 1, 2),
                        ModBlocks.LIGHT_BLUE_STONE.defaultBlockState(),
                        Blocks.WATER.defaultBlockState(),
                        noiseRouter,
                        ModSurfaceRules.createRules(),
                        java.util.List.of(),
                        63,
                        false,
                        true,
                        true,
                        false
                );

        context.register(FROZEN_NOISE, settings);
    }
}
