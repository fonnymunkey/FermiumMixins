package fermiummixins.mixin.mobends;

import fermiummixins.wrapper.IEntity;
import goblinbob.mobends.core.client.event.EntityRenderHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderHandler.class)
public abstract class EntityRenderHandler_IDMixin {
	
	@Inject(
			method = "beforeLivingRender",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private void fermiummixins_moBendsEntityRenderHandler_beforeLivingRender(RenderLivingEvent.Pre<? extends EntityLivingBase> event, CallbackInfo ci) {
		if(event.getEntity() != null && ((IEntity)event.getEntity()).fermiummixins$isFakeEntity()) ci.cancel();
	}
	
	@Inject(
			method = "afterLivingRender",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private void fermiummixins_moBendsEntityRenderHandler_afterLivingRender(RenderLivingEvent.Post<? extends EntityLivingBase> event, CallbackInfo ci) {
		if(event.getEntity() != null && ((IEntity)event.getEntity()).fermiummixins$isFakeEntity()) ci.cancel();
	}
}