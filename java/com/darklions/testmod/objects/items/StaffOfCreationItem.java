package com.darklions.testmod.objects.items;

import java.util.List;

import com.darklions.testmod.util.MathHelpers.CalcCoordinates;
import com.darklions.testmod.util.MathHelpers.Vec3;
import com.darklions.testmod.util.helpers.CustomRayTraceResult;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

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

		if(worldIn.isRemote)
		{
			return ActionResult.resultPass(itemStack);
		}
		else
		{
			RayTraceResult result = CustomRayTraceResult.CustomrayTrace(worldIn, playerIn, FluidMode.SOURCE_ONLY, 10D);
			
			BlockPos TargetPos = new BlockPos(result.getHitVec().x, result.getHitVec().y, result.getHitVec().z);
			BlockPos playerPos = playerIn.getPosition();
			
			Vec3 p1 = new Vec3(TargetPos.getX(), TargetPos.getY(), TargetPos.getZ());
			Vec3 p2 = new Vec3(playerPos.getX(), playerPos.getY(), playerPos.getZ());
			
			CalcCoordinates calc = new CalcCoordinates(p1, p2);
			
			List<Vec3> vecs = calc.CoordListVec3();
			
			playerIn.sendMessage((new StringTextComponent(playerPos.toString())));
			playerIn.sendMessage((new StringTextComponent(TargetPos.toString())));
			
			//worldIn.setBlockState(TargetPos, Blocks.STONE.getDefaultState());
			
			for(Vec3 vec : vecs)
			{
				worldIn.setBlockState(new BlockPos(vec.X(), vec.Y(), vec.Z()), Blocks.STONE.getDefaultState());
				System.out.println(vec.X());
				System.out.println(vec.Z());
			}
			
			return ActionResult.resultSuccess(itemStack);
		}
	}
}
