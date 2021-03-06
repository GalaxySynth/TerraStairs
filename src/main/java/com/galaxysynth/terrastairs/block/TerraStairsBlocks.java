package com.galaxysynth.terrastairs.block;

import com.galaxysynth.terrastairsinit;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.minecraft.block.Blocks.*;

public class TerraStairsBlocks {
    public static final Block DIRT_STAIRS = new DirtStairsBlock(Blocks.DIRT.getDefaultState(), net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copy(Blocks.DIRT).strength(0.5f));
    public static final Block DIRT_SLAB = new DirtSlabBlock(DIRT.getDefaultState(), AbstractBlock.Settings.copy(DIRT).strength(0.5f));
    public static final Block ROOTED_DIRT_STAIRS = new DirtStairsBlock(Blocks.ROOTED_DIRT.getDefaultState(), net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copy(Blocks.ROOTED_DIRT).strength(0.5f));
    public static final Block ROOTED_DIRT_SLAB = new DirtSlabBlock(ROOTED_DIRT.getDefaultState(), AbstractBlock.Settings.copy(ROOTED_DIRT).strength(0.5f));
    public static final Block COARSE_DIRT_STAIRS = new DirtStairsBlock(Blocks.COARSE_DIRT.getDefaultState(), net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copy(Blocks.COARSE_DIRT).strength(0.5f));
    public static final Block COARSE_DIRT_SLAB = new SlabBlock(net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copy(Blocks.COARSE_DIRT).strength(0.5f));
    public static final Block GRASS_STAIRS = new TerraStairsBlock(DIRT.getDefaultState(), AbstractBlock.Settings.of(Material.SOLID_ORGANIC).strength(0.6F).sounds(BlockSoundGroup.GRASS));
    public static final Block GRASS_SLAB = new GrassSlabBlock(net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copy(DIRT).strength(0.6f).sounds(BlockSoundGroup.GRASS));
    public static final Block DIRT_PATH_STAIRS = new DirtPathStairsBlock(Blocks.DIRT_PATH.getDefaultState(), net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copy(Blocks.DIRT_PATH).strength(0.5f));
    public static final Block DIRT_PATH_SLAB = new DirtPathSlabBlock(net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copy(Blocks.DIRT_PATH).strength(0.5f));
    public static final Block MYCELIUM_STAIRS = new TerraStairsBlock(MYCELIUM.getDefaultState(), AbstractBlock.Settings.copy(MYCELIUM).strength(0.5f));
    public static final Block MYCELIUM_SLAB = new GrassSlabBlock(net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copy(Blocks.MYCELIUM).strength(0.5f));
    public static final Block PODZOL_STAIRS = new DirtStairsBlock(Blocks.PODZOL.getDefaultState(), net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copy(Blocks.PODZOL).strength(0.5f));
    public static final Block PODZOL_SLAB = new SlabBlock(net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copy(Blocks.PODZOL).strength(0.5f));
   //public static final Block FARMLAND_SLAB = new SlabBlock(net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copyOf(FARMLAND).strength(0.5f));

    private static void registerBuildingBlock(Block block, String name) {
        Registry.register(Registry.BLOCK, new Identifier(terrastairsinit.MOD_ID, name), block);
        Registry.register(Registry.ITEM, new Identifier(terrastairsinit.MOD_ID, name), new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    }

    public static void register() {
        registerBuildingBlock(DIRT_STAIRS, "dirt_stairs");
        registerBuildingBlock(ROOTED_DIRT_STAIRS, "rooted_dirt_stairs");
        registerBuildingBlock(COARSE_DIRT_STAIRS, "coarse_dirt_stairs");
        registerBuildingBlock(GRASS_STAIRS, "grass_stairs");
        registerBuildingBlock(DIRT_PATH_STAIRS, "dirt_path_stairs");
        registerBuildingBlock(MYCELIUM_STAIRS, "mycelium_stairs");
        registerBuildingBlock(PODZOL_STAIRS, "podzol_stairs");

        registerBuildingBlock(DIRT_SLAB, "dirt_slab");
        registerBuildingBlock(ROOTED_DIRT_SLAB, "rooted_dirt_slab");
        registerBuildingBlock(COARSE_DIRT_SLAB, "coarse_dirt_slab");
        registerBuildingBlock(GRASS_SLAB, "grass_slab");
        registerBuildingBlock(DIRT_PATH_SLAB, "dirt_path_slab");
        registerBuildingBlock(MYCELIUM_SLAB, "mycelium_slab");
        registerBuildingBlock(PODZOL_SLAB, "podzol_slab");
     // registerBuildingBlock(FARMLAND_SLAB, "farmland_slab");
    }
}
