package com.tanttinator.bedrocktools2.items;

import java.util.List;
import java.util.Objects;

import com.tanttinator.bedrocktools2.BedrockTools2;
import com.tanttinator.bedrocktools2.BedrockTools2.Element;
import com.tanttinator.bedrocktools2.capabilities.RunesProvider;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

public class BedrockiumShovel extends ShovelItem implements IUpgradeable {

    public BedrockiumShovel(float attackDamageIn, float attackSpeedIn) {
        super(BT2Items.BEDROCKIUM_TIER, attackDamageIn, attackSpeedIn, new BT2Properties());
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
        if(rune == Element.AIR) {
            stack.addEnchantment(Enchantments.EFFICIENCY, 5);
        }
        if(rune == Element.EARTH) {
            stack.addEnchantment(Enchantments.UNBREAKING, 5);
        }
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        if(!IUpgradeable.HasRune(context.getItem(), Element.FIRE) || context.func_225518_g_()) {
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