package com.darklions.testmod.world.gen.ore;

import com.darklions.testmod.init.BlockInit;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class CustomOreGen 
{
	private static ConfiguredPlacement<CountRangeConfig> RubyOrePlacement = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 2, 3, 50));
	private static final CustomOreFeature OREGEN = new CustomOreFeature(null);
	
	public static void setupOverWorldOreGeneration()
	{
		for(Biome biome : ForgeRegistries.BIOMES)
		{
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration
					(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.ruby_ore.getDefaultState(), 8)).withPlacement(RubyOrePlacement));
		}
	}
	
	public static void setupEndOreGeneration()
	{
		for(Biome biome : ForgeRegistries.BIOMES)
		{
			OREGEN.ChooseBlock(Blocks.END_STONE);
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, OREGEN.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.end_ruby_ore.getDefaultState(), 8)).withPlacement(RubyOrePlacement));
		}
	}
	
	public static void setupNetherOreGeneration()
	{
		for(Biome biome : ForgeRegistries.BIOMES)
		{
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration
					(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, BlockInit.nether_ruby_ore.getDefaultState(), 5)).withPlacement(RubyOrePlacement));
		}
	}
}
//DefaultBiomeFeatures