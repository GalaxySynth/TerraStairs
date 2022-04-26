package com.galaxysynth.terrastairs.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;

public class RootedDirtSlabBlock extends SlabBlock {
    public final Block baseBlock;

    protected RootedDirtSlabBlock(BlockState blockState, Settings settings) {
        super(settings);
        this.baseBlock = blockState.getBlock();
    }
}
