package fermiummixins.mixin.lycanitesmobs.boundingbox;

import com.lycanitesmobs.core.entity.creature.EntityRahovart;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRahovart.class)
public abstract class EntityRahovart_RenderMixin {

    @Inject(
            method = "getRenderBoundingBox",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void fermiummixins_lycanitesMobsEntityRahovart_getRenderBoundingBox(CallbackInfoReturnable<AxisAlignedBB> cir) {
        cir.setReturnValue(((EntityRahovart)(Object)this).getEntityBoundingBox().grow(7.5, 1.0, 7.5));
    }
}