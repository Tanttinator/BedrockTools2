package com.tanttinator.bedrocktools2.blocks;

import com.google.common.collect.Lists;
import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.items.BT2Items;
import com.tanttinator.bedrocktools2.tileentities.BedrockiumDrillEntity;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = BedrockTools2.MOD_ID)
public class BT2Blocks {

    private static List<Block> BLOCKS = Lists.newArrayList();
    //private static List<TileEntityType<?>> ENTITIES = Lists.newArrayList();

    public static final Block bedrockium_ore = register(new Block(Material.ROCK).setHardness(50.0F).setResistance(1200.0F), "bedrockium_ore");
    public static final Block bedrockium_casing = register(new Block(Material.IRON).setHardness(10.0f).setResistance(12.0f), "bedrockium_casing");
    public static final Block bedrockium_drill = register(new BedrockiumDrill(), "bedrockium_drill");
    public static final Block bedrockium_drill_base = register(new Block(Material.IRON).setHardness(10.0f).setResistance(12.0f), "bedrockium_drill_base");
    
    public static Block register(Block block, String name) {
        block.setRegistryName(name);
        block.setUnlocalizedName(name);
        block.setCreativeTab(BedrockTools2.group);
        BLOCKS.add(block);
        BT2Items.register(new ItemBlock(block), name);
        return block;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : BLOCKS) {
            event.getRegistry().register(block);
        }
    }
}
