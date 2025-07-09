package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InventoryEffectRenderer.class)
public abstract class InventoryEffectRenderer_AmplifierMixin {
    
    @ModifyExpressionValue(
            method = "drawActivePotionEffects",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/I18n;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 0)
    )
    private String fermiummixins_vanillaInventoryEffectRenderer_drawActivePotionEffects(String original, @Local PotionEffect effect) {
        int amplifier = effect.getAmplifier();
        if(amplifier > 3 && amplifier < 10) original += " " + I18n.format("enchantment.level." + (amplifier + 1));
        else if(amplifier >= 10 && amplifier < 128) original += " " + (amplifier + 1);
        return original;
    }
}