package com.darklions.testmod.world.gen;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.darklions.testmod.objects.items.StickOfDebug;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class VanillaGenSettingsOverride 
{
	public static void overrideSettings()
	{
		
		try 
		{
			Class<?> GenClass = GenerationSettings.class;
			
			Field defaultBlockField = ObfuscationReflectionHelper.findField(GenClass, "defaultBlock");
			
			GenerationSettings gen = (GenerationSettings) GenClass.newInstance();
			
			Field modifiers = Field.class.getDeclaredField("modifiers");
			
			modifiers.setAccessible(true);
			modifiers.setInt(defaultBlockField, defaultBlockField.getModifiers() & ~Modifier.FINAL);
			
			defaultBlockField.set(gen, Blocks.DIAMOND_BLOCK.getDefaultState());
			
			StickOfDebug.s = new String(defaultBlockField.get(gen).toString());
		}
		catch(Exception e)
		{
			StickOfDebug.s = new String(e.toString());
		}
		
	}

	
}
