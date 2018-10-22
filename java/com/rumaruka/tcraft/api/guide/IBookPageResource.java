package com.rumaruka.tcraft.api.guide;

import net.minecraft.item.ItemStack;

public abstract interface IBookPageResource {

    public abstract String getPageID();
    public abstract String getTitleID();
    public abstract String getContentResourceID();
    public abstract ItemStack getStack();

}
