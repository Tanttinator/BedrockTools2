package com.tanttinator.bedrocktools2.tileentities;

import com.tanttinator.bedrocktools2.blocks.BT2Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class BedrockiumDrillEntity extends TileEntity implements ITickable {

    public BedrockiumDrillEntity() {
        super();
    }

	@Override
	public void update() {
        if(!this.world.isRemote && this.world.isBlockPowered(this.pos) && this.world.getBlockState(this.pos.down().down()).getBlock() == BT2Blocks.bedrockium_drill_base && !this.world.isAirBlock(this.pos.down())) {
            IBlockState block = this.world.getBlockState(this.pos.down());
            //ItemStack stack = new ItemStack(block.getBlock().asItem(), 1);
            //ItemEntity entity = new ItemEntity(this.world, this.pos.down().getX(), this.pos.down().getY(), this.pos.down().getZ(), stack);
            this.world.destroyBlock(this.pos.down(), false);
            //this.world.addEntity(entity);
        }
	}
}