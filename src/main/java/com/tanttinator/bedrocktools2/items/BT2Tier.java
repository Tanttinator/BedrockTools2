package com.tanttinator.bedrocktools2.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class BT2Tier implements IItemTier {

    public BT2Tier(int maxUses, float efficiency, float attackDamage, int harvestLevel, int enchantability, Ingredient repairMaterial) {
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.harvestLevel = harvestLevel;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    int maxUses;
    float efficiency;
    float attackDamage;
    int harvestLevel;
    int enchantability;
    Ingredient repairMaterial;

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial;
    }
}
