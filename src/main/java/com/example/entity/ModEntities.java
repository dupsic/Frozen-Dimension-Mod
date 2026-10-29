package com.example.entity;

import com.example.FrozenDimention;
import com.example.entity.custom.FrozenZombieEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> FROZENZOMBIE_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frozenzombie"));



    public static final EntityType<FrozenZombieEntity> FROZENZOMBIE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, "frozenzombie"),
            EntityType.Builder.of(FrozenZombieEntity::new, MobCategory.CREATURE).sized(1f, 2f).build(FROZENZOMBIE_KEY));


    public static void registerModEntities() {
        FrozenDimention.LOGGER.info("Registering Mod Entities for " + FrozenDimention.MOD_ID);
    }
}
