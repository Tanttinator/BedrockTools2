package com.tanttinator.bedrocktools2;

import java.util.List;

import com.google.common.collect.Lists;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.capabilities.RunesProvider;
import com.tanttinator.bedrocktools2.items.bedrock.BedrockArmor;
import com.tanttinator.bedrocktools2.items.bedrockium.BedrockiumArmor;
import com.tanttinator.bedrocktools2.items.bedrockium.BedrockiumAxe;
import com.tanttinator.bedrocktools2.items.bedrockium.BedrockiumPickaxe;
import com.tanttinator.bedrocktools2.items.bedrockium.BedrockiumShovel;
import com.tanttinator.bedrocktools2.capabilities.IRunes;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BedrockTools2.MOD_ID)
public class EventHandlers {

    @SubscribeEvent
    public static void getBreakSpeed(BreakSpeed speed) {
        EntityPlayer player = speed.getEntityPlayer();
        ItemStack item = player.getHeldItemMainhand();
        if((item.getItem() instanceof BedrockiumPickaxe || item.getItem() instanceof BedrockiumAxe || item.getItem() instanceof BedrockiumShovel)) {
            IRunes runes = item.getCapability(RunesProvider.RUNES, null);
            if(runes.hasRune(Element.WATER) && player.isInsideOfMaterial(Material.WATER)) {
                speed.setNewSpeed(speed.getOriginalSpeed() * 5f);
            }
        }
    }
    /*@SubscribeEvent
    public static void getHarvestDrop(BlockEvent.HarvestDropsEvent event) {
        PlayerEntity player = event.getHarvester();
        if(player == null)
            return;
        ItemStack stack = player.getHeldItemMainhand();
        if(!(stack.getItem() instanceof BedrockiumPickaxe) || !(IUpgradeable.HasRune(stack, Element.FIRE)) )
            return;
        System.out.println("Trying to mine a block with a Fire Infused Bedrockium Pick!");
        List<ItemStack> drops = event.getDrops();
        World world = player.getEntityWorld();

        for (ItemStack itemStack : drops) {
            for(IRecipe<?> r : world.getRecipeManager().getRecipes()) {
                if(r.getType() == IRecipeType.SMELTING) {
                    AbstractCookingRecipe recipe = (AbstractCookingRecipe)r;
                    if(recipe.getIngredients().get(0).test(itemStack)) {
                        int i = drops.indexOf(itemStack);
                        drops.set(i, recipe.getRecipeOutput());
                    }
                }
            }
        }
    }*/

    static void AddEffect(EntityPlayer entity, int effectId, int level) {
    	Potion effect = Potion.getPotionById(effectId);
        entity.removePotionEffect(effect);
        PotionEffect instance = new PotionEffect(effect, Integer.MAX_VALUE, level, false, false);
        instance.setPotionDurationMax(true);
        entity.addPotionEffect(instance);
    }

