package com.theundertaker11.geneticsreborn.blocks.dnaextractor;

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
/**
 * I copy any of my other machines from this class, the todo's are just a reminder to myself
 * where I need to change things
 * @author TheUnderTaker11
 *
 */
public class DNAExtractor extends StorageBlockBase{
	
	public DNAExtractor(String name) {
		super(name);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
    {
		return new GRTileEntityDNAExtractor();//TODO change all with this name using ctrl F
    }
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote) return true;
		TileEntity tEntity = worldIn.getTileEntity(pos);
		
		if(tEntity!=null&&tEntity instanceof GRTileEntityDNAExtractor&&hand==EnumHand.MAIN_HAND)
		{
			if(playerIn.getHeldItem(EnumHand.MAIN_HAND)!=null&&playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem()==GRItems.Overclocker)
			{
				GRTileEntityDNAExtractor tile = (GRTileEntityDNAExtractor)tEntity;
				tile.addOverclocker(playerIn, GeneticsReborn.ocDNAExtractor);//TODO maybe change the 5 based on power
			}
			else playerIn.openGui(GeneticsReborn.instance, GuiHandler.DNAExtractorGuiID, worldIn, pos.getX(), pos.getY(), pos.getZ()); //TODO change here
		}
		return true;
	}
}
