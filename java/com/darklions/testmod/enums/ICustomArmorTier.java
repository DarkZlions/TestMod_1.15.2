package com.darklions.testmod.enums;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.darklions.testmod.init.ItemInit;
import com.darklions.testmod.TestMod;

public enum ICustomArmorTier implements IArmorMaterial
{
	   RUBY("ruby", 27, new int[]{2, 5, 7, 2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.7F, () -> {
		      return Ingredient.fromItems(ItemInit.ruby);}),
	   	XRAY("xray", 13, new int[]{1, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, () -> {
		      return Ingredient.fromItems(Items.DIAMOND);});
	
	final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	String name;
	int maxDamageFactor;
	int[] damageReductionAmountArray;
	int enchantability;
	SoundEvent soundEvent;
	float toughness;
	LazyValue<Ingredient> repairMaterial;
	
	ICustomArmorTier(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial)
	{
		this.name = locationName(name);
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReductionAmountArray;
		this.enchantability = enchantability;
		this.soundEvent = soundEvent;
		this.toughness = toughness;
		this.repairMaterial = new LazyValue<>(repairMaterial);
	}

	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		return this.MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getSoundEvent() {
		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}
	
	private static String locationName(String name)
	{
		return TestMod.MODID + ":" + name;
	}
}
