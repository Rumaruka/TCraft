package com.rumaruka.tcraft.player;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class PlayerEx implements Capability.IStorage {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability capability, Object instance, EnumFacing side) {
        return null;
    }

    @Override
    public void readNBT(Capability capability, Object instance, EnumFacing side, NBTBase nbt) {

    }
}
