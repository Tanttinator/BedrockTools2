package com.tanttinator.bedrocktools2.worldgen;

import java.util.Random;

import com.tanttinator.bedrocktools2.blocks.BT2Blocks;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class BedrockiumOreGen implements IWorldGenerator {

    private final WorldGenMinable bedrockiumOreGenerator;

    public BedrockiumOreGen() {
        bedrockiumOreGenerator = new WorldGenMinable(BT2Blocks.bedrockium_ore.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.STONE));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(world.provider.getDimensionType() == DimensionType.OVERWORLD) {
            genStandard(bedrockiumOreGenerator, world, random, chunkX, chunkZ, 2, 0, 10);
        }
    }

    private void genStandard(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int spawnTries, int minHeight, int maxHeight) {
        if(minHeight < 0) minHeight = 0;
        if(maxHeight > 255) maxHeight = 255;
        
        if(maxHeight < minHeight) {
            maxHeight += minHeight;
            minHeight = maxHeight - minHeight;
            maxHeight -= minHeight;
        } else if(maxHeight == minHeight) {
            if(maxHeight < 255) {
                maxHeight++;
            } else {
                minHeight--;
            }
        }

        BlockPos chunkPosAsBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
        int heightDiff = maxHeight - minHeight + 1;

        for(int i = 0; i < spawnTries; i++) {
            generator.generate(world, random, chunkPosAsBlockPos.add(random.nextInt(16), minHeight + random.nextInt(heightDiff), random.nextInt(16)));
        }
    }
    
}