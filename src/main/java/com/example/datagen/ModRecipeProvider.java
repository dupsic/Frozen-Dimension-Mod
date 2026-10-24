package com.example.datagen;

import com.example.block.ModBlocks;
import com.example.item.ModItems;
import com.example.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                List<ItemLike> CRYONITE_SMELTABLES = List.of(ModItems.RAW_CRYONITE, ModBlocks.CRYONITE_ORE);
                List<ItemLike> CRYONITE_DIRT_SMELTABLES = List.of(ModBlocks.FROZEN_DIRT, ModBlocks.FROZEN_GRASS);
                List<ItemLike> CRYONITE_STONE_SMELTABLES = List.of(ModBlocks.DARK_BLUE_STONE, ModBlocks.LIGHT_BLUE_STONE);


                oreSmelting(CRYONITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.CRYONITE_INGOT, 0.25F, 200, "cryonite" );
                oreBlasting(CRYONITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.CRYONITE_INGOT, 0.25F, 100, "cryonite" );

                oreSmelting(CRYONITE_DIRT_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, Blocks.DIRT, 0.25f, 200, "frozen");
                oreBlasting(CRYONITE_DIRT_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, Blocks.DIRT, 0.25f, 100, "frozen");

                oreSmelting(CRYONITE_STONE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, Blocks.STONE, 0.25f, 200, "frozen");
                oreBlasting(CRYONITE_STONE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, Blocks.STONE, 0.25f, 100, "frozen");

                shaped(RecipeCategory.TOOLS, ModItems.CRYONITE_PICKAXE)
                        .pattern("CCC")
                        .pattern(" S ")
                        .pattern(" S ")
                        .define('C', ModItems.CRYONITE_INGOT)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.CRYONITE_INGOT), has(ModItems.CRYONITE_INGOT))
                        .group("cryonite_pickaxe")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.CRYONITE_SWORD)
                        .pattern(" C ")
                        .pattern(" C ")
                        .pattern(" S ")
                        .define('C', ModItems.CRYONITE_INGOT)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.CRYONITE_INGOT), has(ModItems.CRYONITE_INGOT))
                        .group("cryonite_sword")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.CRYONITE_AXE)
                        .pattern("CC ")
                        .pattern("CS ")
                        .pattern(" S ")
                        .define('C', ModItems.CRYONITE_INGOT)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.CRYONITE_INGOT), has(ModItems.CRYONITE_INGOT))
                        .group("cryonite_axe")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.CRYONITE_AXE)
                        .pattern(" CC")
                        .pattern(" SC")
                        .pattern(" S ")
                        .define('C', ModItems.CRYONITE_INGOT)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.CRYONITE_INGOT), has(ModItems.CRYONITE_INGOT))
                        .group("cryonite_axe")
                        .save(output, "cryonite_axe_reversed");

                shaped(RecipeCategory.TOOLS, ModItems.CRYONITE_SHOVEL)
                        .pattern(" C ")
                        .pattern(" S ")
                        .pattern(" S ")
                        .define('C', ModItems.CRYONITE_INGOT)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.CRYONITE_INGOT), has(ModItems.CRYONITE_INGOT))
                        .group("cryonite_shovel")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.CRYONITE_HOE)
                        .pattern(" CC")
                        .pattern(" S ")
                        .pattern(" S ")
                        .define('C', ModItems.CRYONITE_INGOT)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.CRYONITE_INGOT), has(ModItems.CRYONITE_INGOT))
                        .group("cryonite_hoe")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.CRYONITE_HOE)
                        .pattern("CC ")
                        .pattern(" S ")
                        .pattern(" S ")
                        .define('C', ModItems.CRYONITE_INGOT)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.CRYONITE_INGOT), has(ModItems.CRYONITE_INGOT))
                        .group("cryonite_hoe")
                        .save(output, "cryonite_hoe_reversed");

                shaped(RecipeCategory.TOOLS, ModItems.CRYONITE_BOW)
                        .pattern(" CS")
                        .pattern("C S")
                        .pattern(" CS")
                        .define('C', ModItems.CRYONITE_INGOT)
                        .define('S', Items.STRING)
                        .unlockedBy(getHasName(ModItems.CRYONITE_INGOT), has(ModItems.CRYONITE_INGOT))
                        .group("cryonite_bow")
                        .save(output);

                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.CRYONITE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRYONITE_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.RAW_CRYONITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_CRYONITE_BLOCK);


                woodFromLogs(ModBlocks.ICEBLOOM_WOOD, ModBlocks.ICEBLOOM_LOG);
                woodFromLogs(ModBlocks.STRIPPED_ICEBLOOM_WOOD, ModBlocks.STRIPPED_ICEBLOOM_LOG);

                planksFromLogs(ModBlocks.ICEBLOOM_PLANKS, ModTags.Items.ICEBLOOM_LOGS, 4);

            }
        };
    };


    @Override
    public String getName() {
        return "Frozen Dimention Recipes";
    }
}
