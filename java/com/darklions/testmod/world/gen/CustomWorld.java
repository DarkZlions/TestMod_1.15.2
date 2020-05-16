package com.darklions.testmod.world.gen;

import java.util.function.BiFunction;

import net.minecraft.entity.Entity;
import net.minecraft.profiler.IProfiler;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.Explosion.Mode;
import net.minecraft.world.chunk.AbstractChunkProvider;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.storage.WorldInfo;

public abstract class CustomWorld extends World
{

	protected CustomWorld(WorldInfo info, DimensionType dimType,
			BiFunction<World, Dimension, AbstractChunkProvider> provider, IProfiler profilerIn, boolean remote) 
	{
		super(info, dimType, provider, profilerIn, remote);
		// TODO Auto-generated constructor stub
	}
	
	public Explosion createCustomExplosion(Entity entityIn, DamageSource damageSourceIn, double xIn, double yIn, double zIn, float explosionRadius, boolean causesFire, Mode modeIn) 
	{
	      Explosion explosion = new CustomExplosion(this, entityIn, xIn, yIn, zIn, explosionRadius, causesFire, modeIn);
	      if (damageSourceIn != null) 
	      {
	         explosion.setDamageSource(damageSourceIn);
	      }
	      if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(this, explosion)) return explosion;

	      explosion.doExplosionA();
	      explosion.doExplosionB(true);
	      return explosion;
	}

}
