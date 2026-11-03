package com.example.item.custom;

import com.example.item.ModItems;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

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

            if (!canTill(level, pos)) {
                return InteractionResult.PASS;
            }
            return InteractionResult.SUCCESS;
        });
    }

    static boolean canTill(Level level, BlockPos pos) {
        return level.getBlockState(pos).is(BlockTags.SUPPORTS_VEGETATION) && level.getBlockState(pos.above()).isAir();
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
