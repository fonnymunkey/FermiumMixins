package fermiummixins.mixin.bountifulbaubles;

import cursedflames.bountifulbaubles.proxy.ClientProxy;
import fermiummixins.handlers.bountifulbaubles.flare.EntityFlareNonAlbedo;
import fermiummixins.handlers.bountifulbaubles.flare.RenderFlareNonAlbedo;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientProxy.class)
public abstract class ClientProxy_FlareMixin {
	
	@Redirect(
			method = "registerEntityRenderingHandlers",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/client/registry/RenderingRegistry;registerEntityRenderingHandler(Ljava/lang/Class;Lnet/minecraftforge/fml/client/registry/IRenderFactory;)V"),
			remap = false
	)
	private void fermiummixins_bountifulBaublesClientProxy_registerEntityRenderingHandlers(Class entityClass, IRenderFactory renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(EntityFlareNonAlbedo.class, RenderFlareNonAlbedo::new);
	}
}