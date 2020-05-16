package com.darklions.testmod;
import com.darklions.testmod.TestMod.TestItemGroup;
import com.darklions.testmod.enums.ICustomItemTier;
import com.darklions.testmod.objects.items.AnimalLauncherItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Bus.FORGE)
@ObjectHolder("minecraft")
public class Overrides 
{
	
	public static final Item diamond = register("diamond", new Item(new Item.Properties().group(ItemGroup.REDSTONE).rarity(Rarity.RARE)));
	public static final Item stick = register("stick", new AnimalLauncherItem(new Item.Properties().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)));
	public static final Item DIAMOND_SWORD = register("diamond_sword", new SwordItem(ICustomItemTier.TESTTIER, 3, -2.4F, (new Item.Properties()).group(TestItemGroup.instance).rarity(Rarity.EPIC)));
	
	private static Item register(String key, Item itemIn)
	{
		return register(new ResourceLocation(key), itemIn);
	}

	private static Item register(ResourceLocation key, Item itemIn) 
	{
		if (itemIn instanceof BlockItem) 
		{
			((BlockItem)itemIn).addToBlockToItemMap(Item.BLOCK_TO_ITEM, itemIn);
		}

		return Registry.register(Registry.ITEM, key, itemIn);
	}
}
