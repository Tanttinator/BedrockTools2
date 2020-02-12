package com.tanttinator.bedrocktools2.items.bedrockium;

import java.util.List;

import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.items.BT2Items;
import com.tanttinator.bedrocktools2.items.IUpgradeable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class BedrockiumAxe extends ItemAxe implements IUpgradeable {

    public BedrockiumAxe() {
        super(BT2Items.BEDROCKIUM_TOOL_MATERIAL, 8.0F, -3.0F);
    }
    
    /*@Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        for (BedrockTools2.Element e : IUpgradeable.getUpgrades(stack)) {
            tooltip.add(new StringTextComponent(e.name).applyTextStyle(e.color));
        }
    }*/
    
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

    /*@Override
    public ActionResultType onItemUse(ItemUseContext context) {

        if(!IUpgradeable.HasRune(context.getItem(), Element.FIRE) || context.isPlacerSneaking()) {
            return super.onItemUse(context);
        }

        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState block = world.getBlockState(blockpos);
        BlockPos offset = blockpos.offset(context.getFace());
        BlockState offsetBlock = world.getBlockState(offset);
        if (context.getFace() != Direction.DOWN && block.isSolid() && !offsetBlock.isSolid() && !world.isRemote) {
            BlockState newState;
            if(context.getFace() == Direction.UP) {
                newState = Blocks.TORCH.getDefaultState();
            } else {
                newState = Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, context.getFace());
            }
            world.setBlockState(offset, newState);
            return ActionResultType.SUCCESS;
        }
  
        return ActionResultType.PASS;
    }*/
}