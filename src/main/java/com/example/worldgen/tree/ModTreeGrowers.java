package com.example.worldgen.tree;

import com.example.FrozenDimension;
import com.example.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {

    public static final TreeGrower ICEBLOOM = new TreeGrower(FrozenDimension.MOD_ID + "icebloom",
            Optional.empty(), Optional.of(ModConfiguredFeatures.ICEBLOOM_TREE_KEY), Optional.empty());

    public static final TreeGrower WILLOW = new TreeGrower(FrozenDimension.MOD_ID + "willow",
            Optional.empty(), Optional.of(ModConfiguredFeatures.WILLOW_TREE_KEY), Optional.empty());


}
