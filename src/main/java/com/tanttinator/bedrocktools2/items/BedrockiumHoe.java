package com.tanttinator.bedrocktools2.items;

import java.util.List;
import java.util.Objects;

import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.capabilities.RunesProvider;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

public class BedrockiumHoe extends HoeItem implements IUpgradeable {

    public BedrockiumHoe(float attackSpeedIn) {
        super(BT2Items.BEDROCKIUM_TIER, attackSpeedIn, new BT2Properties());
    }

    @Override
    public CompoundNBT getShareTag(ItemStack stack) {
        CompoundNBT nbt = stack.getOrCreateTag();
        stack.getCapability(RunesProvider.RUNES).ifPresent(handler -> {
            nbt.put("runes", Objects.requireNonNull(RunesProvider.RUNES.writeNBT(handler, null)));
        });
        return nbt;
    }

    @Override
    public void readShareTag(ItemStack stack, CompoundNBT nbt) {
        super.readShareTag(stack, nbt);

        if(nbt != null) {
            stack.getCapability(RunesProvider.RUNES).ifPresent(handler -> {
                RunesProvider.RUNES.readNBT(handler, null, nbt.get("runes"));
            });
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        for (BedrockTools2.Element e : IUpgradeable.getUpgrades(stack)) {
            tooltip.add(new StringTextComponent(e.name).applyTextStyle(e.color));
        }
    }
    
    @Override
    public void addRune(ItemStack stack, Element rune) {
        IUpgradeable.super.addRune(stack, rune);
        if(rune == Element.EARTH) {
            stack.addEnchantment(Enchantments.UNBREAKING, 5);
        }
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);

        if(context.func_225518_g_() && IUpgradeable.HasRune(context.getItem(), Element.WATER)) {
            BlockState block = world.getBlockState(blockpos);
            BlockPos offset = blockpos.offset(context.getFace());
            if (block.isSolid() && world.isAirBlock(offset) && !world.isRemote) {
                BlockState newState = Blocks.WATER.getDefaultState();
                world.setBlockState(offset, newState);
                return ActionResultType.SUCCESS;
            }

            return ActionResultType.SUCCESS;
        }

        ActionResultType result = super.onItemUse(context);

        if (blockstate.getBlock() instanceof IGrowable && IUpgradeable.HasRune(context.getItem(), Element.FIRE) && result != ActionResultType.SUCCESS) {
            IGrowable igrowable = (IGrowable)blockstate.getBlock();
            if (igrowable.canGrow(world, blockpos, blockstate, world.isRemote)) {
                if (!world.isRemote) {
                    if (igrowable.canUseBonemeal(world, world.rand, blockpos, blockstate)) {
                        igrowable.grow((ServerWorld)world, world.rand, blockpos, blockstate);
                    }
                }

                return ActionResultType.SUCCESS;
            }
        }

        if(result == ActionResultType.SUCCESS && IUpgradeable.HasRune(context.getItem(), Element.AIR) && !context.func_225518_g_()) {
            tryHoe(world, context.getPos().north());
            tryHoe(world, context.getPos().north().east());
            tryHoe(world, context.getPos().east());
            tryHoe(world, context.getPos().east().south());
            tryHoe(world, context.getPos().south());
            tryHoe(world, context.getPos().south().west());
            tryHoe(world, context.getPos().west());
            tryHoe(world, context.getPos().west().north());
        }

        return result;
    }

    private void tryHoe(World world, BlockPos blockpos) {
        BlockState above = world.getBlockState(blockpos.up());
        if (world.isAirBlock(blockpos.up()) || above.getMaterial() == Material.PLANTS || above.getMaterial() == Material.TALL_PLANTS) {
            BlockState blockstate = HOE_LOOKUP.get(world.getBlockState(blockpos).getBlock());
            if (blockstate != null) {
                if (!world.isRemote) {
                    world.setBlockState(blockpos, blockstate, 11);
                    world.destroyBlock(blockpos.up(), true);
                }
            }
        }
    }
}