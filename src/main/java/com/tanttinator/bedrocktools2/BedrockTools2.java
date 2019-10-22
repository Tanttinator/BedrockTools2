package com.tanttinator.bedrocktools2;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod(BedrockTools2.MOD_ID)
public class BedrockTools2 {

    public static final String MOD_ID = "bedrocktools2";

    public static final ItemGroup group = new ItemGroup("bedrocktools2") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.diamond_stick);
        }
    };
}
