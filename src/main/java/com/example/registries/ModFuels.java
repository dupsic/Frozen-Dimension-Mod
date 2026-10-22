package com.example.registries;

import com.example.item.ModItems;
import net.fabricmc.fabric.api.registry.FuelValueEvents;

public class ModFuels
{
    public static void registerFuels() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.FROZEN_COAL, 200  * 10);
        });
    }
}