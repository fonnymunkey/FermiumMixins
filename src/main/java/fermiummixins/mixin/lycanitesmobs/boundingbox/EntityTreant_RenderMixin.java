package fermiummixins.mixin.lycanitesmobs.boundingbox;

import com.lycanitesmobs.core.entity.creature.EntityTreant;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityTreant.class)
public abstract class EntityTreant_RenderMixin {

    @Inject(
            method = "getRenderBoundingBox",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void fermiummixins_lycanitesMobsEntityTreant_getRenderBoundingBox(CallbackInfoReturnable<AxisAlignedBB> cir) {
        cir.setReturnValue(((EntityTreant)(Object)this).getEntityBoundingBox().grow(7.0, 5.0, 7.0).offset(0.0, 5.0, 0.0));
    }
}