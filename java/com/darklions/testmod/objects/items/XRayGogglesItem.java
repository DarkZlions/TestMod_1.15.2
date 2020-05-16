package com.darklions.testmod.objects.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.item.minecart.MinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class XRayGogglesItem extends ArmorItem
{
	private static int range = 100;
	
	public XRayGogglesItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) 
	{
		super(materialIn, slot, builder);
	}
	/*
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		List<Entity> entityList = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.getPosX() - range,
				player.getPosY() - range, player.getPosZ() - range, player.getPosX() + range, player.getPosY() + range, player.getPosZ() + range));
		
		for(int i = 0; i < entityList.size(); i++)
		{
			Entity entity = entityList.get(i);
			
			if(entity instanceof PlayerEntity || entity instanceof ItemEntity || entity instanceof ArrowEntity || entity instanceof TNTEntity || entity instanceof MinecartEntity)
			{
				//Do nothing
			}
			else
			{
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.GLOWING, 20, 1));
			}
		}
		
		super.onArmorTick(stack, world, player);
	}
	*/
}
