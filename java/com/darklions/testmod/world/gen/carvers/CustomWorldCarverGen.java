package com.darklions.testmod.world.gen.carvers;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

public class CustomWorldCarverGen 
{
	private static Biome[] endBiomes = {Biomes.THE_END, Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS};
	
	public static void setupEndCarverGeneration()
	{
		for(int i = 0; i < endBiomes.length; i++)
		{
			endBiomes[i].addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.148290F)));
			endBiomes[i].addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Feature.VILLAGE.withConfiguration
					(new VillageConfig("village/plains/town_centers", 6)).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		}
	}
}