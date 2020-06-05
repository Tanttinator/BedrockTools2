package com.tanttinator.bedrocktools2.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class RunesProvider implements ICapabilitySerializable<INBT> {

    @CapabilityInject(IRunes.class)
    public static final Capability<IRunes> RUNES = null;

    private IRunes instance = RUNES.getDefaultInstance();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return RUNES.orEmpty(cap, LazyOptional.of(() -> this.instance));
    }

    @Override
    public INBT serializeNBT() {
        return RUNES.getStorage().writeNBT(RUNES, this.instance, null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        RUNES.getStorage().readNBT(RUNES, this.instance, null, nbt);
    }
    
}