package com.rumaruka.tcraft.utils;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

public enum PrefixUtil {;

    private PrefixUtil(){}


    public static String withPrefix(String id){
        int index=id.indexOf(':');
        String oldPrefix = index == -1 ? "" : id.substring(0,index);

        ModContainer modContainer = Loader.instance().activeModContainer();
        String prefix;
        if(modContainer!=null){
            prefix=modContainer.getModId();

        }else {
            prefix ="minecraft";
        }
        if(!oldPrefix.equals(prefix)){
            id=prefix+":"+id;
        }



        return id;
    }
}
