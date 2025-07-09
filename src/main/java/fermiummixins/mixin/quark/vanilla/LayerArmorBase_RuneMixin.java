package fermiummixins.mixin.quark.vanilla;

import fermiummixins.util.ModLoadedUtil;
import fermiummixins.wrapper.QuarkWrapper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LayerArmorBase.class)
public abstract class LayerArmorBase_RuneMixin {

    @Inject(
            method = "renderEnchantedGlint",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V", ordinal = 1, shift = At.Shift.AFTER)
    )
    private static void fermiummixins_vanillaLayerArmorBase_renderEnchantedGlint(RenderLivingBase<?> p_188364_0_, EntityLivingBase p_188364_1_, ModelBase model, float p_188364_3_, float p_188364_4_, float p_188364_5_, float p_188364_6_, float p_188364_7_, float p_188364_8_, float p_188364_9_, CallbackInfo ci) {
        if(ModLoadedUtil.isQuarkLoaded()) QuarkWrapper.applyRuneColor();
    }
}