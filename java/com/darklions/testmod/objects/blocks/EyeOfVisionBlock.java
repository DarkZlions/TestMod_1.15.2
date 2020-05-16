package com.darklions.testmod.objects.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class EyeOfVisionBlock extends Block
{
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	private static final VoxelShape SHAPE_N = Stream.of(
			Block.makeCuboidShape(0, 0, 0, 16, 5, 1),
			Block.makeCuboidShape(0, 11, 0, 16, 16, 1),
			Block.makeCuboidShape(0, 0, 1, 1, 5, 15),
			Block.makeCuboidShape(0, 11, 1, 1, 16, 15),
			Block.makeCuboidShape(15, 0, 1, 16, 5, 15),
			Block.makeCuboidShape(15, 11, 1, 16, 16, 15),
			Block.makeCuboidShape(0, 0, 15, 16, 5, 16),
			Block.makeCuboidShape(0, 11, 15, 16, 16, 16),
			Block.makeCuboidShape(0, 5, 0, 1, 11, 1),
			Block.makeCuboidShape(0, 5, 15, 1, 11, 16),
			Block.makeCuboidShape(15, 5, 15, 16, 11, 16),
			Block.makeCuboidShape(15, 5, 0, 16, 11, 1),
			Block.makeCuboidShape(7.5, 7.5, 7.5, 8.5, 8.5, 8.5),
			Block.makeCuboidShape(5.5, 5.5, 5.5, 10.5, 10.5, 10.5),
			Block.makeCuboidShape(6.5, 6.5, 4.5, 9.5, 9.5, 11.5),
			Block.makeCuboidShape(4.5, 6.5, 6.5, 11.5, 9.5, 9.5),
			Block.makeCuboidShape(6.5, 4.5, 6.5, 9.5, 11.5, 9.5),
			Block.makeCuboidShape(1, 0, 1, 15, 1, 15),
			Block.makeCuboidShape(1, 15, 1, 15, 16, 15)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

	public EyeOfVisionBlock(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		switch(state.get(FACING))
		{
			case NORTH:
				return SHAPE_N;
			case SOUTH:
				return SHAPE_N;
			case EAST:
				return SHAPE_N;
			case WEST:
				return SHAPE_N;
			default:
				return SHAPE_N;
		}
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rot) 
	{
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) 
	{
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		builder.add(FACING);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) 
	{
		if(!worldIn.isRemote())
		{
			ServerWorld serverWorld = (ServerWorld) worldIn;
			LightningBoltEntity entity = new LightningBoltEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
			serverWorld.addLightningBolt(entity);
		}
		
		return ActionResultType.SUCCESS;
	}
	
}
