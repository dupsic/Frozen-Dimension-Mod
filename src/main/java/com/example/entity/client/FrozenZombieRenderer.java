package com.example.entity.client;

import com.example.FrozenDimension;
import com.example.entity.custom.FrozenZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class FrozenZombieRenderer extends MobRenderer<FrozenZombieEntity, FrozenZombieRenderState, FrozenZombieModel> {
    public FrozenZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new FrozenZombieModel(context.bakeLayer(ModModelLayerLocation.FROZENZOMBIE)), 0.45f);
    }

    @Override
    public FrozenZombieRenderState createRenderState() {
        return new FrozenZombieRenderState();
    }


    @Override
    public Identifier getTextureLocation(FrozenZombieRenderState state) {
        return Identifier.fromNamespaceAndPath(FrozenDimension.MOD_ID, "textures/entity/frozenzombie/frozen_zombie.png");
    }

    @Override
    public void extractRenderState(FrozenZombieEntity entity, FrozenZombieRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        state.idleAnimationState.copyFrom(entity.idleAnimationState);
    }
}
