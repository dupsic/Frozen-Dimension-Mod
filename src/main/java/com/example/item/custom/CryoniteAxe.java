package com.example.item.custom;

import com.example.item.ModItems;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class CryoniteAxe {
    private static final Set<UUID> choppingPlayers = new HashSet<>();
    private static final int MAX_LOGS = 512;

    public static void register() {

        PlayerBlockBreakEvents.AFTER.register(
                (level, player, pos, state, blockEntity) -> {

                    if (!(player instanceof ServerPlayer serverPlayer)) {
                        return;
                    }

                    UUID playerId = player.getUUID();

                    if (choppingPlayers.contains(playerId)) {
                        return;
                    }

                    if (!player.getMainHandItem().is(ModItems.CRYONITE_AXE)) {
                        return;
                    }

                    if (player.isShiftKeyDown()) {
                        return;
                    }

                    if (!state.is(BlockTags.LOGS)) {
                        return;
                    }

                    Set<BlockPos> logs = findLogs(level, pos);

                    if (!hasLeaves(level, logs)) {
                        return;
                    }

                    choppingPlayers.add(playerId);

                    try {
                        breakLogs(serverPlayer, level, pos, logs);
                    } finally {
                        choppingPlayers.remove(playerId);
                    }
                }
        );
    }

    public static Set<BlockPos> findLogs(
            Level level,
            BlockPos startPos

    ) {
        Queue<BlockPos> queue = new ArrayDeque<>();
        Set<BlockPos> visited = new HashSet<>();

        queue.add(startPos);
        visited.add(startPos);

        while (!queue.isEmpty() && visited.size() < MAX_LOGS) {

            BlockPos current = queue.poll();

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {



                        if (x == 0 && y == 0 && z == 0) {
                            continue;
                        }


                        BlockPos neighbor = current.offset(x, y, z);

                        if (visited.contains(neighbor)) {
                            continue;
                        }

                        BlockState neighborState = level.getBlockState(neighbor);

                        if (neighborState.is(BlockTags.LOGS)
                                && visited.size() < MAX_LOGS
                                && visited.add(neighbor)) {

                            queue.add(neighbor);
                        }
                    }
                }
            }
        }

        return visited;
    }

    private static boolean hasLeaves(Level level, Set<BlockPos> logs) {

        for (BlockPos logPos : logs) {

            for (int x = -2; x <= 2; x++) {
                for (int y = -2; y <= 2; y++) {
                    for (int z = -2; z <= 2; z++) {

                        BlockPos checkPos = logPos.offset(x, y, z);

                        if (level.getBlockState(checkPos).is(BlockTags.LEAVES)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    private static void breakLogs(ServerPlayer player, Level level, BlockPos originalPos, Set<BlockPos> logs) {

        for (BlockPos logPos : logs) {

            if (!player.getMainHandItem().is(ModItems.CRYONITE_AXE)) {
                break;
            }

            if (logPos.equals(originalPos)) {
                continue;
            }

            BlockState blockState = level.getBlockState(logPos);

            if (!blockState.is(BlockTags.LOGS)) {
                continue;
            }

            if (player.gameMode.destroyBlock(logPos)) {
                level.levelEvent(2001, logPos, Block.getId(blockState));
            }
        }
    }
}
