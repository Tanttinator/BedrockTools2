package com.tanttinator.bedrocktools2.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

public class BT2ArmorTier implements IArmorMaterial {

    int durability;
    int[] damageReductionAmounts;
    int enchantability;
    SoundEvent sound;
    Ingredient repairMaterial;
    String name;
    float toughness;

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};

    public BT2ArmorTier(int durability, int[] damageReductionAmounts, int enchantability, SoundEvent sound, Ingredient repairMaterial, String name, float toughness) {
        this.durability = durability;
        this.damageReductionAmounts = damageReductionAmounts;
        this.enchantability = enchantability;
        this.sound = sound;
        this.repairMaterial = repairMaterial;
        this.name = name;
        this.toughness = toughness;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * durability;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return damageReductionAmounts[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return sound;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }
}
