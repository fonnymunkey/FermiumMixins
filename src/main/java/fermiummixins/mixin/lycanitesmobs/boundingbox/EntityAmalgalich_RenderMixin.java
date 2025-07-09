package fermiummixins.mixin.lycanitesmobs.boundingbox;

import com.lycanitesmobs.core.entity.creature.EntityAmalgalich;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityAmalgalich.class)
public abstract class EntityAmalgalich_RenderMixin {

    @Inject(
            method = "getRenderBoundingBox",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void fermiummixins_lycanitesMobsEntityAmalgalich_getRenderBoundingBox(CallbackInfoReturnable<AxisAlignedBB> cir) {
        cir.setReturnValue(((EntityAmalgalich)(Object)this).getEntityBoundingBox().grow(5.0, 1.0, 5.0));
    }
}