package com.galaxysynth;

import com.galaxysynth.terrastairs.block.TerraStairsBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class terrastairsinit implements ModInitializer {

	public static final String MOD_ID = "terrastairs";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		LOGGER.info("Getting kinda stair-y...");
		TerraStairsBlocks.register();

		ColorProviderRegistry.BLOCK.register((state, world, pos, layer) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D), TerraStairsBlocks.GRASS_STAIRS, TerraStairsBlocks.GRASS_SLAB);

		ColorProviderRegistry.ITEM.register((stack, layer) -> GrassColors.getColor(0.5D, 1.0D), TerraStairsBlocks.GRASS_STAIRS, TerraStairsBlocks.GRASS_SLAB);
	}
}
