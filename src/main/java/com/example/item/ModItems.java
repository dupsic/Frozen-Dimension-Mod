package com.example.item;

import com.example.FrozenDimension;
import com.example.block.ModBlocks;
import com.example.entity.ModEntities;
import com.example.food.ModFoods;
import com.example.item.custom.CryoniteStaff;
import com.example.item.custom.EmberStaff;
import com.example.item.custom.GlacierStaff;
import com.example.item.custom.RiftStaff;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;

import java.util.function.Function;

public class ModItems {

    public static ResourceKey<Item> getRK(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    public static final Item RAW_CRYONITE = registerItem("raw_cryonite", Item::new);
    public static final Item FROZEN_BERRIES = registerItem("frozen_berries", properties -> new BlockItem(ModBlocks.FROZEN_BERRIES_BUSH, properties.food(ModFoods.FROZEN_BERRIES, ModFoods.FROZEN_BERRIES_CONSUMABLE)));
    public static final Item FROZEN_COAL = registerItem("frozen_coal", Item::new);
    public static final Item CRYONITE_INGOT = registerItem("cryonite_ingot", Item::new);

    public static final Item CRYONITE_PICKAXE = registerItem("cryonite_pickaxe", properties -> new Item(properties.pickaxe(ModToolMaterials.CRYONITE, 1F , -2.8F)));
    public static final Item CRYONITE_SWORD = registerItem("cryonite_sword", properties -> new Item(properties.sword(ModToolMaterials.CRYONITE, 3F , -2.8F)));
    public static final Item CRYONITE_SHOVEL = registerItem("cryonite_shovel",
            properties -> new ShovelItem(ModToolMaterials.CRYONITE, 1.5F, -3.0F, properties));
    public static final Item CRYONITE_AXE = registerItem("cryonite_axe",
            properties -> new AxeItem(ModToolMaterials.CRYONITE, 6F , -3.2F, properties));
    public static final Item CRYONITE_HOE = registerItem("cryonite_hoe",
            properties -> new HoeItem(ModToolMaterials.CRYONITE, 0F, -3.0F, properties));
    public static final Item CRYONITE_BOW = registerItem("cryonite_bow", properties -> new BowItem(properties.durability(500)));


    public static final Item CRYONITE_STAFF = registerItem("cryonite_staff", properties -> new CryoniteStaff(properties.durability(500)));
    public static final Item RIFT_STAFF = registerItem("rift_staff", properties -> new RiftStaff(properties.durability(500).useCooldown(3)));
    public static final Item EMBER_STAFF = registerItem("ember_staff", properties -> new EmberStaff(properties.durability(500).useCooldown(8)));
    public static final Item GLACIER_STAFF = registerItem("glacier_staff", properties -> new GlacierStaff(properties.durability(500).useCooldown(5)));


    public static final Item FROZEN_ZOMBIE_SPAWN_EGG = registerItem("frozen_zombie_spawn_egg", properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.FROZENZOMBIE)));


    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(FrozenDimension.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(FrozenDimension.MOD_ID, name)))));

    }

    public static void registerModItems() {
        FrozenDimension.LOGGER.info("Registering Mod Items for " + FrozenDimension.MOD_ID);
    }
}
