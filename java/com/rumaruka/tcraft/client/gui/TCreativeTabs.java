package com.rumaruka.tcraft.client.gui;

import com.rumaruka.tcraft.core.misc.CoreMisc;
import com.rumaruka.tcraft.init.BlockInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TCreativeTabs extends CreativeTabs {

    public static TCreativeTabs TCraftTabs = new TCreativeTabs();

    public TCreativeTabs() {
        super(CoreMisc.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(BlockInit.oreTime);
    }
}
