
package com.example.item.custom;

import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.phys.*;

public class CryoniteStaff extends Item {

    private static final double RANGE = 32.0;
    private static final int COOLDOWN = 60;
    private static final float DAMAGE = 10.0F;
    private static final double SPLASH_RADIUS = 3.0;

    public CryoniteStaff(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        if (player.getCooldowns().isOnCooldown(stack)) {
            return InteractionResult.FAIL;
        }

        Vec3 start = player.getEyePosition();

        Vec3 end = findLaserEnd(serverLevel, player, start);

        spawnParticles(serverLevel, start, end);

        playSound(serverLevel, player);

        player.getCooldowns().addCooldown(stack, COOLDOWN);

        return InteractionResult.SUCCESS;
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

    private void playSound(ServerLevel level, Player player) {

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
