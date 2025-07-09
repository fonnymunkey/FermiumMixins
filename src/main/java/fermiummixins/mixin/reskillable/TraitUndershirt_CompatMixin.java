package fermiummixins.mixin.reskillable;

import codersafterdark.reskillable.skill.defense.TraitUndershirt;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TraitUndershirt.class)
public abstract class TraitUndershirt_CompatMixin {

    @Inject(
            method = "onHurt",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private void fermiummixins_reskillableTraitUndershirt_onHurt(LivingHurtEvent event, CallbackInfo ci) {
        ci.cancel();
    }
}