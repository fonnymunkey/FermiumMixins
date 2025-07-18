package fermiummixins.mixin.vanilla;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class Minecraft_GLErrorMixin {
	
	@Inject(
			method = "checkGLError",
			at = @At("HEAD"),
			cancellable = true
	)
	private void fermiummixins_vanillaMinecraft_checkGLError(String message, CallbackInfo ci) {
		ci.cancel();
	}
}