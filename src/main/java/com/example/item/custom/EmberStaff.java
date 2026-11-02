package com.example.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EmberStaff extends Item {
    public EmberStaff(Properties properties) {
        super(properties);
    }
    private static final int FIREBALL_COOLDOWN = 100;
    private static final int IGNITE_COOLDOWN = 0;
    private static final int FIRE_COOLDOWN = 5;
    private static final Map<UUID, Integer> playerModes = new HashMap<>();

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        if (player.getCooldowns().isOnCooldown(stack)) {
            return InteractionResult.FAIL;
        }
        UUID playerId = player.getUUID();
        int mode = playerModes.getOrDefault(playerId, 0);

        if (player.isShiftKeyDown()) {

            mode = (mode + 1) % 3;
            playerModes.put(playerId, mode);


            if (mode == 0) player.sendSystemMessage(Component.literal("Mode: Blaze attack"));
            if (mode == 1) player.sendSystemMessage(Component.literal("Mode: Fireball attack"));
            if (mode == 2) player.sendSystemMessage(Component.literal("Mode: Flint and Steel"));

            return InteractionResult.SUCCESS;
        }

        switch (mode) {
            case 0 -> {
                shootFire(serverLevel, player);
                level.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.BLAZE_SHOOT,
                        SoundSource.PLAYERS,
                        0.5F,
                        1.0F
                );
                player.getCooldowns().addCooldown(stack, FIRE_COOLDOWN);
            }
            case 1 -> {
                shootFireball(serverLevel, player);
                level.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.BLAZE_AMBIENT,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
                player.getCooldowns().addCooldown(stack, FIREBALL_COOLDOWN);
            }
            case 2 -> {

                fireBlock(serverLevel, player);
                level.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.FLINTANDSTEEL_USE,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
                player.getCooldowns().addCooldown(stack, IGNITE_COOLDOWN);
            }
        }

        return InteractionResult.SUCCESS;
    }


    private static void shootFireball(ServerLevel level, Player player) {

        Vec3 direction = player.getLookAngle();

        Vec3 start = player.getEyePosition().add(direction.scale(1.0));

        LargeFireball fireball = new LargeFireball(
                level,
                player,
                direction,
                5
        );

        fireball.setPos(start.x, start.y, start.z);

        level.addFreshEntity(fireball);
    }

    private static void fireBlock(ServerLevel level, Player player) {

        HitResult hit = player.pick(10.0, 0.0F, false);

        if (!(hit instanceof BlockHitResult blockHit)) {
            return;
        }

        BlockPos pos = blockHit.getBlockPos();

        BlockPos firePos = pos.above();
        BlockState fireState = Blocks.FIRE.defaultBlockState();

        if (level.getBlockState(firePos).isAir() && fireState.canSurvive(level, firePos)) {

            level.setBlockAndUpdate(firePos, fireState);
        }
    }
    private static void shootFire(ServerLevel level, Player player) {
        Vec3 direction = player.getLookAngle();

        Vec3 start = player.getEyePosition().add(direction.scale(1.0));

        SmallFireball fireball = new SmallFireball(
                level,
                player,
                direction
        );

        fireball.setPos(start.x, start.y, start.z);

        level.addFreshEntity(fireball);
    }
}
