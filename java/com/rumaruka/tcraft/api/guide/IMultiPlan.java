package com.rumaruka.tcraft.api.guide;

import net.minecraft.item.ItemStack;

public abstract interface IMultiPlan {

    public  abstract ItemStack[][][]getPlan();

    public abstract float getPlanRenderScale();
}
