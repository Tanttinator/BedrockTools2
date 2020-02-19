package com.tanttinator.bedrocktools2.items.bedrockium;

import java.util.List;

import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.items.BT2Items;
import com.tanttinator.bedrocktools2.items.IUpgradeable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BedrockiumShovel extends ItemSpade implements IUpgradeable {

    public BedrockiumShovel() {
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
        if(rune == Element.AIR) {
            stack.addEnchantment(Enchantment.getEnchantmentByID(32), 5);
        }
        if(rune == Element.EARTH) {
            stack.addEnchantment(Enchantment.getEnchantmentByID(34), 5);
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        
        ItemStack item = player.getHeldItem(hand);

        if(!IUpgradeable.HasRune(item, Element.FIRE) || player.isSneaking()) {
            return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
        }

        IBlockState block = world.getBlockState(pos);
        BlockPos offset = pos.offset(facing);
        IBlockState offsetBlock = world.getBlockState(offset);
        if (facing != EnumFacing.DOWN && block.isSideSolid(world, pos, facing) && !offsetBlock.causesSuffocation() && !world.isRemote) {
            world.setBlockState(offset, Block.getBlockFromName("torch").getDefaultState().withProperty(BlockTorch.FACING, facing));
            return EnumActionResult.SUCCESS;
        }
  
        return EnumActionResult.PASS;
    }
}