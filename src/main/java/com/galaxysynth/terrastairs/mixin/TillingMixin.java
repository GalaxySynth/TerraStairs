package com.galaxysynth.terrastairs.mixin;

import java.util.function.Consumer;

import com.galaxysynth.terrastairs.block.TerraStairsBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Mixin(HoeItem.class)
public class TillingMixin {
    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    private void useOnBlock(ItemUsageContext itemUsageContext, CallbackInfoReturnable<ActionResult> info) {
        World world = itemUsageContext.getWorld();
        BlockPos pos = itemUsageContext.getBlockPos();
        if (itemUsageContext.getSide() != Direction.DOWN && world.getBlockState(pos.up()).isAir()) {
            BlockState bs = world.getBlockState(pos);
            if (bs.getBlock() == TerraStairsBlocks.COARSE_DIRT_STAIRS) {
                PlayerEntity player = itemUsageContext.getPlayer();
                world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isClient) {
                    world.setBlockState(pos, TerraStairsBlocks.DIRT_STAIRS.getDefaultState()
                            .with(StairsBlock.FACING, bs.get(StairsBlock.FACING))
                            .with(StairsBlock.HALF, bs.get(StairsBlock.HALF))
                            .with(StairsBlock.SHAPE, bs.get(StairsBlock.SHAPE))
                            .with(StairsBlock.WATERLOGGED, bs.get(StairsBlock.WATERLOGGED))
                    );
                    if (player != null) {
                        itemUsageContext.getStack().damage(1, player, (Consumer<LivingEntity>)((playerEntity_1x) -> {
                            (playerEntity_1x).sendToolBreakStatus(itemUsageContext.getHand());
                        }));
                    }
                }
                info.setReturnValue(ActionResult.SUCCESS);
            } else if (bs.getBlock() == TerraStairsBlocks.COARSE_DIRT_SLAB) {
                PlayerEntity player = itemUsageContext.getPlayer();
                world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                if (!world.isClient) {
                    world.setBlockState(pos, TerraStairsBlocks.DIRT_SLAB.getDefaultState()
                            .with(SlabBlock.TYPE, bs.get(SlabBlock.TYPE))
                            .with(SlabBlock.WATERLOGGED, bs.get(SlabBlock.WATERLOGGED))
                    );
                    if (player != null) {
                        itemUsageContext.getStack().damage(1, (LivingEntity)player, (Consumer<LivingEntity>)((playerEntity_1x) -> {
                            (playerEntity_1x).sendToolBreakStatus(itemUsageContext.getHand());
                        }));
                    }
                }
                info.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }
}