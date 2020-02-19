package com.tanttinator.bedrocktools2;

import com.tanttinator.bedrocktools2.capabilities.IRunes;
import com.tanttinator.bedrocktools2.capabilities.Runes;
import com.tanttinator.bedrocktools2.capabilities.RunesStorage;
import com.tanttinator.bedrocktools2.items.BT2Items;
import com.tanttinator.bedrocktools2.worldgen.BedrockiumOreGen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.util.text.TextFormatting;

@Mod(modid = BedrockTools2.MOD_ID, name = BedrockTools2.NAME, version = BedrockTools2.VERSION)
public class BedrockTools2 {

    public static final String MOD_ID = "bedrocktools2";
    public static final String NAME = "Bedrock Tools 2";
    public static final String VERSION = "1.2.0";

    public static final Logger log = LogManager.getLogger(MOD_ID);

    public static final CreativeTabs group = new CreativeTabs("bedrocktools2") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(BT2Items.diamond_stick);
		}

    };

    public enum Element {
        AIR("Air", TextFormatting.YELLOW),
        EARTH("Earth", TextFormatting.GREEN),
        FIRE("Fire", TextFormatting.RED),
        WATER("Water", TextFormatting.BLUE);

        public String name;
        public TextFormatting color;

        private Element(String name, TextFormatting color) {
            this.name = name;
            this.color = color;
        }
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	GameRegistry.registerWorldGenerator(new BedrockiumOreGen(), 0);
        CapabilityManager.INSTANCE.register(IRunes.class, new RunesStorage(), () -> new Runes());
        GameRegistry.addSmelting(new ItemStack(BT2Items.bedrockium_blend), new ItemStack(BT2Items.bedrockium_plate), 1.0F);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
}
