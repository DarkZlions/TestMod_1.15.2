package com.darklions.testmod.objects.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class StickOfDebug extends Item
{

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
			return ActionResult.resultSuccess(itemstack);
		}
	}

}
