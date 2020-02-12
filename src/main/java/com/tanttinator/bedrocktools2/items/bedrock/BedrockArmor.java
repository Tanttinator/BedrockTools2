package com.tanttinator.bedrocktools2.items.bedrock;

import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.items.BT2Items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

public class BedrockArmor extends ItemArmor {
    public BedrockArmor(EntityEquipmentSlot slot) {
        super(BT2Items.BEDROCK_ARMOR_MATERIAL, 3, slot);
    }

    /*@Override
    public String getArmorTexture(@Nonnull ItemStack stack, Entity entity, EquipmentSlotType slot, String layer) {

        String layerId = "1";
        if(slot == EquipmentSlotType.LEGS){
            layerId = "2";
        }
        return "bedrocktools2:textures/armor/bedrock_layer_" + layerId + ".png";
    }*/

}
