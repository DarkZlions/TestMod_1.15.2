package com.darklions.testmod.events;

import com.darklions.testmod.TestMod;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Bus.FORGE)
public class TestEvent 
{

	@SubscribeEvent
	public static void TestDeathEvent(LivingDeathEvent event)
	{
		LivingEntity livingEntity = event.getEntityLiving();
		World world = livingEntity.getEntityWorld();
		if(livingEntity instanceof CodEntity)
		{
			world.createExplosion(livingEntity, livingEntity.getPosX(), livingEntity.getPosY(), livingEntity.getPosZ(), 10, Explosion.Mode.BREAK);
		}
	}
	
	@SubscribeEvent
	public static void GoalChangeEvent(EntityJoinWorldEvent event)
	{
		MobEntity entity;
		if(event.getEntity() instanceof MobEntity)
		{
			entity = (MobEntity) event.getEntity();
			//World world = entity.getEntityWorld();
			entity.targetSelector.addGoal(-1, new NearestAttackableTargetGoal<>(entity, MobEntity.class, false, true));

			if(entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
			{
				try
				{
					entity.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(entity.getMaxHealth() / 3);
					entity.targetSelector.addGoal(-1, new MeleeAttackGoal((CreatureEntity)entity, entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() * 5, false));	
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void ProjecttileImpactEvent(ProjectileImpactEvent event)
	{
		Entity entity = event.getEntity();
		World world = entity.getEntityWorld();
		
		if(entity instanceof AbstractArrowEntity)
		{
			world.createExplosion(entity, DamageSource.causeExplosionDamage(new Explosion(world, entity, 0, 0, 0, 0, false, null)), entity.getPosX(), entity.getPosY(), entity.getPosZ(), 5, true, Explosion.Mode.BREAK);
			entity.remove();
		}
	}
	/*
	@SubscribeEvent
	public static void PlayerFloatEvent(PlayerTickEvent event)
	{
		
	}
	*/
}