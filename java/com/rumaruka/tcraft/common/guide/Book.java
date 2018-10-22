package com.rumaruka.tcraft.common.guide;

import com.rumaruka.tcraft.api.IModService;
import com.rumaruka.tcraft.api.guide.IBookPage;
import com.rumaruka.tcraft.api.guide.PageEnum;
import com.rumaruka.tcraft.common.entity.EntityGuideGem;
import com.rumaruka.tcraft.init.IModInitClient;
import com.rumaruka.tcraft.utils.Point2d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

public class Book implements IModService,IModInitClient {


    @SideOnly(Side.CLIENT)
    private Page index;
    @SideOnly(Side.CLIENT)
    private Page extensions;
    @SideOnly(Side.CLIENT)
    private boolean hasExtensions;
    @SideOnly(Side.CLIENT)
    private Map<String, Page> pages;




    @Override
    public void init(Minecraft minecraft, RenderItem render) {
        this.pages= new HashMap();
        this.index=new Page("index",this);
        this.index.title("book.tcraft:index:title").image("tcraft:textures/gui/testwheel.png",64,64);
        IBookPage about = this.index.createPage("about", PageEnum.CHILD).para("book.tcraft:about.p1",true);


        int i = -1;
        int subPage = 1;
        int BULLETS_PER_PAGE = 9;
    }

    public IBookPage createExtensionIndex(String id)
    {
        if (!this.hasExtensions)
        {
            this.hasExtensions = true;
            this.index.addPage(this.extensions, PageEnum.CHILD);
        }
        return this.extensions.createPage(id, PageEnum.CHILD);
    }

    public void addToBook(Page chapter)
    {
        this.pages.put(chapter.getID(), chapter);
    }

    public Page getPageForId(String id)
    {
        Page page = (Page)this.pages.get(id);
        return page != null ? page : this.index;
    }
    @SideOnly(Side.CLIENT)
    public void render(EntityGuideGem gem, EntityPlayerSP player, Point2d pointer, float partialTicks, RenderManager renderManager)
    {
        PlayerEx playerEx = PlayerEx.get(player);
        BookNavigator nav = playerEx.getBookNavigator();
        String id = nav.getCurrentPage();
        Page page = getPageForId(id);
        if (page != null) {
            page.render(gem, player, nav, pointer, partialTicks, renderManager);
        }
    }
    public boolean exists(String pageName)
    {
        return this.pages.containsKey(pageName);
    }
}
