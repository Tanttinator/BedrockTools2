package com.tanttinator.bedrocktools2.blocks;

import java.util.Random;

import com.tanttinator.bedrocktools2.items.BT2Items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BedrockiumOre extends Block {
    public BedrockiumOre() {
        super(Material.ROCK);
        setHardness(40.0F);
        setResistance(1200.0F);
        setHarvestLevel("pickaxe", 4);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return BT2Items.bedrockium_chunks;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return 1 + fortune + random.nextInt(2);
    }
}