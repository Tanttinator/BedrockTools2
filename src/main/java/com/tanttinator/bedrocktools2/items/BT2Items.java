package com.tanttinator.bedrocktools2.items;

import com.google.common.collect.Lists;
import com.tanttinator.bedrocktools2.BedrockTools2;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;

@Mod.EventBusSubscriber(modid = BedrockTools2.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(BedrockTools2.MOD_ID)
public class BT2Items {

    private static List<Item> items = Lists.newArrayList();

    public static BT2Tier BEDROCK_TIER = new BT2Tier(6000, 5.0F, 1.5F, 4, 15, null);
    public static BT2ArmorTier BEDROCK_ARMOR_TIER = new BT2ArmorTier(132, new int[]{6, 12, 16, 6}, 20, SoundEvents.BLOCK_STONE_PLACE, Ingredient.fromItems(Items.BEDROCK), "bedrock", 4.0F);

    public static final Item diamond_stick = register(new BT2Item(), "diamond_stick");

    public static final Item bedrockium_chunks = register(new BT2Item(), "bedrockium_chunks");
    public static final Item bedrockium_blend = register(new BT2Item(), "bedrockium_blend");
    public static final Item bedrockium_plate = register(new BT2Item(), "bedrockium_plate");

    public static final Item bedrock_pick = register(new PickaxeItem(BEDROCK_TIER, 1, -2.8F, new BT2Properties()), "bedrock_pick");
    public static final Item bedrock_axe = register(new AxeItem(BEDROCK_TIER, 8.0F, -3.0F, new BT2Properties()), "bedrock_axe");
    public static final Item bedrock_shovel = register(new ShovelItem(BEDROCK_TIER, 1.5F, -3F, new BT2Properties()), "bedrock_shovel");
    public static final Item bedrock_hoe = register(new HoeItem(BEDROCK_TIER, 0.4F, new BT2Properties()), "bedrock_hoe");
    public static final Item bedrock_sword = register(new SwordItem(BEDROCK_TIER, 3, -2.4F, new BT2Properties()), "bedrock_sword");

    public static final Item bedrock_helmet = register(new BedrockArmor(EquipmentSlotType.HEAD), "bedrock_helmet");
    public static final Item bedrock_chestplate = register(new BedrockArmor(EquipmentSlotType.CHEST), "bedrock_chestplate");
    public static final Item bedrock_leggings = register(new BedrockArmor(EquipmentSlotType.LEGS), "bedrock_leggings");
    public static final Item bedrock_boots = register(new BedrockArmor(EquipmentSlotType.FEET), "bedrock_boots");

    public static Item register(Item item, String name) {
        item.setRegistryName(BedrockTools2.MOD_ID, name);
        items.add(item);
        return item;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for(Item item : items) {
            event.getRegistry().register(item);
        }
    }
}
