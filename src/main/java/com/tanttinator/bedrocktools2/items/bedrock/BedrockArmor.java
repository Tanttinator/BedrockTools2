package com.tanttinator.bedrocktools2.items.bedrock;

import com.tanttinator.bedrocktools2.items.BT2Items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class BedrockArmor extends ItemArmor {
    public BedrockArmor(EntityEquipmentSlot slot) {
        super(BT2Items.BEDROCK_ARMOR_MATERIAL, 3, slot);
    }
}
