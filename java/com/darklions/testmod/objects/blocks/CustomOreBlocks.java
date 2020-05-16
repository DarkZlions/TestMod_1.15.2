package com.darklions.testmod.objects.blocks;

import java.util.Random;

import com.darklions.testmod.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CustomOreBlocks extends Block
{
	public CustomOreBlocks(Block.Properties properties)
	{
		super(properties);
	}
	
	protected int getExperience(Random rand)
	{
		if(this == BlockInit.ruby_ore)
		{
			return MathHelper.nextInt(rand, 1, 3);
		}
		else if(this == BlockInit.end_ruby_ore)
		{
			return MathHelper.nextInt(rand, 1,  3);
		}
		else if(this == BlockInit.nether_ruby_ore)
		{
			return MathHelper.nextInt(rand, 1, 3);
		}
		else
		{
			return 0;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void spawnAdditionalDrops(BlockState state, World world, BlockPos pos, ItemStack stack)
	{
		super.spawnAdditionalDrops(state, world, pos, stack);
	}
	
	@Override
	public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch)
	{
		return silktouch == 0 ? this.getExperience(RANDOM) : 0;
	}
}
