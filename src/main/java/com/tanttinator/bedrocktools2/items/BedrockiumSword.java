package com.tanttinator.bedrocktools2.items;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Multimap;
import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.capabilities.RunesProvider;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BedrockiumSword extends SwordItem implements IUpgradeable {

    public BedrockiumSword(int attackDamageIn, float attackSpeedIn) {
        super(BT2Items.BEDROCKIUM_TIER, attackDamageIn, attackSpeedIn, new BT2Properties());
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
        if(rune == Element.EARTH) {
            stack.addEnchantment(Enchantments.UNBREAKING, 5);
        }
        if(rune == Element.FIRE) {
            stack.addEnchantment(Enchantments.FIRE_ASPECT, 1);
        }
        if(rune == Element.AIR) {
            stack.addEnchantment(Enchantments.SHARPNESS, 5);
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot);
        if(slot == EquipmentSlotType.MAINHAND && IUpgradeable.HasRune(stack, Element.AIR)) {
            multimap.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 1.0F, Operation.ADDITION));
        }
        return multimap;
    }
}