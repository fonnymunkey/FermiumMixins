package fermiummixins.mixin.bettermineshafts;

import com.yungnickyoung.minecraft.bettermineshafts.world.MapGenBetterMineshaft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MapGenBetterMineshaft.class)
public abstract class MapGenBetterMineshaft_PerformanceMixin extends MapGenMineshaft {
	
	@Redirect(
			method = "canSpawnStructureAtCoords",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBiome(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/world/biome/Biome;")
	)
	private Biome fermiummixins_betterMineshaftsMapGenBetterMineshaft_canSpawnStructureAtCoords_perf(World instance, BlockPos pos) {
		return instance.getBiomeProvider().getBiome(pos);
	}
}