    @SubscribeEvent
    public static void onEquipmentChanged(LivingEquipmentChangeEvent event) {

        if(event.getEntity() instanceof EntityPlayer) {
        	EntityPlayer entity = (EntityPlayer) event.getEntity();
            Item from = event.getFrom().getItem();
            ItemStack to = event.getTo();

            if(from instanceof BedrockiumArmor) {
                BedrockiumArmor armor = (BedrockiumArmor)from;
                IRunes runes = event.getFrom().getCapability(RunesProvider.RUNES, null);
                switch(armor.getEquipmentSlot()) {
                    case HEAD:
                        if(runes.hasRune(BedrockTools2.Element.FIRE)) {
                            entity.removePotionEffect(Potion.getPotionById(16));
                        }
                        if(runes.hasRune(Element.AIR) && !entity.capabilities.isCreativeMode && !entity.isSpectator()) {
                            entity.capabilities.isFlying = false;
                            entity.capabilities.allowFlying = false;
                            entity.sendPlayerAbilities();
                        }
                    break;
                    case CHEST:
                        if(runes.hasRune(Element.EARTH)) {
                            entity.removePotionEffect(Potion.getPotionById(21));
                        }
                        if(runes.hasRune(Element.AIR)) {
                            entity.removePotionEffect(Potion.getPotionById(3));
                        }
                    break;
                    case LEGS:
                        if(runes.hasRune(Element.AIR)) {
                            entity.removePotionEffect(Potion.getPotionById(1));
                        }
                    break;
                    case FEET:
                        if(runes.hasRune(Element.AIR)) {
                            entity.removePotionEffect(Potion.getPotionById(8));
                        }
                    break;
                    case MAINHAND: break;
                    case OFFHAND: break;
                }
            }

            if (to.getItem() instanceof BedrockiumArmor) {
                BedrockiumArmor armor = (BedrockiumArmor)to.getItem();
                IRunes runes = to.getCapability(RunesProvider.RUNES, null);
                switch(armor.getEquipmentSlot()) {
                    case HEAD:
                        if(runes.hasRune(Element.FIRE)) {
                            AddEffect(entity, 16, 0);
                        }
                        if(runes.hasRune(Element.AIR)) {
                            entity.capabilities.allowFlying = true;
                            entity.sendPlayerAbilities();
                        }
                    break;
                    case CHEST:
                        if(runes.hasRune(Element.EARTH)) {
                            AddEffect(entity, 21, 4);
                        }
                        if(runes.hasRune(Element.AIR)) {
                            AddEffect(entity, 3, 2);
                        }
                    break;
                    case LEGS:
                        if(runes.hasRune(Element.AIR)) {
                            AddEffect(entity, 1, 1);
                        }
                    break;
                    case FEET:
                        if(runes.hasRune(Element.AIR)) {
                            AddEffect(entity, 8, 4);
                        }
                    break;
                    case MAINHAND: break;
                    case OFFHAND: break;
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTickPlayerEvent(TickEvent.PlayerTickEvent event) {

    	EntityPlayer player = event.player;

        int bedrockArmor = 0;

        for(ItemStack stack : player.getArmorInventoryList()) {
            if(stack.getItem() instanceof BedrockArmor)
                bedrockArmor++;

            if (stack.getItem() instanceof BedrockiumArmor) {
                BedrockiumArmor armor = (BedrockiumArmor)stack.getItem();
                IRunes runes = stack.getCapability(RunesProvider.RUNES, null);
                switch(armor.getEquipmentSlot()) {
                    case HEAD:
                        if(runes.hasRune(Element.FIRE) && !player.isPotionActive(Potion.getPotionById(16))) {
                            AddEffect(player, 16, 0);
                        }
                        if(runes.hasRune(Element.AIR)) {
                            player.capabilities.allowFlying = true;
                            player.sendPlayerAbilities();
                        }
                    break;
                    case CHEST:
                        if(runes.hasRune(Element.EARTH) && !player.isPotionActive(Potion.getPotionById(21))) {
                            AddEffect(player, 21, 4);
                        }
                        if(runes.hasRune(Element.AIR) && !player.isPotionActive(Potion.getPotionById(3))) {
                            AddEffect(player, 3, 2);
                        }
                    break;
                    case LEGS:
                        if(runes.hasRune(Element.AIR) && !player.isPotionActive(Potion.getPotionById(1))) {
                            AddEffect(player, 1, 1);
                        }
                    break;
                    case FEET:
                        if(runes.hasRune(Element.AIR) && !player.isPotionActive(Potion.getPotionById(8))) {
                            AddEffect(player, 8, 4);
                        }
                    break;
                    case MAINHAND: break;
                    case OFFHAND: break;
                }
            }
        }

        player.removePotionEffect(Potion.getPotionById(2));
        if(bedrockArmor > 1) {
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), Integer.MAX_VALUE, (int) Math.floor(bedrockArmor / 2.0F) - 1, false, false));
        }
    }

    @SubscribeEvent
    public static void onEntityDamaged(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        EntityLivingBase target = (EntityLivingBase) event.getEntityLiving();
        if(source instanceof EntityDamageSource && source.getImmediateSource() != null) {
            for(ItemStack item : target.getArmorInventoryList()) {
                if(item.getItem() instanceof BedrockiumArmor) {
                    BedrockiumArmor armor = (BedrockiumArmor)item.getItem();
                    if(armor.getEquipmentSlot() == EntityEquipmentSlot.CHEST) {
                        setOnFire.add(source.getImmediateSource());
                    }
                }
            }
        }
    }

    static List<Entity> setOnFire;

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent event) {
        if(event.phase == Phase.START) {
            setOnFire = Lists.newArrayList();
        }
        if(event.phase == Phase.END) {
            for (Entity entity : setOnFire) {
                entity.setFire(8);
            }
        }
    }
}