package fermiummixins.mixin.battletowers;

import atomicstryker.battletowers.common.WorldGenHandler;
import net.minecraftforge.common.config.Configuration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldGenHandler.class)
public abstract class WorldGenHandler_ConfigMixin {
	
	@Redirect(
			method = "getIsChunkProviderAllowed",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;load()V"),
			remap = false
	)
	private void fermiummixins_battleTowersWorldGenHandler_getIsChunkProviderAllowed_load(Configuration instance) {
		//noop
	}
	
	@Redirect(
			method = "getIsChunkProviderAllowed",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;save()V"),
			remap = false
	)
	private void fermiummixins_battleTowersWorldGenHandler_getIsChunkProviderAllowed_save(Configuration instance) {
		//noop
	}
	
	@Redirect(
			method = "getIsBiomeAllowed",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;load()V"),
			remap = false
	)
	private void fermiummixins_battleTowersWorldGenHandler_getIsBiomeAllowed_load(Configuration instance) {
		//noop
	}
	
	@Redirect(
			method = "getIsBiomeAllowed",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;save()V"),
			remap = false
	)
	private void fermiummixins_battleTowersWorldGenHandler_getIsBiomeAllowed_save(Configuration instance) {
		//noop
	}
}