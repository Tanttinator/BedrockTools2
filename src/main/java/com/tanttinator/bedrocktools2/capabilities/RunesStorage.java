package com.tanttinator.bedrocktools2.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntArrayNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class RunesStorage implements IStorage<IRunes> {

    @Override
    public INBT writeNBT(Capability<IRunes> capability, IRunes instance, Direction side) {
        return new IntArrayNBT(instance.getRuneIds());
    }

    @Override
    public void readNBT(Capability<IRunes> capability, IRunes instance, Direction side, INBT nbt) {
        instance.setRunesById(((IntArrayNBT) nbt).getIntArray());
    }
    
}