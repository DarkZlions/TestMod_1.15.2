package com.darklions.testmod.init;

import com.darklions.testmod.TestMod;
import com.darklions.testmod.TestMod.TestItemGroup;
import com.darklions.testmod.objects.blocks.CustomOreBlocks;
import com.darklions.testmod.objects.blocks.EyeOfVisionBlock;
import com.darklions.testmod.objects.blocks.QuarryBlock;

import net.minecraft.block.Block;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TestMod.MODID, bus = Bus.MOD)
@ObjectHolder(TestMod.MODID)
public class BlockInit 
{
	/*
	 * Block
	 * Blocks
	 */
	public static final Block test_block = new Block(Block.Properties.create(Material.GLASS).sound(SoundType.SLIME).jumpFactor(12).hardnessAndResistance(0.7f, 99.0f).harvestTool(ToolType.SHOVEL).harvestLevel(1).variableOpacity().notSolid());
	public static final Block test_glass = new GlassBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid());
	public static final Block ruby_ore = new CustomOreBlocks(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F));
	public static final Block end_ruby_ore = new CustomOreBlocks(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(5.0f, 6.0f).harvestTool(ToolType.PICKAXE).harvestLevel(3));
	public static final Block eye_of_the_vision = new EyeOfVisionBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(4.0f, 4.0f).harvestLevel(3).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).lightValue(2));
	public static final Block nether_ruby_ore = new CustomOreBlocks(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(5.0f, 6.0f).harvestTool(ToolType.PICKAXE).harvestLevel(3));
	public static final Block tileentity_quarry = new QuarryBlock(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1.0f));
	/*
	 * BlockItem
	 */
	public static final Item test_block_item = new BlockItem(test_block, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.EPIC));
	public static final Item test_glass_item = new BlockItem(test_glass, new Item.Properties().group(TestItemGroup.instance).food(new Food.Builder().hunger(10).effect(new EffectInstance(Effects.SLOWNESS, 120, 1), 1.0f).build()));
	public static final Item ruby_ore_item = new BlockItem(ruby_ore, new Item.Properties().group(TestItemGroup.instance));
	public static final Item end_ruby_ore_item = new BlockItem(end_ruby_ore, new Item.Properties().group(TestItemGroup.instance));
	public static final Item nether_ruby_ore_item = new BlockItem(nether_ruby_ore, new Item.Properties().group(TestItemGroup.instance));
	public static final Item eye_of_the_vision_item = new BlockItem(eye_of_the_vision, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.EPIC));
	public static final Item tileentity_quarry_item = new BlockItem(tileentity_quarry, new Item.Properties().group(TestItemGroup.instance).rarity(Rarity.RARE));
	
	
	@SubscribeEvent
	public static void onBlockRegister(final RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll
		(
			test_block.setRegistryName(TestMod.location("test_block")),
			test_glass.setRegistryName(TestMod.location("test_glass")),
			ruby_ore.setRegistryName(TestMod.location("ruby_ore")),
			end_ruby_ore.setRegistryName(TestMod.location("end_ruby_ore")),
			nether_ruby_ore.setRegistryName(TestMod.location("nether_ruby_ore")),
			eye_of_the_vision.setRegistryName(TestMod.location("eye_of_the_vision")),
			tileentity_quarry.setRegistryName(TestMod.location("quarry"))
		);
		
		TestMod.LOGGER.info("Blocks registered");
	}
	
	@SubscribeEvent
	public static void onItemBlockRegister(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
			test_block_item.setRegistryName(TestMod.location("test_block")),
			test_glass_item.setRegistryName(TestMod.location("test_glass")),
			ruby_ore_item.setRegistryName(TestMod.location("ruby_ore")),
			end_ruby_ore_item.setRegistryName(TestMod.location("end_ruby_ore")),
			nether_ruby_ore_item.setRegistryName(TestMod.location("nether_ruby_ore")),
			eye_of_the_vision_item.setRegistryName(TestMod.location("eye_of_the_vision")),
			tileentity_quarry_item.setRegistryName(TestMod.location("quarry"))
		);
		
		TestMod.LOGGER.info("ItemBlock registered");
	}
}
