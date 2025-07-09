package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Biome.class)
public abstract class Biome_CustomFillerMixin {
	
	@Inject(
			method = "generateBiomeTerrain",
			at = @At("RETURN")
	)
	private void fermiummixins_vanillaBiome_generateBiomeTerrain_return(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal, CallbackInfo ci) {
		if(worldIn == null || worldIn.provider == null) return;
		IBlockState customFillerState = ConfigHandler.VANILLA_CONFIG.getDimensionFillerBlock(worldIn.provider.getDimension());
		if(customFillerState != null && customFillerState.getBlock() != Blocks.AIR) {
			int l = x & 15;
			int i1 = z & 15;
			for(int j1 = 255; j1 >= 0; --j1) {
				IBlockState iblockstate = chunkPrimerIn.getBlockState(i1, j1, l);
				if(iblockstate.getBlock() == Blocks.STONE) {
					chunkPrimerIn.setBlockState(i1, j1, l, customFillerState);
				}
			}
		}
	}
}