package com.darklions.testmod.objects.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class StaffOfCreationItem extends Item
{
	private static int cubeSize = 3;
	private static BlockState Block = Blocks.STONE.getDefaultState();
	
	public StaffOfCreationItem(Item.Properties properties)
	{
		super(properties);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		ItemStack itemStack = playerIn.getHeldItem(handIn);
		
		double x = playerIn.getPosX();
		double y = playerIn.getPosY();
		double z = playerIn.getPosZ();
		
		if(worldIn.isRemote)
		{
			return ActionResult.resultPass(itemStack);
		}
		else
		{
			
			for( int i = 0; i <= cubeSize; i++)
			{
				for(int j = 0; j <= cubeSize; j++)
				{
					for(int k = 0; k <= cubeSize; k++)
					{
						worldIn.setBlockState(new BlockPos(x + i, y + k, z + j), Blocks.GRINDSTONE.getDefaultState());
						playerIn.sendMessage(new StringTextComponent(new String().valueOf(i) + "x"));
						playerIn.sendMessage(new StringTextComponent(new String().valueOf(j) + "y"));
						playerIn.sendMessage(new StringTextComponent(new String().valueOf(k) + "z"));
					}
				}
			}
			
			return ActionResult.resultSuccess(itemStack);
		}
	}
}
