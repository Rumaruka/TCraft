package com.rumaruka.tcraft.common.blocks;

import com.rumaruka.tcraft.common.blocks.tile.TileEntityBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BasicBlockContainer extends BasicBlock implements ITileEntityProvider {

    protected Class<? extends TileEntityBase>tileClass;

    public BasicBlockContainer(Material materialIn, String name, float harvestr,Class<? extends TileEntityBase>tilClass) {
        super(materialIn, name, harvestr);
        this.tileClass=tilClass;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        try {
            return tileClass.newInstance();
        }catch (Exception  e){
            e.printStackTrace();
            return null;
        }
    }
}
