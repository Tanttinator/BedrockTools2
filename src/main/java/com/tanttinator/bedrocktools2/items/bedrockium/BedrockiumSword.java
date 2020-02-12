package com.tanttinator.bedrocktools2.items.bedrockium;

import java.util.List;

import com.google.common.collect.Multimap;
import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.items.BT2Items;
import com.tanttinator.bedrocktools2.items.IUpgradeable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class BedrockiumSword extends ItemSword implements IUpgradeable {

    public BedrockiumSword() {
        super(BT2Items.BEDROCKIUM_TOOL_MATERIAL);
    }
    
    /*@Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        for (BedrockTools2.Element e : IUpgradeable.getUpgrades(stack)) {
            tooltip.add(new StringTextComponent(e.name).applyTextStyle(e.color));
        }
    }*/

    @Override
    public void addRune(ItemStack stack, Element rune) {
        IUpgradeable.super.addRune(stack, rune);
        if(rune == Element.EARTH) {
            stack.addEnchantment(Enchantment.getEnchantmentByID(34), 5);
        }
        if(rune == Element.FIRE) {
            stack.addEnchantment(Enchantment.getEnchantmentByID(20), 1);
        }
        if(rune == Element.AIR) {
            stack.addEnchantment(Enchantment.getEnchantmentByID(16), 5);
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);
        if(slot == EntityEquipmentSlot.MAINHAND && IUpgradeable.HasRune(stack, Element.AIR)) {
            multimap.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 1.0F, 0));
        }
        return multimap;
    }
}