package com.darklions.testmod.init;

import com.darklions.testmod.TestMod;
import com.darklions.testmod.tileentity.QuarryTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit 
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, TestMod.MODID);
	
	public static final RegistryObject<TileEntityType<QuarryTileEntity>> QUARRY = TILE_ENTITY_TYPES.register("quarry", () -> TileEntityType.Builder.create(QuarryTileEntity::new, BlockInit.tileentity_quarry).build(null));
}
