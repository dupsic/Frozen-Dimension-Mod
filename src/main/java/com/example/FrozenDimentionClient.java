package com.example;

import com.example.entity.ModEntities;
import com.example.entity.client.FrozenZombieModel;
import com.example.entity.client.FrozenZombieRenderer;
import com.example.entity.client.ModModelLayerLocation;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class FrozenDimentionClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(ModModelLayerLocation.FROZENZOMBIE, FrozenZombieModel::createBodyLayer);
        EntityRenderers.register(ModEntities.FROZENZOMBIE, FrozenZombieRenderer::new);
    }
}
