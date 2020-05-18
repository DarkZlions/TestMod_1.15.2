package com.darklions.testmod.objects.items;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.gen.GenerationSettings;

public class StickOfDebug extends Item
{
	public static String s = "";
	public StickOfDebug(Properties properties) 
	{
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if(worldIn.isRemote)
		{
			return ActionResult.resultPass(itemstack);
		}
		else
		{

			playerIn.sendMessage(new StringTextComponent(s));
			try 
			{
				Class<?> clazz = Class.forName("net.minecraft.world.biome.provider.OverworldBiomeProvider");
				OverworldBiomeProvider instance = (OverworldBiomeProvider) clazz.newInstance();
				Field field = clazz.getDeclaredField("field_226847_e_");
				field.setAccessible(true);
				playerIn.sendMessage(new StringTextComponent(field.get(instance).toString()));
			} 
			catch (Exception e) 
			{
				playerIn.sendMessage(new StringTextComponent(e.toString()));
			}
			return ActionResult.resultSuccess(itemstack);
		}
	}

}
