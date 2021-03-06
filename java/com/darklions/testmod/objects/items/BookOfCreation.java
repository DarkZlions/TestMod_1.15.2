package com.darklions.testmod.objects.items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.Attributes;

import com.darklions.testmod.util.MathHelpers.CalcHelpers;
import com.darklions.testmod.util.MathHelpers.CalculateLine;
import com.darklions.testmod.util.MathHelpers.Vec3;
import com.darklions.testmod.util.helpers.CustomRayTraceResult;
import com.darklions.testmod.util.helpers.NBTHelper;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BookOfCreation extends Item
{
	private List<Vec3> vecs = new ArrayList<Vec3> ();
	
	public BookOfCreation(Item.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) 
	{
		
		if(entityLiving instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) entityLiving;
			
			CalcHelpers helper = new CalcHelpers();
			
			int range = getUseDuration(stack) - timeLeft;
			
			RayTraceResult result = CustomRayTraceResult.CustomrayTrace(worldIn, player, FluidMode.SOURCE_ONLY, helper.Clamp(range, 0, 30));
					
			BlockPos TargetPos = new BlockPos(result.getHitVec().x, result.getHitVec().y, result.getHitVec().z);
			BlockPos playerPos = player.getPosition().down();
			
			Vec3 p1 = new Vec3(TargetPos.getX(), TargetPos.getY(), TargetPos.getZ());
			Vec3 p2 = new Vec3(playerPos.getX(), playerPos.getY(), playerPos.getZ());
				
			CalculateLine calc = new CalculateLine(p1, p2);
				
			vecs = calc.CoordListVec3();
				
			for(Vec3 vec : vecs)
			{
				BlockPos pos = new BlockPos(vec.X(), vec.Y(), vec.Z());

				worldIn.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
				worldIn.playSound(player, playerPos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 2.0f, 0.0f);
				worldIn.addParticle(ParticleTypes.SMOKE, pos.getX(), pos.getY() + 1, pos.getZ(), 0, 0, 0);
			}	
			
			player.getCooldownTracker().setCooldown(this, 400);
		}
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
		if(entityIn instanceof PlayerEntity && isSelected)	
		{
			PlayerEntity player = (PlayerEntity) entityIn;
			player.abilities.allowFlying = true;
			player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200, 2));
		}
		else
		{
			PlayerEntity player = (PlayerEntity) entityIn;
			player.abilities.allowFlying = false;
			//player.setMotion(player.getMotion().x, player.getMotion().y - .01, player.getMotion().z);
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
	      ItemStack itemstack = playerIn.getHeldItem(handIn);
	      boolean flag = !playerIn.findAmmo(itemstack).isEmpty();

	      ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
	      if (ret != null) return ret;

	      if (playerIn.abilities.isCreativeMode) 
	      {
	         return ActionResult.resultFail(itemstack);
	      } 
	      else 
	      {
	         playerIn.setActiveHand(handIn);
	         return ActionResult.resultConsume(itemstack);
	      }
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) 
	{
		 return UseAction.BOW;
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) 
	{
		return new StringTextComponent("\u00A7l" + "Book Of Creation");
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) 
	{
		return true;
	}
	
	@Override
	public int getUseDuration(ItemStack stack) 
	{
		return 36000;
	}
}
