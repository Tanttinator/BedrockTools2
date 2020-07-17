package com.tanttinator.bedrocktools2.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;

import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.capabilities.CapabilityHandler;
import com.tanttinator.bedrocktools2.capabilities.RunesProvider;

@Mod.EventBusSubscriber(modid = BedrockTools2.MOD_ID)
public class BedrockiumArmor extends ArmorItem implements IUpgradeable {

    public BedrockiumArmor(EquipmentSlotType slot) {
        super(BT2Items.BEDROCKIUM_ARMOR_TIER, slot, new BT2Properties());
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, Entity entity, EquipmentSlotType slot, String layer) {

        String layerId = "1";
        if (slot == EquipmentSlotType.LEGS) {
            layerId = "2";
        }
        return "bedrocktools2:textures/armor/bedrockium_layer_" + layerId + ".png";
    }

    @Override
    public CompoundNBT getShareTag(ItemStack stack) {
        CompoundNBT nbt = stack.getOrCreateTag();
        stack.getCapability(RunesProvider.RUNES).ifPresent(handler -> {
            nbt.put("runes", Objects.requireNonNull(RunesProvider.RUNES.writeNBT(handler, null)));
        });
        return nbt;
    }

    @Override
    public void readShareTag(ItemStack stack, CompoundNBT nbt) {
        super.readShareTag(stack, nbt);

        if(nbt != null) {
            stack.getCapability(RunesProvider.RUNES).ifPresent(handler -> {
                RunesProvider.RUNES.readNBT(handler, null, nbt.get("runes"));
            });
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        for (BedrockTools2.Element e : IUpgradeable.getUpgrades(stack)) {
            tooltip.add(new StringTextComponent(e.name).applyTextStyle(e.color));
        }
    }

    @Override
    public void addRune(ItemStack stack, Element rune) {
        IUpgradeable.super.addRune(stack, rune);
        switch(slot) {
            case HEAD:
                switch(rune) {
                    case AIR: break;
                    case EARTH: stack.addEnchantment(Enchantments.PROTECTION, 5); break;
                    case FIRE: break;
                    case WATER: stack.addEnchantment(Enchantments.RESPIRATION, 5); break;
                }
            break;
            case CHEST: 
                switch(rune) {
                    case AIR: break;
                    case EARTH: break;
                    case FIRE: break;
                    case WATER: stack.addEnchantment(Enchantments.BLAST_PROTECTION, 5); break;
                }
            break;
            case LEGS:
                switch(rune) {
                    case AIR: break;
                    case EARTH: stack.addEnchantment(Enchantments.PROTECTION, 5); break;
                    case FIRE: stack.addEnchantment(Enchantments.FIRE_PROTECTION, 5); break;
                    case WATER: stack.addEnchantment(Enchantments.DEPTH_STRIDER, 5); break;
                }
            break;
            case FEET:
                switch(rune) {
                    case AIR: break;
                    case EARTH: stack.addEnchantment(Enchantments.PROTECTION, 5); break;
                    case FIRE: stack.addEnchantment(Enchantments.FIRE_PROTECTION, 5); break;
                    case WATER: stack.addEnchantment(Enchantments.FROST_WALKER, 1); break;
                }
            break;
            case MAINHAND: break;
            case OFFHAND: break;
        }
    }
}
