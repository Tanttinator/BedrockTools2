package com.tanttinator.bedrocktools2.items;

import com.tanttinator.bedrocktools2.BedrockTools2;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = BedrockTools2.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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

    @SubscribeEvent
    public static void onEquipmentChanged(LivingEquipmentChangeEvent event) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!ITEM EQUIPPED!!!!!!!!!!!!!!!!!!!!!");
        if(event.getEntity() instanceof PlayerEntity) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!WAS PLAYER!!!!!!!!!!!!!!!!!!!!!");
            PlayerEntity entity = (PlayerEntity) event.getEntity();
            if (event.getTo().getItem() instanceof BedrockArmor || event.getFrom().getItem() instanceof  BedrockArmor) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!WAS BEDROCK ARMOR!!!!!!!!!!!!!!!!!!!!!");
                int n = 0;
                for (ItemStack item : event.getEntity().getArmorInventoryList()) {
                    if (item.getItem() instanceof BedrockArmor) {
                        n++;
                    }
                }
                entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 1, (int)Math.floor(n / 2.0F), false, false, false));
            }
        }
    }

}
