package com.tanttinator.bedrocktools2.items.bedrockium;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.items.BT2Items;
import com.tanttinator.bedrocktools2.items.IUpgradeable;

public class BedrockiumArmor extends ItemArmor implements IUpgradeable {

    public BedrockiumArmor(EntityEquipmentSlot slot) {
        super(BT2Items.BEDROCKIUM_ARMOR_MATERIAL, 3, slot);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        for (BedrockTools2.Element e : IUpgradeable.getUpgrades(stack)) {
            tooltip.add(e.name);
        }
    }

    @Override
    public void addRune(ItemStack stack, Element rune) {
        IUpgradeable.super.addRune(stack, rune);
        switch(getEquipmentSlot()) {
            case HEAD:
                switch(rune) {
                    case AIR: break;
                    case EARTH: stack.addEnchantment(Enchantment.getEnchantmentByID(0), 5); break;
                    case FIRE: break;
                    case WATER: stack.addEnchantment(Enchantment.getEnchantmentByID(5), 5); break;
                }
            break;
            case CHEST: 
                switch(rune) {
                    case AIR: break;
                    case EARTH: break;
                    case FIRE: break;
                    case WATER: stack.addEnchantment(Enchantment.getEnchantmentByID(3), 5); break;
                }
            break;
            case LEGS:
                switch(rune) {
                    case AIR: break;
                    case EARTH: stack.addEnchantment(Enchantment.getEnchantmentByID(0), 5); break;
                    case FIRE: stack.addEnchantment(Enchantment.getEnchantmentByID(1), 5); break;
                    case WATER: stack.addEnchantment(Enchantment.getEnchantmentByID(8), 5); break;
                }
            break;
            case FEET:
                switch(rune) {
                    case AIR: break;
                    case EARTH: stack.addEnchantment(Enchantment.getEnchantmentByID(0), 5); break;
                    case FIRE: stack.addEnchantment(Enchantment.getEnchantmentByID(1), 5); break;
                    case WATER: stack.addEnchantment(Enchantment.getEnchantmentByID(9), 1); break;
                }
            break;
            case MAINHAND: break;
            case OFFHAND: break;
        }
    }
}
