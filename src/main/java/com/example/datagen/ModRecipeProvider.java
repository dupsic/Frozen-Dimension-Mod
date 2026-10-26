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


                oreSmelting(List.of(ModItems.RAW_CRYONITE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.CRYONITE_INGOT, 0.25F, 200, "cryonite" );
                oreBlasting(List.of(ModItems.RAW_CRYONITE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.CRYONITE_INGOT, 0.25F, 100, "cryonite" );

                oreSmelting(List.of(ModBlocks.FROZEN_COAL_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.FROZEN_COAL, 0.25F, 200, "cryonite" );
                oreBlasting(List.of(ModBlocks.FROZEN_COAL_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.FROZEN_COAL, 0.25F, 100, "cryonite" );


                oreSmelting(List.of(ModBlocks.FROZEN_COPPER_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.COPPER_INGOT, 0.25F, 200, "cryonite" );
                oreBlasting(List.of(ModBlocks.FROZEN_COPPER_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.COPPER_INGOT, 0.25F, 100, "cryonite" );


                oreSmelting(List.of(ModBlocks.FROZEN_DIAMOND_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.DIAMOND, 0.25F, 200, "cryonite" );
                oreBlasting(List.of(ModBlocks.FROZEN_DIAMOND_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.DIAMOND, 0.25F, 100, "cryonite" );


                oreSmelting(List.of(ModBlocks.FROZEN_EMERALD_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.EMERALD, 0.25F, 200, "cryonite" );
                oreBlasting(List.of(ModBlocks.FROZEN_EMERALD_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.EMERALD, 0.25F, 100, "cryonite" );

                oreSmelting(List.of(ModBlocks.FROZEN_GOLD_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.GOLD_INGOT, 0.25F, 200, "cryonite" );
                oreBlasting(List.of(ModBlocks.FROZEN_GOLD_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.GOLD_INGOT, 0.25F, 100, "cryonite" );

                oreSmelting(List.of(ModBlocks.FROZEN_IRON_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.IRON_INGOT, 0.25F, 200, "cryonite" );
                oreBlasting(List.of(ModBlocks.FROZEN_IRON_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.IRON_INGOT, 0.25F, 100, "cryonite" );

                oreSmelting(List.of(ModBlocks.FROZEN_LAPIS_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.LAPIS_LAZULI, 0.25F, 200, "cryonite" );
                oreBlasting(List.of(ModBlocks.FROZEN_LAPIS_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.LAPIS_LAZULI, 0.25F, 100, "cryonite" );

                oreSmelting(List.of(ModBlocks.FROZEN_REDSTONE_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.REDSTONE, 0.25F, 200, "cryonite" );
                oreBlasting(List.of(ModBlocks.FROZEN_REDSTONE_ORE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.REDSTONE, 0.25F, 100, "cryonite" );



                oreSmelting(List.of(ModBlocks.FROZEN_DIRT), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Blocks.DIRT, 0.25f, 200, "frozen");
                oreBlasting(List.of(ModBlocks.FROZEN_DIRT), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Blocks.DIRT, 0.25f, 100, "frozen");

                oreSmelting(List.of(ModBlocks.LIGHT_BLUE_STONE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Blocks.STONE, 0.25f, 200, "frozen");
                oreBlasting(List.of(ModBlocks.LIGHT_BLUE_STONE), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Blocks.STONE, 0.25f, 100, "frozen");

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
