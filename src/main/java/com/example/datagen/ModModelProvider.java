package com.example.datagen;

import com.example.block.ModBlocks;
import com.example.block.custom.FrozenBerriesBlock;
import com.example.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.DARK_CRYONITE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.DARK_BLUE_STONE);
        blockModelGenerators.createTrivialCube(ModBlocks.LIGHT_BLUE_STONE);
        blockModelGenerators.createTrivialCube(ModBlocks.FROZEN_DIRT);
        blockModelGenerators.createTrivialCube(ModBlocks.CRYONITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.RAW_CRYONITE_BLOCK);

        blockModelGenerators.createTrivialBlock(ModBlocks.FROZEN_GRASS, TexturedModel.CUBE_TOP_BOTTOM);
        blockModelGenerators.createCrossBlock(ModBlocks.FROZEN_BERRIES_BUSH, BlockModelGenerators.PlantType.NOT_TINTED, FrozenBerriesBlock.AGE, 0,1,2,3);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.RAW_CRYONITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CRYONITE_INGOT, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.FROZEN_COAL, ModelTemplates.FLAT_ITEM);

    }
}
