package com.rumaruka.tcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockEnergy extends BasicBlock implements ICapabilityProvider {




    public BlockEnergy(Material materialIn, String name, float harvestr) {
        super(materialIn, name, harvestr);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return false;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return null;
    }
}
