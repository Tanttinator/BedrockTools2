package com.tanttinator.bedrocktools2;

import com.tanttinator.bedrocktools2.capabilities.IRunes;
import com.tanttinator.bedrocktools2.capabilities.Runes;
import com.tanttinator.bedrocktools2.capabilities.RunesStorage;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

class SideProxy {
    SideProxy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(SideProxy::commonSetup);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(IRunes.class, new RunesStorage(), () -> new Runes());
    }

    static class Client extends SideProxy {
        Client() {

        }
    }

    static class Server extends SideProxy {
        Server() {
            
        }
    }
}