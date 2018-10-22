package com.rumaruka.tcraft.proxy;

import com.rumaruka.tcraft.api.IModService;
import com.rumaruka.tcraft.common.guide.Book;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.List;

public class CommonProxy {
    protected final List<IModService> services = new ArrayList();
    public Book book;



    public void registerService(IModService service){
        this.services.add(service);
    }

    public void  initProxySystem(FMLPreInitializationEvent e){
        registerService(this.book = new Book());
    }


    public void Renders(){

    }
}
