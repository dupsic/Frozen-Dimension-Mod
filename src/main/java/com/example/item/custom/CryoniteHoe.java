package com.example.item.custom;

import com.example.item.ModItems;
import com.example.tags.ModTags;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CryoniteHoe {


//    private static final Set<UUID> hoeingPlayers = new HashSet<>();

    public static void register() {

        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {

            if (!player.getItemInHand(hand).is(ModItems.CRYONITE_HOE)) {
                return InteractionResult.PASS;
            }

            if (player.isShiftKeyDown()) {
                return InteractionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();

            if (!level.isClientSide()) {
                tillBlocks(level, pos);
            }

            return InteractionResult.SUCCESS;
        });
    }

    private static void tillBlocks(Level level, BlockPos pos) {

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {

                BlockPos target = pos.offset(x, 0, z);

                BlockState state = level.getBlockState(target);

                if (!state.is(BlockTags.SUPPORTS_VEGETATION)) {
                    continue;
                }

                if (!level.getBlockState(target.above()).isAir()) {
                    continue;
                }

                level.setBlockAndUpdate(target, Blocks.FARMLAND.defaultBlockState());
            }
        }
    }

}
