package com.darklions.testmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.darklions.testmod.init.BlockInit;
import com.darklions.testmod.init.TileEntityInit;
import com.darklions.testmod.objects.items.StickOfDebug;
import com.darklions.testmod.world.gen.VanillaGenSettingsOverride;
import com.darklions.testmod.world.gen.carvers.CustomWorldCarverGen;
import com.darklions.testmod.world.gen.ore.CustomOreGen;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("testmod")
@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Bus.MOD)
public class TestMod 
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "testmod";
	public static TestMod INSTANCE;
	
	public TestMod()
	{
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::clientSetup);
		
		TileEntityInit.TILE_ENTITY_TYPES.register(modEventBus);
		
		INSTANCE = this;
		MinecraftForge.EVENT_BUS.register(this);
	}
	@SubscribeEvent
	public void setup(final FMLCommonSetupEvent event)
	{

	}
	
	private void clientSetup(final FMLClientSetupEvent event)
	{
	}
	
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event)
	{
		
	}
	
	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event)
	{
		CustomOreGen.setupOverWorldOreGeneration();
		CustomOreGen.setupEndOreGeneration();
		CustomOreGen.setupNetherOreGeneration();
		CustomWorldCarverGen.setupEndCarverGeneration();
		VanillaGenSettingsOverride.overrideSettings();
	}
	
	public static class TestItemGroup extends ItemGroup
	{
		public static final TestItemGroup instance = new TestItemGroup(ItemGroup.GROUPS.length, "testtab");
		
		private TestItemGroup(int index, String label)
		{
			super(index, label);
		}
		
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(BlockInit.test_block);
		}
	}
	
	public static ResourceLocation location(String name)
	{
		return new ResourceLocation(MODID, name);
	}
}
