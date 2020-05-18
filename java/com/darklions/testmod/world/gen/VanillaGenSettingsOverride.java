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

			Class<?> GenClass = Class.forName("net.minecraft.world.gen.GenerationSettings");
			Field defaultBlockField = GenClass.getDeclaredField("defaultBlock");
			Field villageDistanceField = GenClass.getDeclaredField("villageDistance");
			
			GenerationSettings gen = (GenerationSettings) GenClass.newInstance();

			defaultBlockField.setAccessible(true);
			villageDistanceField.setAccessible(true);

			Field modifiers = Field.class.getDeclaredField("modifiers");

			modifiers.setAccessible(true);

			modifiers.setInt(defaultBlockField,  defaultBlockField.getModifiers() & ~Modifier.FINAL);
			modifiers.setInt(villageDistanceField,  villageDistanceField.getModifiers() & ~Modifier.FINAL);

			defaultBlockField.set(gen, Blocks.DIAMOND_BLOCK.getDefaultState());
			villageDistanceField.setInt(gen, 1);
			StickOfDebug.s = new String(defaultBlockField.get(gen).toString());
		}
		catch(Exception e)
		{
			StickOfDebug.s = new String(e.toString());
		}
	}
	
}
