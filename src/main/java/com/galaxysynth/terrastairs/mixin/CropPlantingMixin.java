//package com.galaxysynth.terrastairs.mixin;
//
//import com.galaxysynth.terrastairs.block.TerraStairsBlocks;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.CropBlock;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.BlockView;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(CropBlock.class)
//public class CropPlantingMixin {
//    @Inject(at = @At("TAIL"), method = "canPlantOnTop", cancellable = true)
//    private void cropBlockCanPlantOnTopOfFarmlandSlab(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
//        cir.setReturnValue(cir.getReturnValue() || floor.isOf(TerraStairsBlocks.FARMLAND_SLAB));
//    }
//}