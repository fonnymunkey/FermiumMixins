package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Biome.class)
public abstract class Biome_MaterialMixin {
	
	@Unique
	private static IBlockState fermiummixins$defState = IChunkPrimerAccessor.getDefaultState();
	
	//Gross but still technically a little  faster than getMaterial (interface overhead nonsense)
	@WrapOperation(
			method = "generateBiomeTerrain",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getMaterial()Lnet/minecraft/block/material/Material;")
	)
	private Material fermiumMixins_vanillaBiome_generateBiomeTerrain(IBlockState state, Operation<Material> operation) {
		if(state == fermiummixins$defState) return Material.AIR;
		return Material.GROUND;
	}
}