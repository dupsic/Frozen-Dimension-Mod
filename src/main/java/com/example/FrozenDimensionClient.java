package com.example;

import com.example.entity.ModEntities;
import com.example.entity.client.FrozenZombieModel;
import com.example.entity.client.FrozenZombieRenderer;
import com.example.entity.client.ModModelLayerLocation;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;

public class FrozenDimensionClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(ModModelLayerLocation.FROZENZOMBIE, FrozenZombieModel::createBodyLayer);
        EntityRenderers.register(ModEntities.FROZENZOMBIE, FrozenZombieRenderer::new);

    }
}
