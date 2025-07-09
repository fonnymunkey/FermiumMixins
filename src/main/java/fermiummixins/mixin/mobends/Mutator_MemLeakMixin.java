package fermiummixins.mixin.mobends;

import goblinbob.mobends.core.data.EntityData;
import goblinbob.mobends.core.kumo.variable.KumoVariableRegistry;
import goblinbob.mobends.core.mutators.Mutator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fix thanks to Meldexun
 */
@Mixin(Mutator.class)
public abstract class Mutator_MemLeakMixin {

    @Redirect(
            method = "performAnimations",
            at = @At(value = "INVOKE", target = "Lgoblinbob/mobends/core/kumo/variable/KumoVariableRegistry;provideTemporaryData(Lgoblinbob/mobends/core/data/EntityData;)V"),
            remap = false
    )
    private void fermiummixins_mobendsMutator_performAnimations(KumoVariableRegistry instance, EntityData<?> tempData) {
        //noop
    }
}