package fermiummixins.mixin.lycanitesmobs.boundingbox;

import com.lycanitesmobs.core.entity.creature.EntityAsmodeus;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityAsmodeus.class)
public abstract class EntityAsmodeus_RenderMixin {

    @Inject(
            method = "getRenderBoundingBox",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void fermiummixins_lycanitesMobsEntityAsmodeus_getRenderBoundingBox(CallbackInfoReturnable<AxisAlignedBB> cir) {
        cir.setReturnValue(((EntityAsmodeus)(Object)this).getEntityBoundingBox().grow(7.0, 0.0, 7.0));
    }
}