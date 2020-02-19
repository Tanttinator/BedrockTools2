package com.tanttinator.bedrocktools2.items.bedrockium;

import java.util.List;

import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.items.BT2Items;
import com.tanttinator.bedrocktools2.items.IUpgradeable;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BedrockiumHoe extends ItemHoe implements IUpgradeable {

    public BedrockiumHoe() {
        super(BT2Items.BEDROCKIUM_TOOL_MATERIAL);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        for (BedrockTools2.Element e : IUpgradeable.getUpgrades(stack)) {
            tooltip.add(e.name);
        }
    }
    
    @Override
    public void addRune(ItemStack stack, Element rune) {
        IUpgradeable.super.addRune(stack, rune);
        if(rune == Element.EARTH) {
            stack.addEnchantment(Enchantment.getEnchantmentByID(34), 5);
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockpos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        IBlockState blockstate = world.getBlockState(blockpos);
        ItemStack item = player.getHeldItem(hand);
        Boolean sneaking = player.isSneaking();

        if(sneaking && IUpgradeable.HasRune(item, Element.WATER)) {
            IBlockState block = world.getBlockState(blockpos);
            BlockPos offset = blockpos.offset(facing);
            if (block.isSideSolid(world, blockpos, facing) && world.isAirBlock(offset) && !world.isRemote) {
                IBlockState newState = Blocks.WATER.getDefaultState();
                world.setBlockState(offset, newState, 11);
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.SUCCESS;
        }

        EnumActionResult result = super.onItemUse(player, world, blockpos, hand, facing, hitX, hitY, hitZ);

        if (blockstate.getBlock() instanceof IGrowable && IUpgradeable.HasRune(item, Element.FIRE) && result != EnumActionResult.SUCCESS) {
            IGrowable igrowable = (IGrowable)blockstate.getBlock();
            if (igrowable.canGrow(world, blockpos, blockstate, world.isRemote)) {
                if (!world.isRemote) {
                    if (igrowable.canUseBonemeal(world, world.rand, blockpos, blockstate)) {
                        igrowable.grow(world, world.rand, blockpos, blockstate);
                    }
                }

                return EnumActionResult.SUCCESS;
            }
        }

        if(result == EnumActionResult.SUCCESS && IUpgradeable.HasRune(item, Element.AIR) && !sneaking) {
            tryHoe(world, blockpos.north());
            tryHoe(world, blockpos.north().east());
            tryHoe(world, blockpos.east());
            tryHoe(world, blockpos.east().south());
            tryHoe(world, blockpos.south());
            tryHoe(world, blockpos.south().west());
            tryHoe(world, blockpos.west().north());
            tryHoe(world, blockpos.west());
        }

        return result;
    }

    private void tryHoe(World world, BlockPos blockpos) {
        IBlockState above = world.getBlockState(blockpos.up());
        Block block = world.getBlockState(blockpos).getBlock();
        if (world.isAirBlock(blockpos.up()) || above.getBlock().isReplaceable(world, blockpos.up())) {
            if (block == Blocks.GRASS || block == Blocks.GRASS_PATH || block == Blocks.DIRT) {
                if (!world.isRemote) {
                    world.setBlockState(blockpos, Blocks.FARMLAND.getDefaultState(), 11);
                    world.destroyBlock(blockpos.up(), true);
                }
            }
        }
    }
}