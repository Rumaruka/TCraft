package com.rumaruka.tcraft.core;


import com.rumaruka.tcraft.common.guide.Book;
import com.rumaruka.tcraft.core.misc.CoreMisc;
import com.rumaruka.tcraft.core.misc.CoreProxy;
import com.rumaruka.tcraft.init.BlockInit;
import com.rumaruka.tcraft.init.ItemInit;
import com.rumaruka.tcraft.init.TileInit;
import com.rumaruka.tcraft.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = CoreMisc.MODID,name = CoreMisc.NAME,version = CoreMisc.VERSION)
public class tcraft {
    @SidedProxy(serverSide = CoreProxy.serverProxy, clientSide = CoreProxy.clientProxy)
    public static CommonProxy proxy;

    public tcraft instance;






    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        BlockInit.init();
        BlockInit.InGameRegister();

        ItemInit.init();
        ItemInit.InGameRegister();

        TileInit.registerTileEntity();
        proxy.initProxySystem(e);
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {

    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.Renders();
    }




}
