package com.tanttinator.bedrocktools2.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;

public class RunesStorage implements IStorage<IRunes> {

    @Override
    public NBTBase writeNBT(Capability<IRunes> capability, IRunes instance, EnumFacing side) {
        return new NBTTagIntArray(instance.getRuneIds());
    }

    @Override
    public void readNBT(Capability<IRunes> capability, IRunes instance, EnumFacing side, NBTBase nbt) {
        instance.setRunesById(((NBTTagIntArray)nbt).getIntArray());
    }
    
}