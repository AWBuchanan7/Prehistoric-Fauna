package superlord.prehistoricfauna.blocks;


import net.minecraft.block.BlockContainer;

import net.minecraft.block.BlockHorizontal;

import net.minecraft.block.SoundType;

import net.minecraft.block.material.Material;

import net.minecraft.block.properties.PropertyDirection;

import net.minecraft.block.state.BlockStateContainer;

import net.minecraft.block.state.IBlockState;

import net.minecraft.entity.EntityLivingBase;

import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.inventory.Container;

import net.minecraft.inventory.IInventory;

import net.minecraft.inventory.InventoryHelper;

import net.minecraft.item.Item;

import net.minecraft.item.ItemStack;

import net.minecraft.tileentity.TileEntity;

import net.minecraft.util.*;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.math.RayTraceResult;

import net.minecraft.world.World;

import net.minecraftforge.fml.relauncher.Side;

import net.minecraftforge.fml.relauncher.SideOnly;
import superlord.prehistoricfauna.Main;
import superlord.prehistoricfauna.init.ModBlocks;
import superlord.prehistoricfauna.tab.PFTabRegistry;
import superlord.prehistoricfauna.util.BlockEntity;
import superlord.prehistoricfauna.util.DefaultRenderedItem;
import superlord.prehistoricfauna.util.Reference;

import java.util.Random;



public class BlockDNAExtractor2 extends BlockContainer implements DefaultRenderedItem, BlockEntity {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;



	private static boolean keepInventory = false;



	public BlockDNAExtractor2(boolean isActive) {

		super(Material.IRON);

		this.setHardness(3.0F);

		this.setSoundType(SoundType.METAL);

		if (isActive) {

			this.setLightLevel(0.9375F);
			setRegistryName("dna_extractor2_on");
			setTranslationKey("dna_extractor2_on");

		} else {


			this.setCreativeTab(PFTabRegistry.NORMAL);
			setRegistryName("dna_extractor2");
			setTranslationKey("dna_extractor2");

		}

		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

	}



	public static void setState(boolean isActive, World world, BlockPos pos) {

		TileEntity tile = world.getTileEntity(pos);

		EnumFacing facing = EnumFacing.NORTH;

		if(world.getBlockState(pos).getBlock() instanceof BlockDNAExtractor2){

			facing = world.getBlockState(pos).getValue(FACING);

		}

		keepInventory = true;

		if (isActive) {

			world.setBlockState(pos, ModBlocks.ANALYZER_ACTIVE.getDefaultState().withProperty(FACING, facing));

		} else {

			world.setBlockState(pos, ModBlocks.ANALYZER.getDefaultState().withProperty(FACING, facing));

		}

		keepInventory = false;

		if (tile != null) {

			tile.validate();

			world.setTileEntity(pos, tile);

		}

	}



	@Override

	public EnumBlockRenderType getRenderType(IBlockState state) {

		return EnumBlockRenderType.MODEL;

	}



	@Override

	public Item getItemDropped(IBlockState state, Random random, int fortune) {

		return Item.getItemFromBlock(ModBlocks.ANALYZER);

	}



	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());

	}



	@SuppressWarnings("unused")
	private void setDefaultFacing(World world, BlockPos pos, IBlockState state) {

		if (!world.isRemote) {

			IBlockState north = world.getBlockState(pos.north());

			IBlockState south = world.getBlockState(pos.south());

			IBlockState west = world.getBlockState(pos.west());

			IBlockState east = world.getBlockState(pos.east());

			EnumFacing facing = state.getValue(FACING);

			if (facing == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) {

				facing = EnumFacing.SOUTH;

			} else if (facing == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) {

				facing = EnumFacing.NORTH;

			} else if (facing == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) {

				facing = EnumFacing.EAST;

			} else if (facing == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) {

				facing = EnumFacing.WEST;

			}

			world.setBlockState(pos, state.withProperty(FACING, facing), 2);

		}

	}



	@Override

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

		if (!world.isRemote) {

			player.openGui(Main.instance, Reference.GUI_DNA_EXTRACTOR, world, pos.getX(), pos.getY(), pos.getZ());

		}

		return true;

	}



	@Override

	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

		if (stack.hasDisplayName()) {

			TileEntity tile = world.getTileEntity(pos);

			if (tile instanceof TileEntityDNAExtractor) {

				((TileEntityDNAExtractor) tile).setCustomName(stack.getDisplayName());

			}

		}

	}



	@Override

	public void breakBlock(World world, BlockPos pos, IBlockState state) {

		this.dropInventory(world, pos);

		super.breakBlock(world, pos, state);

	}



	private void dropInventory(World world, BlockPos pos) {

		if (!keepInventory  || !(world.getBlockState(pos).getBlock() instanceof BlockDNAExtractor2)) {

			TileEntity entity = world.getTileEntity(pos);

			if (entity == null) {

				return;

			}

			if (entity instanceof TileEntityDNAExtractor) {

				TileEntityDNAExtractor analyzer = (TileEntityDNAExtractor)entity;

				for (int i = 0; i < analyzer.getSizeInventory(); i++) {

					ItemStack stack = analyzer.getStackInSlot(i);

					if (!stack.isEmpty()) {

						InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);

					}

				}

			}

		}

	}



	@Override

	public TileEntity createNewTileEntity(World world, int metadata) {

		return new TileEntityDNAExtractor();

	}



	@Override

	public boolean hasComparatorInputOverride(IBlockState state) {

		return true;

	}



	@Override

	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos) {

		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(pos));

	}



	@Override

	@SideOnly(Side.CLIENT)

	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {

		return new ItemStack(ModBlocks.ANALYZER);

	}



	@Override
	public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.byIndex(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }



	@Override

	public int getMetaFromState(IBlockState state) {

		return state.getValue(FACING).getIndex();

	}



	@Override

	public IBlockState withRotation(IBlockState state, Rotation rotation) {

		return state.withProperty(FACING, rotation.rotate(state.getValue(FACING)));

	}



	@Override

	public IBlockState withMirror(IBlockState state, Mirror mirror) {

		return state.withRotation(mirror.toRotation(state.getValue(FACING)));

	}



	@Override

	protected BlockStateContainer createBlockState() {

		return new BlockStateContainer(this, FACING);

	}



	@Override

	public Class<? extends TileEntity> getEntity() {

		return TileEntityDNAExtractor.class;

	}
}
