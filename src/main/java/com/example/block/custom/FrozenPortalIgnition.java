package com.example.block.custom;

import com.example.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FrozenPortalIgnition {

    public static boolean tryActivate(ServerLevel level, BlockPos pos) {

        Direction.Axis[] axes = {Direction.Axis.X, Direction.Axis.Z};

        for (int i = 0; i < axes.length; i++) {
            Direction.Axis axis = axes[i];
            Direction dir;

            if (axis == Direction.Axis.X) {
                dir = Direction.EAST;
            } else {
                dir = Direction.SOUTH;
            }

            for (int dx = 0; dx < 2; dx++) {
                for (int dy = 0; dy < 3; dy++) {

                    BlockPos origin = pos.relative(dir, -dx).below(dy);
                    boolean valid = true;

                    for (int x = -1; x <= 2; x++) {
                        for (int y = -1; y <= 3; y++) {

                            BlockPos check = origin.relative(dir, x).above(y);
                            BlockState state = level.getBlockState(check);

                            boolean corner = false;
                            boolean border = false;

                            if (x == -1 || x == 2 || y == -1 || y == 3) {
                                border = true;
                            }

                            if (x == -1 || x == 2) {
                                if (y == -1 || y == 3) {
                                    corner = true;
                                }
                            }

                            if (corner) {
                                continue;
                            }

                            if (border) {
                                if (!state.is(Blocks.SNOW_BLOCK)) {
                                    valid = false;
                                }
                            } else {
                                if (!state.isAir()) {
                                    valid = false;
                                }
                            }
                        }
                    }

                    if (!valid) {
                        continue;
                    }

                    fillPortal(level, origin, axis);

                    return true;
                }
            }
        }

        return false;
    }




    public static BlockPos createPortal(ServerLevel level, BlockPos pos) {

        Direction.Axis axis = Direction.Axis.X;
        Direction dir = Direction.EAST;

        BlockPos origin = pos;

        for (int x = -1; x <= 2; x++) {
            for (int y = -1; y <= 3; y++) {

                BlockPos blockPos = origin.relative(dir, x).above(y);

                boolean border = false;

                if (x == -1 || x == 2 || y == -1 || y == 3) {
                    border = true;
                }

                if (border) {level.setBlock(blockPos, Blocks.SNOW_BLOCK.defaultBlockState(), 3);
                }
            }
        }

        fillPortal(level, origin, axis);

        return origin;
    }




    private static void fillPortal(ServerLevel level, BlockPos origin, Direction.Axis axis) {
        Direction dir;

        if (axis == Direction.Axis.X) {
            dir = Direction.EAST;
        } else {
            dir = Direction.SOUTH;
        }

        BlockState portal = ModBlocks.FROZEN_PORTAL.defaultBlockState().setValue(FrozenPortalBlock.AXIS, axis);

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 3; y++) {

                BlockPos blockPos = origin.relative(dir, x).above(y);

                level.setBlock(blockPos, portal, 3);
            }
        }
    }
}