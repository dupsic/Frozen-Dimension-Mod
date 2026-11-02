
package com.example.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Set;

public class RiftStaff extends Item {

    private static final double RANGE = 200.0;
    private static final int COOLDOWN = 60;

    public RiftStaff(Properties properties) {
        super(properties);
    }

    //
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        if (player.getCooldowns().isOnCooldown(stack)) {
            return InteractionResult.FAIL;
        }

        // logika sakas

        // funkcija kas nosala xyz bloka
        BlockPos pos = getBlock(player, RANGE);

        if (level.getBlockState(pos).isAir()) {
            return InteractionResult.FAIL;
        }



        // funkcijas kas teleporte

        if (player instanceof ServerPlayer serverPlayer) {
            teleportPlayer(serverPlayer, pos);
        }


        // funkcija kas uztaisa skanu

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENDERMAN_TELEPORT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );

        // funkcija kas uztaisa particles
        serverLevel.sendParticles(
                ParticleTypes.PORTAL,
                player.getX(),
                player.getY(),
                player.getZ(),
                50,
                0.4,
                0.8,
                0.4,
                0.2
        );

        serverLevel.sendParticles(
                ParticleTypes.PORTAL,
                pos.getX(),
                pos.getY(),
                pos.getZ(),
                50,
                0.4,
                0.8,
                0.4,
                0.2
        );

        // logikas beidzas


        player.getCooldowns().addCooldown(stack, COOLDOWN);

        return InteractionResult.SUCCESS;
    }


    private static BlockPos getBlock(Player player, double range) {

        HitResult hit = player.pick(range, 0.0F, false);

        if (hit instanceof BlockHitResult blockHit) {
            return blockHit.getBlockPos();
        }

        return null;
    }

    private static void teleportPlayer(ServerPlayer player, BlockPos pos) {

        player.teleportTo(player.level(),
                pos.getX() + 0.5,
                pos.getY() + 1,
                pos.getZ() + 0.5,
                Set.of(),
                player.getYRot(),
                player.getXRot(),
                false
        );
    }
}

