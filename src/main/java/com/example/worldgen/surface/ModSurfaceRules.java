
package com.example.worldgen.surface;

import com.example.block.ModBlocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource FROZEN_GRASS =
            SurfaceRules.state(ModBlocks.FROZEN_GRASS.defaultBlockState());

    private static final SurfaceRules.RuleSource FROZEN_DIRT =
            SurfaceRules.state(ModBlocks.FROZEN_DIRT.defaultBlockState());

    public static SurfaceRules.RuleSource createRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(0, 0), FROZEN_GRASS)),
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, FROZEN_DIRT),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, FROZEN_DIRT)
        );
    }
}
