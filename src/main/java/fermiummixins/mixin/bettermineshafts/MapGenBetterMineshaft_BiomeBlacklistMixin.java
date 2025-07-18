package fermiummixins.mixin.bettermineshafts;

import com.llamalad7.mixinextras.sugar.Local;
import com.yungnickyoung.minecraft.bettermineshafts.world.MapGenBetterMineshaft;
import fermiummixins.handlers.ConfigHandler;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapGenBetterMineshaft.class)
public abstract class MapGenBetterMineshaft_BiomeBlacklistMixin {
	
	@Inject(
			method = "canSpawnStructureAtCoords",
			at = @At("RETURN"),
			cancellable = true
	)
	private void fermiummixins_betterMineshaftsMapGenBetterMineshaft_canSpawnStructureAtCoords_blacklist(int chunkX, int chunkZ, CallbackInfoReturnable<Boolean> cir, @Local Biome biome) {
		if(cir.getReturnValue()) {
			if(ConfigHandler.BETTERMINESHAFTS_CONFIG.isMineshaftsBlacklistedFromBiome(biome)) {
				cir.setReturnValue(false);
			}
		}
	}
}