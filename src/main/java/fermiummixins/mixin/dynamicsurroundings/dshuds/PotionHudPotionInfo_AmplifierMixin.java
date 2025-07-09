package fermiummixins.mixin.dynamicsurroundings.dshuds;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.orecruncher.lib.Localization;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "org.orecruncher.dshuds.hud.PotionHUD$PotionInfo")
public abstract class PotionHudPotionInfo_AmplifierMixin {

    @Unique
    private int fermiummixins$amplifier = 0;

    @Redirect(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/potion/Potion;shouldRenderInvText(Lnet/minecraft/potion/PotionEffect;)Z", remap = false),
            remap = false
    )
    private boolean fermiummixins_dshudsPotionHudPotionInfo_init_redirect0(Potion instance, PotionEffect effect) {
        this.fermiummixins$amplifier = effect.getAmplifier();
        return instance.shouldRenderInvText(effect);
    }

    @Redirect(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lorg/orecruncher/lib/Localization;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 0, remap = false),
            remap = false
    )
    private String fermiummixins_dshudsPotionHudPotionInfo_init_redirect1(String translateKey, Object[] parameters) {
        String text = Localization.format(translateKey);
        if(this.fermiummixins$amplifier > 3 && this.fermiummixins$amplifier < 10) text += " " + Localization.format("enchantment.level." + (this.fermiummixins$amplifier + 1));
        this.fermiummixins$amplifier = 0;
        return text;
    }
}