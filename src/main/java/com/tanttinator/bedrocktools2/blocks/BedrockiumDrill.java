package com.tanttinator.bedrocktools2.blocks;

import com.tanttinator.bedrocktools2.tileentities.BedrockiumDrillEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BedrockiumDrill extends Block {

    public BedrockiumDrill() {
        super(Block.Properties.create(Material.IRON).hardnessAndResistance(10.0f, 12.0f).sound(SoundType.METAL));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new BedrockiumDrillEntity();
    }
}