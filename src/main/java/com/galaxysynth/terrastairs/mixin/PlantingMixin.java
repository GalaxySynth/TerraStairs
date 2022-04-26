package com.galaxysynth.terrastairs.mixin;


import com.galaxysynth.terrastairs.block.TerraStairsBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public class PlantingMixin {
    @Inject(at = @At("TAIL"), method = "canPlantOnTop", cancellable = true)
    public void canPlantOnTop(BlockState bs, BlockView blockView, BlockPos pos, CallbackInfoReturnable<Boolean> info) {
        Block block = bs.getBlock();
        if (block == TerraStairsBlocks.DIRT_STAIRS || block == TerraStairsBlocks.COARSE_DIRT_STAIRS || block == TerraStairsBlocks.GRASS_STAIRS || block == TerraStairsBlocks.PODZOL_STAIRS) {
            if (bs.get(StairsBlock.HALF) == BlockHalf.TOP) {
                info.setReturnValue(true);
            }
        } else if (block == TerraStairsBlocks.DIRT_SLAB || block == TerraStairsBlocks.COARSE_DIRT_SLAB || block == TerraStairsBlocks.GRASS_SLAB || block == TerraStairsBlocks.PODZOL_SLAB) {
            if (bs.get(SlabBlock.TYPE) == SlabType.TOP || bs.get(SlabBlock.TYPE) == SlabType.DOUBLE) {
                info.setReturnValue(true);
            }
        }
    }
}