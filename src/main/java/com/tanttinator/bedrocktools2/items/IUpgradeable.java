package com.tanttinator.bedrocktools2.items;

import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.capabilities.IRunes;
import com.tanttinator.bedrocktools2.capabilities.RunesProvider;

import net.minecraft.item.ItemStack;

public interface IUpgradeable {
    public static BedrockTools2.Element[] getUpgrades(ItemStack stack) {

        if(RunesProvider.RUNES == null)
            return new BedrockTools2.Element[0];
        
        IRunes runeCapability = stack.getCapability(RunesProvider.RUNES, null);
        int[] runeIds = runeCapability.getRuneIds();
        BedrockTools2.Element[] runes = new BedrockTools2.Element[runeIds.length];
        for(int i = 0; i < runeIds.length; i++) {
            runes[i] = BedrockTools2.Element.values()[runeIds[i]];
        }
        return runes;
    }

    public static Boolean HasRune(ItemStack stack, Element element) {
        if(stack.getItem() instanceof IUpgradeable && RunesProvider.RUNES != null) {
            IRunes runes = stack.getCapability(RunesProvider.RUNES, null);
            return runes.hasRune(element);
        }
        return false;
    }

    public default void addRune(ItemStack stack, BedrockTools2.Element rune) {
        IRunes runes = stack.getCapability(RunesProvider.RUNES, null);
        runes.add(rune);
    }
}