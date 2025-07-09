package fermiummixins.mixin.lycanitesmobs.boundingbox;

import com.lycanitesmobs.core.entity.creature.EntityEpion;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityEpion.class)
public abstract class EntityEpion_RenderMixin {

    @Inject(
            method = "getRenderBoundingBox",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void fermiummixins_lycanitesMobsEntityEpion_getRenderBoundingBox(CallbackInfoReturnable<AxisAlignedBB> cir) {
        cir.setReturnValue(((EntityEpion)(Object)this).getEntityBoundingBox().grow(3.0, 3.0, 3.0).offset(0.0, 0.5, 0.0));
    }
}