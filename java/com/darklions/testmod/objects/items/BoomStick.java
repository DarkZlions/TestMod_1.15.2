package com.darklions.testmod.objects.items;

import com.darklions.testmod.util.helpers.CustomRayTraceResult;
import com.darklions.testmod.world.gen.CustomExplosion;
import com.darklions.testmod.world.gen.CustomWorld;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BoomStick extends Item
{
	private int size;
	
	public BoomStick(int size, Properties properties) 
	{
		super(properties);
		this.size = size;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);
		if(world.isRemote)
		{
			return ActionResult.resultPass(itemstack);
		}
		else
		{
			RayTraceResult result = CustomRayTraceResult.CustomrayTrace(world, player, FluidMode.NONE, 27d);
			
			CustomWorld customworld = (CustomWorld) world;
			
			customworld.createCustomExplosion(player, DamageSource.ON_FIRE, result.getHitVec().x, result.getHitVec().y, result.getHitVec().z, 10, true, Explosion.Mode.BREAK);
			
			player.getCooldownTracker()	.setCooldown(this, 60);
			
			return ActionResult.resultSuccess(itemstack);
		}
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) 
	{
		return 600;
	}
}
