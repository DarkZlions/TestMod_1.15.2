package com.darklions.testmod.world.gen;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.darklions.testmod.objects.items.StickOfDebug;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationSettings;

public class VanillaGenSettingsOverride 
{
	public static void overrideSettings()
	{
		try 
		{
			StickOfDebug.s = new String("Stage 0");
			Class<?> GenClass = GenerationSettings.class;
			Field field = GenClass.getDeclaredField("defaultBlock");
			GenerationSettings gen = (GenerationSettings) GenClass.newInstance();
			StickOfDebug.s = new String("Stage 1");
			field.setAccessible(true);
			StickOfDebug.s = new String("Stage 2");
			Field modifiers = Field.class.getDeclaredField("modifiers");
			StickOfDebug.s = new String("Stage 3");
			modifiers.setAccessible(true);
			StickOfDebug.s = new String("Stage 4");
			modifiers.setInt(field,  field.getModifiers() & ~Modifier.FINAL);
			StickOfDebug.s = new String("Stage 5");
			field.set(gen, Blocks.DIAMOND_BLOCK.getDefaultState());
			StickOfDebug.s = new String("Stage 6");
		}
		catch(Exception e)
		{
			StickOfDebug.s = new String(e.toString());
		}
	}
	
}
