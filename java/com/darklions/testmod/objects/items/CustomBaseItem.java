package com.darklions.testmod.objects.items;

import java.util.List;
/*
 * 
 * Easier to add Custom names and custom tooltips
 * 
 */
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class CustomBaseItem extends Item
{
	private String itemInformation;
	private String itemName;
	
	public CustomBaseItem(String itemName, String itemInformation, Properties properties) 
	{
		super(properties);
		
		this.itemInformation = itemInformation;
		this.itemName = itemName;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(new StringTextComponent(itemInformation));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) 
	{
		return new StringTextComponent(itemName);
	}

}
