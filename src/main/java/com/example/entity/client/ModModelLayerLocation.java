package com.example.entity.client;

import com.example.FrozenDimention;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModModelLayerLocation {
    public static final ModelLayerLocation FROZENZOMBIE =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frozenzombie"), "main");
}
