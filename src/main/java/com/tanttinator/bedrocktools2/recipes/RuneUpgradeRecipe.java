package com.tanttinator.bedrocktools2.recipes;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.items.IUpgradeable;
import com.tanttinator.bedrocktools2.items.RuneItem;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class RuneUpgradeRecipe extends SpecialRecipe {

    public static final ResourceLocation NAME = new ResourceLocation(BedrockTools2.MOD_ID, "rune_upgrade");
    public static final Serializer SERIALIZER = new Serializer(); 

    public RuneUpgradeRecipe(ResourceLocation idIn) {
        super(idIn);
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        ItemStack item = ItemStack.EMPTY;
        ItemStack rune = ItemStack.EMPTY;

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof IUpgradeable) {
                    if (!item.isEmpty()) {
                        return false;
                    }

                    item = itemstack;
                } else if (itemstack.getItem() instanceof RuneItem) {
                    if(IUpgradeable.HasRune(item, ((RuneItem)itemstack.getItem()).getElement()))
                        return false;

                    rune = itemstack;
                } else {
                    return false;
                }
            }
        }

        return !item.isEmpty() && !rune.isEmpty();
    }

    public ItemStack getCraftingResult(CraftingInventory inv) {
        List<ItemStack> runes = Lists.newArrayList();
        ItemStack item = ItemStack.EMPTY;

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof IUpgradeable) {
                    if (!item.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    item = itemstack.copy();
                } else if (itemstack.getItem() instanceof RuneItem) {
                    if(IUpgradeable.HasRune(item, ((RuneItem)itemstack.getItem()).getElement()))
                    return ItemStack.EMPTY;
                   
                    runes.add(itemstack);
                } else {
                    return ItemStack.EMPTY;
                }
            }
        }

        if (!item.isEmpty() && !runes.isEmpty()) {
            for (ItemStack rune : runes) {
                ((IUpgradeable)item.getItem()).addRune(item, ((RuneItem)rune.getItem()).getElement());
            }
            return item;
        } else {
            return ItemStack.EMPTY;
        }
    }

    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<RuneUpgradeRecipe> {

        @Override
        public RuneUpgradeRecipe read(ResourceLocation recipeId, JsonObject json) {
            return new RuneUpgradeRecipe(recipeId);
        }

        @Override
        public RuneUpgradeRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            return new RuneUpgradeRecipe(recipeId);
        }

        @Override
        public void write(PacketBuffer buffer, RuneUpgradeRecipe recipe) {
        }

    }
}