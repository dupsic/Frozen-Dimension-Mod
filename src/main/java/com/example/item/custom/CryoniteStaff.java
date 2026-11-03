package com.example.item.custom;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.*;

import java.util.*;


public class CryoniteStaff extends Item {
    public CryoniteStaff(Properties properties) {
        super(properties);
    }
    private static final Map<UUID, Integer> playerModes = new HashMap<>();
    private static final Map<UUID, Direction> breakFaces = new HashMap<>();
    private static final int ICE_DURATION = 100;
    private static final double RANGE = 32.0;
    private static final float DAMAGE = 10.0F;
    private static final double SPLASH_RADIUS = 3.0;


    private record IceTask(
            ServerLevel level,
            BlockPos pos,
            long removeAt
    ) {}

    private static final List<IceTask> iceTasks = new ArrayList<>();

    public static void register() {

        ServerTickEvents.END_SERVER_TICK.register(server -> {

            iceTasks.removeIf(task -> {

                if (task.level().getGameTime() < task.removeAt()) {
                    return false;
                }

                if (task.level().getBlockState(task.pos()).is(Blocks.ICE)) {
                    task.level().removeBlock(task.pos(), false);
                }

                return true;
            });
        });
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        UUID playerId = player.getUUID();
        BlockHitResult hit = (BlockHitResult) player.pick(6, 0.0F, false);


        BlockHitResult blockHit = hit;

        BlockPos pos = blockHit.getBlockPos();
        breakFaces.put(playerId, hit.getDirection());



        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        if (player.getCooldowns().isOnCooldown(stack)) {
            return InteractionResult.FAIL;
        }


        int mode = playerModes.getOrDefault(playerId, 0);

        if (player.isShiftKeyDown()) {

            mode = (mode + 1) % 2;
            playerModes.put(playerId, mode);


            if (mode == 0) player.sendSystemMessage(Component.literal("Mode: Ice Wall"));
            if (mode == 1) player.sendSystemMessage(Component.literal("Mode: Snow Laser"));

            return InteractionResult.SUCCESS;
        }

        switch (mode) {
            case 0 -> {

                iceWall(serverLevel, player, pos);
                level.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.GLASS_PLACE,
                        SoundSource.PLAYERS,
                        1.0F,
                        0.8F
                );
            }
            case 1 -> {

                Vec3 start = player.getEyePosition();

                Vec3 end = findLaserEnd(serverLevel, player, start);

                spawnParticles(serverLevel, start, end);

                level.playSound(
                        null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.GLASS_BREAK,
                        SoundSource.PLAYERS,
                        1.0F,
                        0.8F
                );
            }

        }

        return InteractionResult.SUCCESS;
    }

    private static void iceWall(ServerLevel level, Player player, BlockPos pos) {
        Direction wallDirection;

        double dx = pos.getX() + 0.5 - player.getX();
        double dz = pos.getZ() + 0.5 - player.getZ();

        if (Math.abs(dx) > Math.abs(dz)) {

            if (dx > 0) {
                wallDirection = Direction.EAST;
            } else {
                wallDirection = Direction.WEST;
            }

        } else {

            if (dz > 0) {
                wallDirection = Direction.SOUTH;
            } else {
                wallDirection = Direction.NORTH;
            }
        }


        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {

                BlockPos target = getTargetPos(pos, wallDirection, x, y);

                level.setBlockAndUpdate(target, Blocks.ICE.defaultBlockState());

                iceTasks.add(new IceTask(level, target.immutable(), level.getGameTime() + ICE_DURATION));
            }
        }
    }

    private static BlockPos getTargetPos(BlockPos pos, Direction face, int x, int y) {

        return switch (face.getAxis()) {
            case X -> pos.offset(0, y+3, x);
            case Z -> pos.offset(x, y+3, 0);
            case Y -> pos.offset(x, y+3, 0);
        };
    }

    private Vec3 findLaserEnd(ServerLevel level, Player player, Vec3 start) {

        Vec3 direction = player.getLookAngle();
        Vec3 end = start.add(direction.scale(RANGE));


        BlockHitResult blockHit = level.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

        if (blockHit.getType() != HitResult.Type.MISS) {
            end = blockHit.getLocation();
        }

        Vec3 ray = end.subtract(start);
        AABB searchBox = player.getBoundingBox().expandTowards(ray).inflate(1.0);


        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                level,
                player,
                start,
                end,
                searchBox,
                entity -> entity instanceof LivingEntity && entity != player && entity.isPickable(), 0.3F);

        if (entityHit != null) {

            Entity target = entityHit.getEntity();

            applyDamage(level, target);
            applySplashDamage(level, player, target);

            end = entityHit.getLocation();
        }

        return end;
    }
    private void applyDamage(ServerLevel level, Entity target) {

        target.hurtServer(level, level.damageSources().magic(), DAMAGE);

        if (target instanceof LivingEntity livingTarget) {
            livingTarget.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 1));
        }
    }

    private void applySplashDamage(ServerLevel level, Player player, Entity hitTarget) {

        Vec3 center = hitTarget.position();

        AABB area = hitTarget.getBoundingBox().inflate(SPLASH_RADIUS);

        for (LivingEntity target : level.getEntitiesOfClass(LivingEntity.class, area, entity -> entity != player && entity != hitTarget)) {


            if (target.distanceToSqr(center) > SPLASH_RADIUS * SPLASH_RADIUS) {
                continue;
            }


            target.hurtServer(level, level.damageSources().magic(), DAMAGE);
            target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 0));
        }
    }

    private void spawnParticles(ServerLevel level, Vec3 start, Vec3 end) {

        Vec3 beam = end.subtract(start);
        double length = beam.length();

        if (length == 0) {
            return;
        }

        Vec3 direction = beam.normalize();

        for (double distance = 0; distance < length; distance += 0.5) {

            Vec3 point = start.add(direction.scale(distance));

            level.sendParticles(
                    ParticleTypes.SNOWFLAKE,
                    point.x,
                    point.y,
                    point.z,
                    30,
                    0.4,
                    0.4,
                    0.4,
                    0.01
            );
        }
    }


}


