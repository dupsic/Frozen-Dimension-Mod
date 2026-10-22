package com.example;

import com.example.datagen.*;
import com.example.worldgen.ModConfiguredFeatures;
import com.example.worldgen.ModPlacedFeatures;
import com.example.worldgen.biome.ModBiomes;
import com.example.worldgen.dimension.ModDimensions;
import com.example.worldgen.dimension.ModNoiseSettings;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class FrozenDimentionDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModItemTagsProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModRegistryDataProvider::new);
	}


	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);


		registryBuilder.add(Registries.LEVEL_STEM, ModDimensions::bootstrapStem);
		registryBuilder.add(Registries.DIMENSION_TYPE, ModDimensions::bootstrapType);

		registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
		registryBuilder.add(Registries.NOISE_SETTINGS, ModNoiseSettings::bootstrap);
	}
}
