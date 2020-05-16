package com.darklions.testmod.objects.items;

import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class FireBallStaff extends Item
{
	private int power;
	private float speed = 1.2f;

	public FireBallStaff(int powerIn, Properties properties) 
	{
		super(properties);
		this.power = powerIn;
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
			FireballEntity entity = new FireballEntity(world, player.getPosX(), player.getPosY() + 2, player.getPosZ(), player.getLookVec().x * speed, player.getLookVec().y * speed, player.getLookVec().z * speed);
			entity.explosionPower = power;
			world.addEntity(entity);
			player.getCooldownTracker().setCooldown(this, 100);
			
			return ActionResult.resultSuccess(itemstack);
		}
	}
	
}
