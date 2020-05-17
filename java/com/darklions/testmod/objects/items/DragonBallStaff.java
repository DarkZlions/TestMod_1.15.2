package com.darklions.testmod.objects.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class DragonBallStaff extends Item
{
	public DragonBallStaff(Properties properties) 
	{
		super(properties);
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
			player.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 10, 0);
			DragonFireballEntity entity = new DragonFireballEntity(world, player.getPosX(), player.getPosY() + 1.5, player.getPosZ(), player.getLookVec().x, player.getLookVec().y, player.getLookVec().z);
			world.addEntity(entity);
			player.getCooldownTracker().setCooldown(this, 100);
			
			return ActionResult.resultSuccess(itemstack);
		}
	}

}
