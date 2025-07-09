package fermiummixins.mixin.openterraingenerator;

import com.pg85.otg.forge.dimensions.OTGWorldProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldProviderSurface;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OTGWorldProvider.class)
public abstract class OTGWorldProvider_SpawnMixin extends WorldProviderSurface {
	
	@Override
	public BlockPos getRandomizedSpawnPoint() {
		return super.getRandomizedSpawnPoint();
	}
}