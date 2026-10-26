
package com.example.worldgen.surface;

import com.example.block.ModBlocks;
import com.example.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import java.rmi.MarshalledObject;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource FROZEN_GRASS =
            SurfaceRules.state(ModBlocks.FROZEN_GRASS.defaultBlockState());

    private static final SurfaceRules.RuleSource FROZEN_DIRT =
            SurfaceRules.state(ModBlocks.FROZEN_DIRT.defaultBlockState());

    private static final SurfaceRules.RuleSource BEDROCK =
            SurfaceRules.state(Blocks.BEDROCK.defaultBlockState());

    private static final SurfaceRules.RuleSource LIGHT_BLUE_STONE =
            SurfaceRules.state(ModBlocks.LIGHT_BLUE_STONE.defaultBlockState());

    private static final SurfaceRules.RuleSource SNOW =
            SurfaceRules.state(Blocks.SNOW_BLOCK.defaultBlockState());

    private static final SurfaceRules.RuleSource STONE =
            SurfaceRules.state(ModBlocks.DARK_BLUE_STONE.defaultBlockState());

    public static SurfaceRules.RuleSource createRules(HolderGetter<Biome> biomes) {
        return SurfaceRules.sequence(

                // pasaules apaksa ir bedrock
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),

                // frost peak mountains biome
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, ModBiomes.FROSTPEAK_MOUNTAINS),
                        SurfaceRules.ifTrue(
                                SurfaceRules.abovePreliminarySurface(),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SNOW),
                                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, STONE)
                                )
                        )
                ),

                // zemes bloki ir augsa, bet ne alas
                SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(0, 0), FROZEN_GRASS)),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, FROZEN_DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, FROZEN_DIRT)
                        )
                ),

                // zeme ir akmens
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, LIGHT_BLUE_STONE)


        );
    }
}
