package com.tanttinator.bedrocktools2.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class BedrockArmor extends ArmorItem {
    public BedrockArmor(EquipmentSlotType slot) {
        super(BT2Items.BEDROCK_ARMOR_TIER, slot, new BT2Properties());
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, Entity entity, EquipmentSlotType slot, String layer) {

        String layerId = "1";
        if(slot == EquipmentSlotType.LEGS){
            layerId = "2";
        }
        return "bedrocktools2:textures/armor/bedrock_layer_" + layerId + ".png";
    }
}
