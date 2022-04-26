package com.galaxysynth.terrastairs.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class DirtStairsBlock extends StairsBlock {
    public final Block baseBlock;

    protected DirtStairsBlock(BlockState blockState, Settings settings) {
        super(blockState, settings);
        this.baseBlock = blockState.getBlock();
    }
}
