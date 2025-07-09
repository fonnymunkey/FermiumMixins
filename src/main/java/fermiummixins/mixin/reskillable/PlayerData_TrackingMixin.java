package fermiummixins.mixin.reskillable;

import codersafterdark.reskillable.api.data.PlayerData;
import codersafterdark.reskillable.api.data.RequirementHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerData.class)
public abstract class PlayerData_TrackingMixin {

    @Inject(
            method = "matchStats",
            at = @At(value = "RETURN"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_reskillablePlayerData_matchStats(RequirementHolder holder, CallbackInfoReturnable<Boolean> cir) {
        if(!cir.getReturnValue() && ((IRequirementCacheInvoker)(((PlayerData)(Object)this).getRequirementCache())).invokeGetPlayer() == null) {
            cir.setReturnValue(true);
        }
    }
}