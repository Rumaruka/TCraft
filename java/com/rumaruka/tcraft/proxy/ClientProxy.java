package com.rumaruka.tcraft.proxy;

import com.rumaruka.tcraft.init.BlockInit;
import com.rumaruka.tcraft.init.ItemInit;

public class ClientProxy extends CommonProxy {

    @Override
    public void Renders() {
        BlockInit.Render();
        ItemInit.Render();
    }
}
