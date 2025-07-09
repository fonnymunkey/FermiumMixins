package fermiummixins.mixin.testdummy;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "boni.dummy.network.DamageMessage$MessageHandlerClient$1")
public abstract class DamageMessage_DisplayMixin {

    @ModifyConstant(
            method = "run",
            constant = @Constant(floatValue = 2.0F),
            remap = false
    )
    private static float fermiummixins_testDummyDamageMessage_run(float constant) {
        return 1.0F;
    }
}