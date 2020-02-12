package com.tanttinator.bedrocktools2.capabilities;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.items.IUpgradeable;

@Mod.EventBusSubscriber(modid = BedrockTools2.MOD_ID)
public class CapabilityHandler {

    public static final ResourceLocation RUNES = new ResourceLocation(BedrockTools2.MOD_ID, "runes");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<ItemStack> event) {
        if(!(event.getObject().getItem() instanceof IUpgradeable))
            return;

        event.addCapability(RUNES, new RunesProvider());
    }
}