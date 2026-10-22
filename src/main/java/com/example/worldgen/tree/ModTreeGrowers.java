package com.example.worldgen.tree;

import com.example.FrozenDimention;
import com.example.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {

    public static final TreeGrower ICEBLOOM = new TreeGrower(FrozenDimention.MOD_ID + "icebloom",
            Optional.empty(), Optional.of(ModConfiguredFeatures.ICEBLOOM_KEY), Optional.empty());
}
