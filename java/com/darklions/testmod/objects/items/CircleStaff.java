package com.darklions.testmod.objects.items;

import java.util.List;

import com.darklions.testmod.util.MathHelpers.CircleCalculator;
import com.darklions.testmod.util.MathHelpers.Vec2;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class CircleStaff extends Item
{
	public CircleStaff(Item.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack stack = playerIn.getHeldItem(handIn);
		
		if(!worldIn.isRemote)
		{
			return ActionResult.resultPass(stack);
		}
		else
		{
			CircleCalculator calc = new CircleCalculator(5, new Vec2(playerIn.getPosX(), playerIn.getPosZ()));
			
			List<Vec2> vecs = calc.CircleCoords();
			
			for(Vec2 vec : vecs)
			{
				worldIn.setBlockState(new BlockPos(vec.X(), playerIn.getPosition().getY(), vec.Y()), Blocks.STONE.getDefaultState());
				playerIn.sendMessage(new StringTextComponent(new BlockPos(vec.X(), playerIn.getPosition().getY(), vec.Y()).toString()));
			}
			
			return ActionResult.resultSuccess(stack);
		}
	}
}
