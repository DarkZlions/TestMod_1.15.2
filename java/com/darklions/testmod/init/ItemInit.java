package com.darklions.testmod.init;

import com.darklions.testmod.TestMod;
import com.darklions.testmod.TestMod.TestItemGroup;
import com.darklions.testmod.enums.ICustomArmorTier;
import com.darklions.testmod.enums.ICustomItemTier;
import com.darklions.testmod.objects.items.AnimalLauncherItem;
import com.darklions.testmod.objects.items.BoomStick;
import com.darklions.testmod.objects.items.CustomArmorItem;
import com.darklions.testmod.objects.items.CustomHoeItem;
import com.darklions.testmod.objects.items.CustomWandItem;
import com.darklions.testmod.objects.items.DragonBallStaff;
import com.darklions.testmod.objects.items.FireBallStaff;
import com.darklions.testmod.objects.items.LightingStaff;
import com.darklions.testmod.objects.items.StickOfDebug;
import com.darklions.testmod.objects.items.XRayGogglesItem;

import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Food;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item.Properties;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Bus.MOD)
@ObjectHolder(TestMod.MODID)
public class ItemInit 
{	
	public static final Item test_item = new Item(new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.UNCOMMON));
	public static final Item item_test = new Item(new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.COMMON).food(new Food.Builder().hunger(10).saturation(10.0f).effect(new EffectInstance(Effects.GLOWING, 6000, 5), 1.0f).build()));
	public static final Item item_item = new Item(new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.COMMON).maxStackSize(0));
	public static final Item ruby = new Item(new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.RARE));
	
	//Tools
	public static final Item test_sword = new SwordItem(ICustomItemTier.TESTTIER, 4, 8, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.RARE));
	public static final Item test_pickaxe = new PickaxeItem(ICustomItemTier.TESTTIER, 1, 2, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.RARE));
	public static final Item test_axe = new AxeItem(ICustomItemTier.TESTTIER, 5, 5, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.RARE));
	public static final Item test_hoe = new CustomHoeItem(ICustomItemTier.TESTTIER, 0, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.RARE));
	public static final Item test_shovel = new ShovelItem(ICustomItemTier.TESTTIER, 1, 5, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.RARE));
	public static final Item strange_hoe = new CustomHoeItem(ItemTier.WOOD, 0, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.COMMON));
	public static final Item test_wand = new CustomWandItem(EntityType.TNT, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.UNCOMMON));
	public static final Item boom_stick = new BoomStick(10, new Item.Properties().group(TestItemGroup.instance));
	public static final Item fireballwand = new FireBallStaff(10, new Item.Properties().group(TestItemGroup.instance));
	public static final Item fish_stick = new CustomWandItem(EntityType.COD, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.UNCOMMON));
	public static final Item lighting_wand = new LightingStaff(new Item.Properties().group(TestItemGroup.instance));
	public static final Item debug_le_stick = new StickOfDebug(new Item.Properties().group(TestItemGroup.instance));
	public static final Item chickenlauncher = new AnimalLauncherItem(new Item.Properties().group(TestItemGroup.instance));
	public static final Item dragonfireballwand = new DragonBallStaff(0, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.EPIC));
	
	//Armour
	public static final Item ruby_helmet = new CustomArmorItem(ICustomArmorTier.RUBY, EquipmentSlotType.HEAD, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.RARE));
	public static final Item ruby_chestplate = new CustomArmorItem(ICustomArmorTier.RUBY, EquipmentSlotType.CHEST, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.RARE));
	public static final Item ruby_leggins = new CustomArmorItem(ICustomArmorTier.RUBY, EquipmentSlotType.LEGS, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.RARE));
	public static final Item ruby_boots = new CustomArmorItem(ICustomArmorTier.RUBY, EquipmentSlotType.FEET, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.RARE));
	public static final Item xray_googles = new XRayGogglesItem(ICustomArmorTier.RUBY, EquipmentSlotType.HEAD, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.UNCOMMON));
	//Items
	
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
				test_item.setRegistryName(TestMod.location("test_item")),
				item_test.setRegistryName(TestMod.location("item_test")),
				item_item.setRegistryName(TestMod.location("item_item")),
				test_sword.setRegistryName(TestMod.location("test_sword")),
				test_pickaxe.setRegistryName(TestMod.location("test_pickaxe")),
				test_axe.setRegistryName(TestMod.location("test_axe")),
				test_hoe.setRegistryName(TestMod.location("test_hoe")),
				test_shovel.setRegistryName(TestMod.location("test_shovel")),
				strange_hoe.setRegistryName(TestMod.location("strange_hoe")),
				test_wand.setRegistryName(TestMod.location("test_wand")),
				fish_stick.setRegistryName(TestMod.location("fish_stick")),
				ruby.setRegistryName(TestMod.location("ruby")),
				ruby_helmet.setRegistryName(TestMod.location("ruby_helmet")),
				ruby_leggins.setRegistryName(TestMod.location("ruby_leggins")),
				ruby_boots.setRegistryName(TestMod.location("ruby_boots")),
				ruby_chestplate.setRegistryName(TestMod.location("ruby_chestplate")),
				xray_googles.setRegistryName(TestMod.location("xray_goggles")),
				boom_stick.setRegistryName(TestMod.location("boom_stick")),
				fireballwand.setRegistryName(TestMod.location("fireballwand")),
				lighting_wand.setRegistryName(TestMod.location("lighting_wand")),
				debug_le_stick.setRegistryName(TestMod.location("debug_le_stick")),
				dragonfireballwand.setRegistryName(TestMod.location("dragonfireballwand")),
				chickenlauncher.setRegistryName(TestMod.location("chickenlauncher"))
		);
		
		TestMod.LOGGER.info("Items Registered");
	}
}
