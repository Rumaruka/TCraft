package com.rumaruka.tcraft.common.items;

import com.rumaruka.tcraft.client.gui.TCreativeTabs;
import net.minecraft.item.Item;

public class BasicItem  extends Item {

   public String name;

    public BasicItem(String name){
        setUnlocalizedName(name);
        this.name = name;
        setCreativeTab(TCreativeTabs.TCraftTabs);

    }


}
