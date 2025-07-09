package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(Biome.class)
public abstract class Biome_BedrockRangeMixin {
	
	@Redirect(
			method = "generateBiomeTerrain",
			at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0)
	)
	private int fermiummixins_vanillaBiome_generateBiomeTerrain_nextInt(Random instance, int i) {
		if(i == 5) return instance.nextInt(ConfigHandler.VANILLA_CONFIG.maximumBedrockGenerationRange);
		else return instance.nextInt(i);
	}
}