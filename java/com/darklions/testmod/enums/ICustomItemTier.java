package com.darklions.testmod.enums;

import java.util.function.Supplier;

import com.darklions.testmod.init.ItemInit;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum ICustomItemTier implements IItemTier
{
	TESTTIER(4.0f, 5.0f, 10, 2, 230, () -> {
		return Ingredient.fromItems(ItemInit.test_item);
	});
	
	private final float attackDamage, efficiency;
	private final int enchantability, harvestLevel, durability;
	private final LazyValue<Ingredient> repairItem;
	
	ICustomItemTier(float attackDamage, float efficiency, int enchantability, int harvestLevel, int durability, Supplier<Ingredient> repairItem)
	{
		this.attackDamage = attackDamage;
		this.efficiency = efficiency;
		this.enchantability = enchantability;
		this.harvestLevel = harvestLevel;
		this.durability = durability;
		this.repairItem = new LazyValue<>(repairItem);
	}
	
	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getMaxUses() {
		return this.durability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return this.repairItem.getValue();
	}

}
