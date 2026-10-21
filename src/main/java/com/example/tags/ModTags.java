package com.example.tags;

import com.example.FrozenDimention;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_CRYONITE_TOOL = createTag("needs_cryonite_tool");
        public static final TagKey<Block> INCORRECT_FOR_CRYONITE_TOOL = createTag("incorrect_for_cryonite_tool");
        public static final TagKey<Block> CRYONITE_MINEABLE = createTag("cryonite_mineable");

        public static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, name));


        }
    }

    public static class Items {
        public static final TagKey<Item> CRYONITE_REPAIR = createTag("cryonite_repair");


        public static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(FrozenDimention.MOD_ID, name));
        }


    }

}