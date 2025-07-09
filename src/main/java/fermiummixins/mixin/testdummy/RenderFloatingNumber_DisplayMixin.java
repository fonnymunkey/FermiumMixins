package fermiummixins.mixin.testdummy;

import boni.dummy.client.RenderFloatingNumber;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(RenderFloatingNumber.class)
public abstract class RenderFloatingNumber_DisplayMixin {

    @ModifyConstant(
            method = "doRender(Lboni/dummy/EntityFloatingNumber;DDDFF)V",
            constant = @Constant(floatValue = 2.0F),
            remap = false
    )
    private static float fermiummixins_testDummyRenderFloatingNumber_doRender(float constant) {
        return 1.0F;
    }
}