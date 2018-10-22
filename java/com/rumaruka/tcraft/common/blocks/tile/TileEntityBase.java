package com.rumaruka.tcraft.common.blocks.tile;

import com.rumaruka.tcraft.IRegistryEntry;
import com.rumaruka.tcraft.core.misc.CoreMisc;
import com.rumaruka.tcraft.utils.ModUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TileEntityBase extends TileEntity implements IRegistryEntry {
    private static List<Class<? extends TileEntity>> registered = new ArrayList<>();

    private String name;

    public TileEntityBase(String name) {
        this.name = name;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound, ModUtils.NBTType.SYNC);

        return super.getUpdatePacket();
    }

    private void writeToNBT(NBTTagCompound compound, ModUtils.NBTType sync) {
    }

    public String getName() {
        return CoreMisc.MODID+this.name;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.writeToNBT(compound, ModUtils.NBTType.SAVE);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.readFromNBT(compound, ModUtils.NBTType.SAVE);
    }

    private void readFromNBT(NBTTagCompound compound, ModUtils.NBTType save) {
    }

    @Override
    public IRegistryEntry[] getRegisterElement() {
        return new IRegistryEntry[]{this};

    }

    @Override
    public void onRegister(IRegistryEntry[] otherRegister) {

    }
}
