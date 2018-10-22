package com.rumaruka.tcraft.init;

import com.google.common.base.Strings;
import com.rumaruka.tcraft.common.items.BasicItem;
import com.rumaruka.tcraft.core.misc.CoreMisc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemInit {

    //GUIDE

    //Matreials
    public static BasicItem shardTime;
    //Wands

    //DEBUGING


    //Registry
    public static void init(){
        shardTime = new BasicItem("shardTime");
    }

    public static void InGameRegister(){
    registerItem(shardTime,shardTime.getUnlocalizedName().substring(5));
    }
    public static void Render(){
        renderItem(shardTime);
    }
    //Api
    @Deprecated
    public static void registerItem(Item item, String name)
    {
        if (item.getRegistryName() == null && Strings.isNullOrEmpty(name))
            throw new IllegalArgumentException("Attempted to register a item with no name: " + item);
        if (item.getRegistryName() != null && !item.getRegistryName().toString().equals(name))
            throw new IllegalArgumentException("Attempted to register a item with conflicting names. Old: " + item.getRegistryName() + " New: " + name);
        ForgeRegistries.ITEMS.register(item.getRegistryName() == null ? item.setRegistryName(name) : item);
    }
    public static void renderItem(Item i){

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(
                CoreMisc.MODID + ":" + i.getUnlocalizedName().substring(5), "inventory"));

    }
}
