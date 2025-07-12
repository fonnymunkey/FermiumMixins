package fermiummixins.mixin.distinctdamagedescriptions;

import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yeelp.distinctdamagedescriptions.handlers.DaylightTracker;

@Mixin(DaylightTracker.class)
public abstract class DaylightTracker_SkylightMixin {
	
	@Inject(
			method = "shouldStartTracking",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private void fermiummixins_distinctDamageDescriptionsDaylightTracker_shouldStartTracking(EntityLivingBase entity, CallbackInfoReturnable<Boolean> cir) {
		if(entity.ticksExisted%20 != 0) cir.setReturnValue(false);
	}
}