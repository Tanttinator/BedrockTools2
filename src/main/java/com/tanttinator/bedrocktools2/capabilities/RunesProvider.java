package com.tanttinator.bedrocktools2.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class RunesProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IRunes.class)
    public static final Capability<IRunes> RUNES = null;

    private IRunes instance = RUNES.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == RUNES;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == RUNES ? RUNES.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return RUNES.getStorage().writeNBT(RUNES, instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		RUNES.getStorage().readNBT(RUNES, instance, null, nbt);
	}
    
}