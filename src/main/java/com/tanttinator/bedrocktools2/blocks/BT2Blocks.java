package com.tanttinator.bedrocktools2.blocks;

import com.google.common.collect.Lists;
import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.items.BT2Items;
import com.tanttinator.bedrocktools2.items.BT2Properties;
import com.tanttinator.bedrocktools2.tileentities.BedrockiumDrillEntity;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;

@Mod.EventBusSubscriber(modid = BedrockTools2.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(BedrockTools2.MOD_ID)
public class BT2Blocks {

    private static List<Block> BLOCKS = Lists.newArrayList();
    private static List<TileEntityType<?>> ENTITIES = Lists.newArrayList();

    public static final Block bedrockium_ore = register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F).harvestLevel(4)), "bedrockium_ore");
    public static final Block bedrockium_casing = register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(10.0f, 12.0f).sound(SoundType.METAL)), "bedrockium_casing");
    public static final Block bedrockium_drill = register(new BedrockiumDrill(), "bedrockium_drill");
    public static final Block bedrockium_drill_base = register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(10.0f, 12.0f).sound(SoundType.METAL)), "bedrockium_drill_base");
    
    public static final TileEntityType<BedrockiumDrillEntity> bedrockium_drill_entity = registerTileEntity("bedrockium_drill", TileEntityType.Builder.create(BedrockiumDrillEntity::new, bedrockium_drill));

    public static Block register(Block block, String name) {
        block.setRegistryName(BedrockTools2.MOD_ID, name);
        BLOCKS.add(block);
        BT2Items.register(new BlockItem(block, new BT2Properties()), name);
        return block;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Block block : BLOCKS) {
            event.getRegistry().register(block);
        }
    }

    public static <T extends TileEntity> TileEntityType<T> registerTileEntity(String key, TileEntityType.Builder<T> builder) {
        TileEntityType<T> type = builder.build(null);
        type.setRegistryName(BedrockTools2.MOD_ID, key);
        ENTITIES.add(type);
        return type;
    }

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        for(TileEntityType<?> entity : ENTITIES) {
            event.getRegistry().register(entity);
        }
    }
}
