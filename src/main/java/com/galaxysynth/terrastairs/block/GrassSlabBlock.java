package com.galaxysynth.terrastairs.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class GrassSlabBlock extends SlabBlock {

    public GrassSlabBlock(Settings settings) {
        super(settings);
    }
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        SlabType slabType = state.get(TYPE);
        return switch (slabType) {
            case DOUBLE -> VoxelShapes.fullCube();
            case TOP -> TOP_SHAPE;
            default -> BOTTOM_SHAPE;
        };
    }
}
