package com.tanttinator.bedrocktools2;

import java.util.List;

import com.google.common.collect.Lists;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.capabilities.RunesProvider;
import com.tanttinator.bedrocktools2.items.BedrockArmor;
import com.tanttinator.bedrocktools2.items.BedrockiumArmor;
import com.tanttinator.bedrocktools2.items.BedrockiumAxe;
import com.tanttinator.bedrocktools2.items.BedrockiumPickaxe;
import com.tanttinator.bedrocktools2.items.BedrockiumShovel;
import com.tanttinator.bedrocktools2.capabilities.IRunes;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BedrockTools2.MOD_ID)
public class EventHandlers {

    @SubscribeEvent
    public static void getBreakSpeed(BreakSpeed speed) {
        PlayerEntity player = speed.getPlayer();
        ItemStack item = player.getHeldItemMainhand();
        if((item.getItem() instanceof BedrockiumPickaxe || item.getItem() instanceof BedrockiumAxe || item.getItem() instanceof BedrockiumShovel)) {
            IRunes runes = item.getCapability(RunesProvider.RUNES, null).orElse(RunesProvider.RUNES.getDefaultInstance());
            if(runes.hasRune(Element.WATER) && player.areEyesInFluid(FluidTags.WATER)) {
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

    static void AddEffect(PlayerEntity entity, Effect effect, int level) {
        entity.removePotionEffect(effect);
        EffectInstance instance = new EffectInstance(effect, 0, level, false, false, false);
        //instance.setPotionDurationMax(true);
        entity.addPotionEffect(instance);
    }

    @SubscribeEvent
    public static void onEquipmentChanged(LivingEquipmentChangeEvent event) {

        if(event.getEntity() instanceof PlayerEntity) {
            PlayerEntity entity = (PlayerEntity) event.getEntity();
            Item from = event.getFrom().getItem();
            if(from instanceof BedrockiumArmor) {
                BedrockiumArmor armor = (BedrockiumArmor)from;
                IRunes runes = event.getFrom().getCapability(RunesProvider.RUNES, null).orElse(RunesProvider.RUNES.getDefaultInstance());
                switch(armor.getEquipmentSlot()) {
                    case HEAD:
                        if(runes.hasRune(BedrockTools2.Element.FIRE)) {
                            entity.removePotionEffect(Effects.NIGHT_VISION);
                        }
                        if(runes.hasRune(Element.AIR) && !entity.abilities.isCreativeMode && !entity.isSpectator()) {
                            entity.abilities.isFlying = false;
                            entity.abilities.allowFlying = false;
                            entity.sendPlayerAbilities();
                        }
                    break;
                    case CHEST:
                        if(runes.hasRune(Element.EARTH)) {
                            entity.removePotionEffect(Effects.HEALTH_BOOST);
                        }
                        if(runes.hasRune(Element.AIR)) {
                            entity.removePotionEffect(Effects.HASTE);
                        }
                    break;
                    case LEGS:
                        if(runes.hasRune(Element.AIR)) {
                            entity.removePotionEffect(Effects.SPEED);
                        }
                    break;
                    case FEET:
                        if(runes.hasRune(Element.AIR)) {
                            entity.removePotionEffect(Effects.JUMP_BOOST);
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

        PlayerEntity player = event.player;

        int bedrockArmor = 0;

        for(ItemStack stack : player.getArmorInventoryList()) {
            if(stack.getItem() instanceof BedrockArmor)
                bedrockArmor++;
            if (stack.getItem() instanceof BedrockiumArmor) {
                BedrockiumArmor armor = (BedrockiumArmor)stack.getItem();
                IRunes runes = stack.getCapability(RunesProvider.RUNES, null).orElse(RunesProvider.RUNES.getDefaultInstance());
                switch(armor.getEquipmentSlot()) {
                    case HEAD:
                        if(runes.hasRune(Element.FIRE)) {
                            AddEffect(player, Effects.NIGHT_VISION, 0);
                        }
                        if(runes.hasRune(Element.AIR)) {
                            player.abilities.allowFlying = true;
                        }
                    break;
                    case CHEST:
                        if(runes.hasRune(Element.EARTH)) {
                            AddEffect(player, Effects.HEALTH_BOOST, 4);
                        }
                        if(runes.hasRune(Element.AIR)) {
                            AddEffect(player, Effects.HASTE, 2);
                        }
                    break;
                    case LEGS:
                        if(runes.hasRune(Element.AIR)) {
                            AddEffect(player, Effects.SPEED, 1);
                        }
                    break;
                    case FEET:
                        if(runes.hasRune(Element.AIR)) {
                            AddEffect(player, Effects.JUMP_BOOST, 4);
                        }
                    break;
                    case MAINHAND: break;
                    case OFFHAND: break;
                }
            }
        }

        player.removePotionEffect(Effects.SLOWNESS);
        if(bedrockArmor > 1) {
            player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, Integer.MAX_VALUE, (int) Math.floor(bedrockArmor / 2.0F) - 1, false, false, false));
        }
    }

    @SubscribeEvent
    public static void onEntityDamaged(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        LivingEntity target = event.getEntityLiving();
        if(source instanceof EntityDamageSource && source.getImmediateSource() != null) {
            for(ItemStack item : target.getArmorInventoryList()) {
                if(item.getItem() instanceof BedrockiumArmor) {
                    BedrockiumArmor armor = (BedrockiumArmor)item.getItem();
                    if(armor.getEquipmentSlot() == EquipmentSlotType.CHEST) {
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