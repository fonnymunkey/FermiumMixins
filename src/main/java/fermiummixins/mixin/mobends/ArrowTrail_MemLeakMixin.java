package fermiummixins.mixin.mobends;

import fermiummixins.mixin.mobends.vanilla.IEntityArrowAccessor;
import goblinbob.mobends.standard.client.renderer.entity.ArrowTrail;
import net.minecraft.entity.projectile.EntityArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArrowTrail.class)
public abstract class ArrowTrail_MemLeakMixin {

    @Shadow(remap = false) private EntityArrow trackedArrow;

    @Inject(
            method = "shouldBeRemoved",
            at = @At("RETURN"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_moBendsArrowTrail_shouldBeRemoved(CallbackInfoReturnable<Boolean> cir) {
        if(!cir.getReturnValue()) cir.setReturnValue(((IEntityArrowAccessor)this.trackedArrow).getInGround());
    }
}