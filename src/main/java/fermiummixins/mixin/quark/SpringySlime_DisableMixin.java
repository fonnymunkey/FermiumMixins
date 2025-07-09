package fermiummixins.mixin.quark;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.tweaks.feature.SpringySlime;

@Mixin(SpringySlime.class)
public abstract class SpringySlime_DisableMixin {

    @Inject(
            method = "onEntityCollision",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void fermiummixins_quarkSpringySlime_onEntityCollision(Entity entity, double attemptedX, double attemptedY, double attemptedZ, double dX, double dY, double dZ, CallbackInfo ci) {
        ci.cancel();
    }
}