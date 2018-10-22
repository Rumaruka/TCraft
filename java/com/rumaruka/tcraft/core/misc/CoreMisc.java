package com.rumaruka.tcraft.core.misc;

import com.rumaruka.tcraft.common.guide.Book;
import com.rumaruka.tcraft.core.tcraft;

public class CoreMisc {

    public static final String MODID ="tcraft";
    public static final String NAME ="TimeCraft";
    public static final String VERSION ="@VERSION";



    public static Book book(){
        return tcraft.proxy.book;
    }


}
