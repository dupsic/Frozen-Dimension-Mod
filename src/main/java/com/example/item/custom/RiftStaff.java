package com.example.item.custom;

import com.example.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class RiftStaff extends Item {
    public RiftStaff(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide()){
            player.sendSystemMessage(Component.literal("Rift Staff activated!"));

        }
        return InteractionResult.SUCCESS;
    }

}
