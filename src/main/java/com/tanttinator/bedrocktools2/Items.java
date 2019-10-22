package com.tanttinator.bedrocktools2;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = BedrockTools2.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(BedrockTools2.MOD_ID)
public class Items {

    public static final Item diamond_stick = null;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new Item(new Item.Properties().group(BedrockTools2.group)).setRegistryName(BedrockTools2.MOD_ID, "diamond_stick")
        );
    }
}
