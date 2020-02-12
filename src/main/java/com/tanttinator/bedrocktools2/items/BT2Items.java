package com.tanttinator.bedrocktools2.items;

import com.google.common.collect.Lists;
import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.items.bedrock.BedrockArmor;
import com.tanttinator.bedrocktools2.items.bedrock.BedrockAxe;
import com.tanttinator.bedrocktools2.items.bedrock.BedrockHoe;
import com.tanttinator.bedrocktools2.items.bedrock.BedrockPickaxe;
import com.tanttinator.bedrocktools2.items.bedrock.BedrockShovel;
import com.tanttinator.bedrocktools2.items.bedrock.BedrockSword;
import com.tanttinator.bedrocktools2.items.bedrockium.BedrockiumArmor;
import com.tanttinator.bedrocktools2.items.bedrockium.BedrockiumAxe;
import com.tanttinator.bedrocktools2.items.bedrockium.BedrockiumHoe;
import com.tanttinator.bedrocktools2.items.bedrockium.BedrockiumPickaxe;
import com.tanttinator.bedrocktools2.items.bedrockium.BedrockiumShovel;
import com.tanttinator.bedrocktools2.items.bedrockium.BedrockiumSword;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = BedrockTools2.MOD_ID)
public class BT2Items {

    private static List<Item> items = Lists.newArrayList();

    //public static BT2Tier BEDROCK_TIER = new BT2Tier(6000, 5.0F, 1.5F, 4, 15, Ingredient.fromItems(Items.BEDROCK));
    //public static BT2ArmorTier BEDROCK_ARMOR_TIER = new BT2ArmorTier(132, new int[]{6, 12, 16, 6}, 20, SoundEvents.BLOCK_STONE_PLACE, Ingredient.fromItems(Items.BEDROCK), "bedrock", 4.0F);
    public static ToolMaterial BEDROCK_TOOL_MATERIAL = ToolMaterial.DIAMOND;
    public static ArmorMaterial BEDROCK_ARMOR_MATERIAL = ArmorMaterial.DIAMOND;
    
    public static ToolMaterial BEDROCKIUM_TOOL_MATERIAL = ToolMaterial.DIAMOND;
    public static ArmorMaterial BEDROCKIUM_ARMOR_MATERIAL = ArmorMaterial.DIAMOND;
    

    public static final Item bedrockium_plate = register(new Item(), "bedrockium_plate");
    //public static BT2Tier BEDROCKIUM_TIER = new BT2Tier(18000, 15.0F, 4.0F, 4, 20, Ingredient.fromItems(bedrockium_plate));
    //public static BT2ArmorTier BEDROCKIUM_ARMOR_TIER = new BT2ArmorTier(396, new int[]{12, 24, 32, 12}, 25, SoundEvents.BLOCK_STONE_PLACE, Ingredient.fromItems(bedrockium_plate), "bedrockium", 6.0F);

    public static final Item diamond_stick = register(new Item(), "diamond_stick");

    public static final Item bedrockium_chunks = register(new Item(), "bedrockium_chunks");
    public static final Item bedrockium_blend = register(new Item(), "bedrockium_blend");

    public static final Item bedrock_pick = register(new BedrockPickaxe(), "bedrock_pick");
    public static final Item bedrock_axe = register(new BedrockAxe(), "bedrock_axe");
    public static final Item bedrock_shovel = register(new BedrockShovel(), "bedrock_shovel");
    public static final Item bedrock_hoe = register(new BedrockHoe(), "bedrock_hoe");
    public static final Item bedrock_sword = register(new BedrockSword(), "bedrock_sword");

    public static final Item bedrock_helmet = register(new BedrockArmor(EntityEquipmentSlot.HEAD), "bedrock_helmet");
    public static final Item bedrock_chestplate = register(new BedrockArmor(EntityEquipmentSlot.CHEST), "bedrock_chestplate");
    public static final Item bedrock_leggings = register(new BedrockArmor(EntityEquipmentSlot.LEGS), "bedrock_leggings");
    public static final Item bedrock_boots = register(new BedrockArmor(EntityEquipmentSlot.FEET), "bedrock_boots");

    public static final Item bedrockium_pick = register(new BedrockiumPickaxe(), "bedrockium_pick");
    public static final Item bedrockium_axe = register(new BedrockiumAxe(), "bedrockium_axe");
    public static final Item bedrockium_shovel = register(new BedrockiumShovel(), "bedrockium_shovel");
    public static final Item bedrockium_hoe = register(new BedrockiumHoe(), "bedrockium_hoe");
    public static final Item bedrockium_sword = register(new BedrockiumSword(), "bedrockium_sword");

    public static final Item bedrockium_helmet = register(new BedrockiumArmor(EntityEquipmentSlot.HEAD), "bedrockium_helmet");
    public static final Item bedrockium_chestplate = register(new BedrockiumArmor(EntityEquipmentSlot.CHEST), "bedrockium_chestplate");
    public static final Item bedrockium_leggings = register(new BedrockiumArmor(EntityEquipmentSlot.LEGS), "bedrockium_leggings");
    public static final Item bedrockium_boots = register(new BedrockiumArmor(EntityEquipmentSlot.FEET), "bedrockium_boots");

    public static final Item bedrock_tablet = register(new Item(), "bedrock_tablet");
    public static final Item air_rune = register(new RuneItem(BedrockTools2.Element.AIR), "air_rune");
    public static final Item earth_rune = register(new RuneItem(BedrockTools2.Element.EARTH), "earth_rune");
    public static final Item fire_rune = register(new RuneItem(BedrockTools2.Element.FIRE), "fire_rune");
    public static final Item water_rune = register(new RuneItem(BedrockTools2.Element.WATER), "water_rune");

    public static Item register(Item item, String name) {
        item.setRegistryName(name);
        item.setUnlocalizedName(name);
        item.setCreativeTab(BedrockTools2.group);
        items.add(item);
        return item;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for(Item item : items) {
            event.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for(Item item : items) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
