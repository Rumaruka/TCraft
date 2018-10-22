package com.rumaruka.tcraft.api.guide;

import net.minecraft.item.ItemStack;

public abstract interface IBookPage {

    public abstract IBookPage title(String title);


    public abstract IBookPage para(String paramString);

    public abstract IBookPage para(String paramString, boolean paramBoolean);

    public abstract IBookPage image(String paramString, int paramInt1, int paramInt2);

    public abstract IBookPage image(ItemStack paramItemStack);

    public abstract IBookPage createPage(String text,PageEnum typePage, ItemStack image);

    public abstract IBookPage createPage(String text,PageEnum typePage, IMultiPlan multiPlan);
}
