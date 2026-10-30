package com.example.item.custom;

import com.example.item.ModItems;
import com.example.tags.ModTags;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class CryonitePickaxe {

    private static final Set<UUID> miningPlayers = new HashSet<>();

    private static final int MAX_ORES = 128;

    public static void register() {

        PlayerBlockBreakEvents.AFTER.register(
                (level, player, pos, state, blockEntity) -> {

                    if (!(player instanceof ServerPlayer serverPlayer)) {
                        return;
                    }

                    UUID playerId = player.getUUID();

                    if (miningPlayers.contains(playerId)) {
                        return;
                    }

                    if (!player.getMainHandItem().is(ModItems.CRYONITE_PICKAXE)) {
                        return;
                    }

                    if (!isValidOre(state)) {
                        return;
                    }

                    Set<BlockPos> ores = findOres(level, pos, state);

                    miningPlayers.add(playerId);


                    try {

                        breakOres(serverPlayer, level, pos, state, ores);

                    } finally {
                        miningPlayers.remove(playerId);
                    }
                }
        );
    }


    private static boolean isValidOre(BlockState state) {

        return state.is(ConventionalBlockTags.ORES)
                || state.is(ModTags.Blocks.CRYONITE_MINEABLE);
    }


    public static Set<BlockPos> findOres(Level level, BlockPos startPos, BlockState originalState) {

        Queue<BlockPos> queue = new ArrayDeque<>();
        Set<BlockPos> visited = new HashSet<>();

        queue.add(startPos);
        visited.add(startPos);

        while (!queue.isEmpty() && visited.size() < MAX_ORES) {

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

                        if (!neighborState.is(originalState.getBlock())) {
                            continue;
                        }
                        if (visited.size() >= MAX_ORES) {
                            continue;
                        }

                        if (visited.add(neighbor)) {
                            queue.add(neighbor);
                        }
                    }
                }
            }
        }
        return visited;
    }

    private static void breakOres(ServerPlayer player, Level level, BlockPos originalPos, BlockState originalState, Set<BlockPos> ores) {


        for (BlockPos orePos : ores) {

            if (orePos.equals(originalPos)) {
                continue;
            }

            if (!player.getMainHandItem().is(ModItems.CRYONITE_PICKAXE)) {
                break;
            }

            BlockState targetState = level.getBlockState(orePos);

            if (!targetState.is(originalState.getBlock())) {
                continue;
            }

            player.gameMode.destroyBlock(orePos);
        }
    }



}