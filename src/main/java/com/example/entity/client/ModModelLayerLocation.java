package com.example.entity.client;

import com.example.FrozenDimension;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModModelLayerLocation {
    public static final ModelLayerLocation FROZENZOMBIE =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(FrozenDimension.MOD_ID, "frozenzombie"), "main");
}
