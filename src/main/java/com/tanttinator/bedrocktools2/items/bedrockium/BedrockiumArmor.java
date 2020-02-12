package com.tanttinator.bedrocktools2.items.bedrockium;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import javax.annotation.Nonnull;

import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.items.BT2Items;
import com.tanttinator.bedrocktools2.items.IUpgradeable;

public class BedrockiumArmor extends ItemArmor implements IUpgradeable {

    public BedrockiumArmor(EntityEquipmentSlot slot) {
        super(BT2Items.BEDROCKIUM_ARMOR_MATERIAL, 3, slot);
    }

    /*@Override
    public String getArmorTexture(@Nonnull ItemStack stack, Entity entity, EquipmentSlotType slot, String layer) {

        String layerId = "1";
        if (slot == EquipmentSlotType.LEGS) {
            layerId = "2";
        }
        return "bedrocktools2:textures/armor/bedrockium_layer_" + layerId + ".png";
    }*/

    /*@Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        for (BedrockTools2.Element e : IUpgradeable.getUpgrades(stack)) {
            tooltip.add(new StringTextComponent(e.name).applyTextStyle(e.color));
        }
    }*/

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
