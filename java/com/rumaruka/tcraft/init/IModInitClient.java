package com.rumaruka.tcraft.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract interface IModInitClient {

    @SideOnly(Side.CLIENT)
    public abstract void init(Minecraft minecraft, RenderItem render);


}
