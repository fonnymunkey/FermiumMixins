package fermiummixins.mixin.openterraingenerator;

import com.pg85.otg.forge.ForgeEngine;
import com.pg85.otg.forge.events.server.ServerTickHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerTickHandler.class)
public abstract class ServerTickHandler_PregeneratorMixin {

	@Redirect(
			method = "onServerTick",
			at = @At(value = "INVOKE", target = "Lcom/pg85/otg/forge/ForgeEngine;processPregeneratorTick()V"),
			remap = false
	)
	private void fermiummixins_openTerrainGeneratorServerTickHandler_onServerTick(ForgeEngine instance) {
		//noop
	}
}