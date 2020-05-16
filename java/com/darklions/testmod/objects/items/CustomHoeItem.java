package com.darklions.testmod.objects.items;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.TieredItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CustomHoeItem extends TieredItem
{
	private final float speed;
	private final int placeHolder = 1;
	 protected static final Map<Block, BlockState> HOE_LOOKUP = Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.ALLIUM.getDefaultState(), Blocks.DIRT, Blocks.FARMLAND.getDefaultState(), Blocks.COARSE_DIRT, Blocks.DIRT.getDefaultState(), Blocks.STONE, Blocks.COBBLESTONE.getDefaultState(), Blocks.BEDROCK, Blocks.DIAMOND_ORE.getDefaultState()));
	 
	 public CustomHoeItem(IItemTier tier, float attackSpeedIn, Item.Properties builder)
	 {
		 super(tier, builder);
		 this.speed = attackSpeedIn;
	 }
	 
	 public ActionResultType onItemUse(ItemUseContext context)
	 {
		 World world = context.getWorld();
		 BlockPos blockpos = context.getPos();
		 int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(context);
		 if(hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
		 if( placeHolder == 1)
		 {
			 BlockState blockstate = HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
			 if(blockstate != null)
			 {
				 PlayerEntity playerentity = context.getPlayer();
				 world.playSound(playerentity, blockpos, SoundEvents.AMBIENT_CAVE, SoundCategory.BLOCKS, 1.0f, 1.0f);
				 if(!world.isRemote)
				 {
					 world.setBlockState(blockpos, blockstate, 11);
					 if(playerentity != null)
					 {
						 context.getItem().damageItem(1, playerentity, (p1) ->
						 {
							 p1.sendBreakAnimation(context.getHand());
						 });
					 }
				 }
			 	return ActionResultType.SUCCESS;
			 	}		 
		 }
	 return ActionResultType.PASS;
	 }
	 
	 public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
	 {
		 stack.damageItem(1, attacker, (p2) -> {
			 p2.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		 });
		 return true;
	 }
	 
	   public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
		      Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
		      if (equipmentSlot == EquipmentSlotType.MAINHAND) {
		         multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0.0D, AttributeModifier.Operation.ADDITION));
		         multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)this.speed, AttributeModifier.Operation.ADDITION));
		      }

		      return multimap;
		   }
	 
	 
}
