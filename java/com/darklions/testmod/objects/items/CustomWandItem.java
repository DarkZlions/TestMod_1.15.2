package com.darklions.testmod.objects.items;

import java.util.List;

import com.darklions.testmod.util.helpers.KeyBoardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class CustomWandItem extends Item
{
	int durability;
	static double range = 27d;
	private EntityType<?> entityType;
	private static String tag = "9/11/2001";
	
	public CustomWandItem(EntityType<?> entityType, Properties properties) 
	{
		super(properties);
		this.entityType = entityType;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);
		if(world.isRemote)
		{
			return ActionResult.resultPass(itemstack);
		}
		else
		{
			
			List<Entity> entityList = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(player.getPosX() - 10, player.getPosY() - 10, player.getPosZ() - 10, player.getPosX() + 10, player.getPosY() + 10, player.getPosZ() + 10));
			if(entityList != null)
			{
				for(int i = 0; i < entityList.size(); ++i)
				{	
					Entity entity = entityList.get(i);
					
					if(entity != player && tag != entity.getTags().toString())
					{	
						if(entity instanceof ItemEntity || entity instanceof TNTEntity || entity instanceof CodEntity)
						{
						}
						else
						{
							entityType.spawn(world, itemstack, player, entity.getPosition().up(), SpawnReason.EVENT, true, false);
						    player.getCooldownTracker().setCooldown(this, 20);
						}
					}
				}
			}
			
			return ActionResult.resultSuccess(itemstack);
		}
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) 
	{
		return true;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		if(KeyBoardHelper.isHoldingShift())
		{
			tooltip.add(new StringTextComponent("\u00A7l" + "Right for use the wand"));
		}
		else
		{
			tooltip.add(new StringTextComponent("\u00A7o" + "Hold shift for more information"));
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}


