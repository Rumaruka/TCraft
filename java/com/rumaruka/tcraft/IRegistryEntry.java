package com.rumaruka.tcraft;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IRegistryEntry {

    IRegistryEntry[] getRegisterElement();
    void onRegister(IRegistryEntry[] otherRegister);
    @SideOnly(Side.CLIENT)
    default void loadClientSide(){

    }
}
