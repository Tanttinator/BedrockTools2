package com.tanttinator.bedrocktools2.items;

import java.util.List;

import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class BedrockiumPickaxe extends PickaxeItem implements IUpgradeable {

    public BedrockiumPickaxe(int attackDamageIn, float attackSpeedIn) {
        super(BT2Items.BEDROCKIUM_TIER, attackDamageIn, attackSpeedIn, new BT2Properties());
    }
    
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        for (BedrockTools2.Element e : IUpgradeable.getUpgrades(stack)) {
            tooltip.add(new StringTextComponent(e.name).applyTextStyle(e.color));
        }
    }


    @Override
    public void addRune(ItemStack stack, Element rune) {
        IUpgradeable.super.addRune(stack, rune);
        if(rune == Element.AIR) {
            stack.addEnchantment(Enchantments.EFFICIENCY, 5);
        }
        if(rune == Element.EARTH) {
            stack.addEnchantment(Enchantments.UNBREAKING, 5);
        }
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        if(!IUpgradeable.HasRune(context.getItem(), Element.FIRE)) {
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
    }
}