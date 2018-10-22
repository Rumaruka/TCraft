package com.rumaruka.tcraft.common.blocks;

import com.rumaruka.tcraft.client.gui.TCreativeTabs;
import com.rumaruka.tcraft.utils.generation.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BasicBlock extends Block {
    public  String name;

    public float harvestr;
    public BasicBlock(Material materialIn, String name,float harvestr) {
        super(materialIn);
        this.setUnlocalizedName(name);
        this.name=name;
        this.setHardness(harvestr);
        this.harvestr=harvestr;
        setCreativeTab(TCreativeTabs.TCraftTabs);


    }

    @Override
    public String getLocalizedName() {
        return this.name;
    }

    public BasicBlock generatedBlock(Block toSpawnInside, int minY, int maxY,int maxVein, int chanceToSpawn){
        WorldUtils.addBlockToSpawn(this,toSpawnInside,minY,maxY,maxVein,chanceToSpawn);
        return this;
    }

    public  enum ToolEnum{
        PICKAXE("pickaxe"),
        AXE("axe"),
        SHOVEL("shovel");


        public String toolClass;

        ToolEnum(String toolClass) {
            this.toolClass=toolClass;

        }

    }

    public  enum LevelEnum{
        WOOD(0),
        STONE(1),
        IRON(2),
        DIAMOND(3),
        GOLD(0);


        public int level;

        LevelEnum(int level) {
            this.level=level;

        }

    }

    public BasicBlock setHarvertLevel(ToolEnum tool, LevelEnum level){
        setHarvestLevel(tool.toolClass, level.level);
        return this;
    }
}
