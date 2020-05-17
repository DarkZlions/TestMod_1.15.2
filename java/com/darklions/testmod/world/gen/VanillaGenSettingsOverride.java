package com.darklions.testmod.world.gen;

import java.lang.reflect.Field;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationSettings;

public class VanillaGenSettingsOverride 
{
	public static void overrideSettings()
	{
		try 
		{
			Class<?> genSettings = Class.forName("net.minecraft.world.gen.GenerationSettings");
			GenerationSettings gen = (GenerationSettings) genSettings.newInstance();
			Field defaultBlockGen = genSettings.getDeclaredField("defaultBlock");
			defaultBlockGen.setAccessible(true);
			defaultBlockGen.set(gen.getDefaultBlock(), Blocks.BRICKS.getDefaultState());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
}
