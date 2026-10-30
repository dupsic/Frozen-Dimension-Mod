package com.example.item.custom;

import com.example.item.ModItems;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.*;

public class CryoniteShovel {

    private static final Map<UUID, Direction> breakFaces = new HashMap<>();
    private static final Set<UUID> diggingPlayers = new HashSet<>();


    public static void register() {
        PlayerBlockBreakEvents.BEFORE.register(
                (level, player, pos, state, blockEntity) -> {


                    if (!(player instanceof ServerPlayer)) {
                        return true;
                    }

                    UUID playerId = player.getUUID();
                    if (diggingPlayers.contains(playerId)) {
                        return true;
                    }

                    if (!player.getMainHandItem().is(ModItems.CRYONITE_SHOVEL)) {
                        breakFaces.remove(playerId);
                        return true;
                    }

                    BlockHitResult hit = (BlockHitResult) player.pick(5.0, 0.0F, false);
                    breakFaces.put(playerId, hit.getDirection());

                    return true;
                }
        );


        PlayerBlockBreakEvents.AFTER.register(
                (level, player, pos, state, blockEntity) -> {


                    UUID playerId = player.getUUID();
                    if (diggingPlayers.contains(playerId)) {
                        return;
                    }

                    Direction face = breakFaces.remove(playerId);


                    if (!(player instanceof ServerPlayer serverPlayer)) {
                        return;
                    }
                    if (!player.getMainHandItem().is(ModItems.CRYONITE_SHOVEL)) {
                        return;
                    }
                    if (!state.is(BlockTags.MINEABLE_WITH_SHOVEL) || face == null) {
                        return;
                    }


                    diggingPlayers.add(playerId);

                    try {
                        if (!player.isShiftKeyDown()) {
                            breakBlocks(serverPlayer, level, pos, face);
                        }
                    }
                    finally {
                        diggingPlayers.remove(playerId);
                    }
                }
        );
    }

    private static void breakBlocks(ServerPlayer player, Level level, BlockPos pos, Direction face) {

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {

                BlockPos target = getTargetPos(pos, face, x, y);

                if (target.equals(pos)) {
                    continue;
                }

                if (!player.getMainHandItem().is(ModItems.CRYONITE_SHOVEL)) {
                    return;
                }



                BlockState targetState = level.getBlockState(target);
                if (!targetState.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
                    continue;
                }

                player.gameMode.destroyBlock(target);
            }
        }
    }

    private static BlockPos getTargetPos(BlockPos pos, Direction face, int x, int y) {

        return switch (face.getAxis()) {
            case Y -> pos.offset(x, 0, y);
            case X -> pos.offset(0, x, y);
            case Z -> pos.offset(x, y, 0);
        };
    }
}
