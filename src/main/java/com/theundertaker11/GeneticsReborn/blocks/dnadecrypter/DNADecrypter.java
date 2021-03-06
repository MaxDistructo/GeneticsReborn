package com.theundertaker11.geneticsreborn.blocks.dnadecrypter;

import javax.annotation.Nullable;

import com.theundertaker11.geneticsreborn.GeneticsReborn;
import com.theundertaker11.geneticsreborn.blocks.StorageBlockBase;
import com.theundertaker11.geneticsreborn.gui.GuiHandler;
import com.theundertaker11.geneticsreborn.items.GRItems;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class DNADecrypter extends StorageBlockBase{
	
	public DNADecrypter(String name) {
		super(name);
	}
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
    {
		return new GRTileEntityDNADecrypter();
    }
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote) return true;
		TileEntity tEntity = worldIn.getTileEntity(pos);
		
		if(tEntity!=null&&tEntity instanceof GRTileEntityDNADecrypter&&hand==EnumHand.MAIN_HAND)
		{
			if(playerIn.getHeldItem(EnumHand.MAIN_HAND)!=null&&playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem()==GRItems.Overclocker)
			{
				GRTileEntityDNADecrypter tile = (GRTileEntityDNADecrypter)tEntity;
				tile.addOverclocker(playerIn, GeneticsReborn.ocDNADecrypter);
			}
			else playerIn.openGui(GeneticsReborn.instance, GuiHandler.DNADecrypterGuiID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
}
