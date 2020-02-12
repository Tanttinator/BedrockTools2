package com.tanttinator.bedrocktools2.blocks;

import com.tanttinator.bedrocktools2.tileentities.BedrockiumDrillEntity;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BedrockiumDrill extends Block implements ITileEntityProvider {

    public BedrockiumDrill() {
        super(Material.IRON);
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new BedrockiumDrillEntity();
	}
    
    
}