package com.example.block.custom;

import com.example.block.ModBlocks;
import com.example.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class FrozenPortalBlock extends Block implements Portal {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    private static final VoxelShape X_SHAPE = Block.box(0, 0, 7, 16, 16, 9);
    private static final VoxelShape Z_SHAPE = Block.box(7, 0, 0, 9, 16, 16);

    public FrozenPortalBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X)
        );

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    public int getPortalTransitionTime(ServerLevel level, Entity entity) {
        return 80;
    }

    @Override
    protected VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        if (state.getValue(AXIS) == Direction.Axis.X) {
            return X_SHAPE;
        } else {
            return Z_SHAPE;
        }
    }

    @Override
    protected void entityInside(
            BlockState state,
            Level level,
            BlockPos pos,
            Entity entity,
            InsideBlockEffectApplier effectApplier,
            boolean isPrecise
    ) {
        if (!entity.canUsePortal(false)) {
            return;
        }

        entity.setAsInsidePortal(this, pos);

        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        if (serverLevel.getRandom().nextFloat() < 0.35F) {
            serverLevel.sendParticles(
                    ParticleTypes.SNOWFLAKE,
                    entity.getX(),
                    entity.getY() + entity.getBbHeight() / 2,
                    entity.getZ(),
                    5,
                    0.4,
                    0.7,
                    0.4,
                    0.02
            );
        }

        if (serverLevel.getRandom().nextFloat() < 0.05F) {
            serverLevel.playSound(
                    null,
                    entity.blockPosition(),
                    SoundEvents.BEACON_AMBIENT,
                    SoundSource.BLOCKS,
                    0.4F,
                    1.5F
            );
        }
    }

    @Override
    public @Nullable TeleportTransition getPortalDestination(
            ServerLevel currentLevel,
            Entity entity,
            BlockPos portalEntryPos
    ) {
        boolean inFrozen = currentLevel.dimension() == ModDimensions.FROZEN_LEVEL_KEY;

        ServerLevel targetLevel;

        if (inFrozen) {
            targetLevel = currentLevel.getServer().getLevel(Level.OVERWORLD);
        } else {
            targetLevel = currentLevel.getServer().getLevel(ModDimensions.FROZEN_LEVEL_KEY);
        }

        if (targetLevel == null) {
            return null;
        }

        BlockPos targetPos = entity.blockPosition();

        BlockPos portalPos = findPortal(targetLevel, targetPos);

        if (portalPos == null) {

            BlockPos surfacePos = targetLevel.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, targetPos);

            BlockPos origin = surfacePos.above();

            portalPos = FrozenPortalIgnition.createPortal(targetLevel, origin);
        }

        Vec3 destination = Vec3.atBottomCenterOf(portalPos);

        return new TeleportTransition(
                targetLevel,
                destination,
                Vec3.ZERO,
                entity.getYRot(),
                entity.getXRot(),
                Relative.DELTA,
                TeleportTransition.PLAY_PORTAL_SOUND
        );
    }

    private static BlockPos findPortal(ServerLevel level, BlockPos center) {

        for (int x = -16; x <= 16; x++) {
            for (int z = -16; z <= 16; z++) {

                BlockPos surface = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, center.offset(x, 0, z));

                for (int y = -8; y <= 8; y++) {

                    BlockPos check = surface.above(y);

                    if (level.getBlockState(check).is(ModBlocks.FROZEN_PORTAL)) {
                        return check;
                    }
                }
            }
        }

        return null;
    }

}


