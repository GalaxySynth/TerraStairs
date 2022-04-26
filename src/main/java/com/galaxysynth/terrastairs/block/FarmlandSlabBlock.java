//package com.galaxysynth.terrastairs.block;
//
//import java.util.Iterator;
//import java.util.Random;
//import net.minecraft.block.*;
//import net.minecraft.block.enums.SlabType;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.ai.pathing.NavigationType;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.fluid.FluidState;
//import net.minecraft.fluid.Fluids;
//import net.minecraft.item.ItemPlacementContext;
//import net.minecraft.item.ItemStack;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.state.StateManager;
//import net.minecraft.state.property.*;
//import net.minecraft.tag.FluidTags;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Direction;
//import net.minecraft.util.shape.VoxelShape;
//import net.minecraft.util.shape.VoxelShapes;
//import net.minecraft.world.BlockView;
//import net.minecraft.world.GameRules;
//import net.minecraft.world.World;
//import net.minecraft.world.WorldAccess;
//import net.minecraft.world.WorldView;
//import org.jetbrains.annotations.Nullable;
//
//public class FarmlandSlabBlock extends FarmlandBlock {
//
//
//    public static final EnumProperty<SlabType> TYPE;
//    public static final BooleanProperty WATERLOGGED;
//    protected static final VoxelShape BOTTOM_SHAPE;
//    protected static final VoxelShape TOP_SHAPE;
//
//    protected FarmlandSlabBlock(Settings settings) {
//        super(settings);
//        this.setDefaultState(this.stateManager.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, false).with(MOISTURE, 0));
//    }
//
//
//    @Override
//    public boolean canReplace(BlockState state, ItemPlacementContext context) {
//        ItemStack itemStack = context.getStack();
//        SlabType slabType = state.get(TYPE);
//        if (slabType != SlabType.DOUBLE && itemStack.isOf(this.asItem())) {
//            if (context.canReplaceExisting()) {
//                boolean bl = context.getHitPos().y - (double)context.getBlockPos().getY() > 0.5;
//                Direction direction = context.getSide();
//                if (slabType == SlabType.BOTTOM) {
//                    return direction == Direction.UP || bl && direction.getAxis().isHorizontal();
//                } else {
//                    return direction == Direction.DOWN || !bl && direction.getAxis().isHorizontal();
//                }
//            } else {
//                return true;
//            }
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public FluidState getFluidState(BlockState state) {
//        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
//    }
//
//    static {
//        TYPE = Properties.SLAB_TYPE;
//        WATERLOGGED = Properties.WATERLOGGED;
//        BOTTOM_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
//        TOP_SHAPE = Block.createCuboidShape(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
//    }
//
//    public static final IntProperty MOISTURE;
//    protected static final VoxelShape SHAPE;
//
//
//    private static boolean hasWater(WorldView world, BlockPos pos) {
//        for (BlockPos blockpos : BlockPos.iterate(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
//            if (world.getFluidState(blockpos).isIn(FluidTags.WATER)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//    @Override
//    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
//        if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
//            world.createAndScheduleBlockTick(pos, this, 1);
//        }
//        if (state.get(WATERLOGGED)) {
//            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
//        }
//        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
//    }
//    @Override
//    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
//        BlockState blockState = world.getBlockState(pos.up());
//        return !blockState.getMaterial().isSolid() || blockState.getBlock() instanceof FenceGateBlock || blockState.getBlock() instanceof PistonExtensionBlock;
//    }
//
//    @Nullable
//    public BlockState getPlacementState(ItemPlacementContext ctx) {
//        BlockPos blockPos = ctx.getBlockPos();
//        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
//        if (blockState.isOf(this)) {
//            return blockState.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, false);
//        } else {
//            FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
//            BlockState blockState2 = this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
//            Direction direction = ctx.getSide();
//            return direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getHitPos().y - (double)blockPos.getY() > 0.5)) ? blockState2 : blockState2.with(TYPE, SlabType.TOP);
//        }
//
//    }
//    @Override
//    public boolean hasSidedTransparency(BlockState state) {
//        return true;
//    }
//    @Override
//    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
//        SlabType slabType = state.get(TYPE);
//        switch (slabType) {
//            case DOUBLE:
//                return VoxelShapes.fullCube();
//            case TOP:
//                return TOP_SHAPE;
//            default:
//                return BOTTOM_SHAPE;
//        }
//    }
//    @Override
//    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
//        if (!state.canPlaceAt(world, pos)) {
//            setToDirt(state, world, pos);
//        }
//
//    }
//    @Override
//    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
//        int i = state.get(MOISTURE);
//        if (!isWaterNearby(world, pos) && !world.hasRain(pos.up())) {
//            if (i > 0) {
//                world.setBlockState(pos, state.with(MOISTURE, i - 1), 2);
//            } else if (!hasCrop(world, pos)) {
//                setToDirt(state, world, pos);
//            }
//        } else if (i < 7) {
//            world.setBlockState(pos, state.with(MOISTURE, 7), 2);
//        }
//        int moisture = state.get(MOISTURE);
//        if (!hasWater(world, pos) && !world.hasRain(pos.up())) {
//            if (moisture > 0) {
//                world.setBlockState(pos, state.with(MOISTURE, moisture - 1), 2);
//            }
//        } else if (moisture < 7) {
//            world.setBlockState(pos, state.with(MOISTURE, 7), 2);
//        }
//    }
//
//    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
//        if (!world.isClient && world.random.nextFloat() < fallDistance - 0.5F && entity instanceof LivingEntity && (entity instanceof PlayerEntity || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) && entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F) {
//            setToDirt(state, world, pos);
//        }
//
//        super.onLandedUpon(world, state, pos, entity, fallDistance);
//    }
//
//    public static void setToDirt(BlockState state, World world, BlockPos pos) {
//        world.setBlockState(pos, pushEntitiesUpBeforeBlockChange(state, TerraStairsBlocks.DIRT_SLAB.getDefaultState(), world, pos));
//    }
//
//    private static boolean hasCrop(BlockView world, BlockPos pos) {
//        Block block = world.getBlockState(pos.up()).getBlock();
//        return block instanceof CropBlock || block instanceof StemBlock || block instanceof AttachedStemBlock;
//    }
//
//    private static boolean isWaterNearby(WorldView world, BlockPos pos) {
//        Iterator<BlockPos> var2 = BlockPos.iterate(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();
//
//        BlockPos blockPos;
//        do {
//            if (!var2.hasNext()) {
//                return false;
//            }
//
//            blockPos = var2.next();
//        } while(!world.getFluidState(blockPos).isIn(FluidTags.WATER));
//
//        return true;
//    }
//
//    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
//        builder.add(MOISTURE);
//        builder.add(TYPE, WATERLOGGED);
//    }
//    @Override
//    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
//        return switch (type) {
//            case LAND, AIR -> false;
//            case WATER -> world.getFluidState(pos).isIn(FluidTags.WATER);
//        };
//    }
//
//    static {
//        MOISTURE = Properties.MOISTURE;
//        SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 7.0, 16.0);
//    }
//}

