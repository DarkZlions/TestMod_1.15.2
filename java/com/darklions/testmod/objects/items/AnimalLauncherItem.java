package com.darklions.testmod.objects.items;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class AnimalLauncherItem extends Item
{
	float power = 1.5f;
	public AnimalLauncherItem(Item.Properties properties)
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
			ChickenEntity entity = new ChickenEntity(EntityType.CHICKEN, world);
			entity.addVelocity(player.getLookVec().x * power, player.getLookVec().y * power, player.getLookVec().z * power);
			entity.setPosition(player.getPosX(), player.getPosY() + 2, player.getPosZ());
			world.addEntity(entity);
			player.getCooldownTracker().setCooldown(this, 0);
			
			return ActionResult.resultSuccess(itemstack);
		}
	}
}
