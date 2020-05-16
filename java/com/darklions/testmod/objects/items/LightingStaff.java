package com.darklions.testmod.objects.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LightingStaff extends Item
{
	public LightingStaff(Properties properties) 
	{
		super(properties);
	}
	
	
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);
		if(world.isRemote)
		{
			return ActionResult.resultPass(itemstack);
		}
		else
		{
			
			List<Entity> entityList = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.getPosX() - 10, player.getPosY() - 10, player.getPosZ() - 10, player.getPosX() + 10, player.getPosY() + 10, player.getPosZ() + 10));
			if(entityList != null)
			{
				for(int i = 0; i < entityList.size(); ++i)
				{	
					Entity entity = entityList.get(i);
					
					if(entity != player)
					{	
						if(entity instanceof ItemEntity || entity instanceof TNTEntity || entity instanceof CodEntity)
						{
						}
						else
						{
							LightningBoltEntity lighting = new LightningBoltEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), false);
							ServerWorld serverworld = (ServerWorld) world;
							serverworld.addLightningBolt(lighting);
						    player.getCooldownTracker().setCooldown(this, 20);
						}
					}
				}
			}
			
			return ActionResult.resultSuccess(itemstack);
		}
	}
}
