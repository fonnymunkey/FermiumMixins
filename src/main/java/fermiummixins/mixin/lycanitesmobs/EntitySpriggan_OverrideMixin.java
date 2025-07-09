package fermiummixins.mixin.lycanitesmobs;

import com.lycanitesmobs.core.entity.creature.EntitySpriggan;
import fermiummixins.handlers.ConfigHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntitySpriggan.class)
public abstract class EntitySpriggan_OverrideMixin {

    @Shadow(remap = false)
    public int farmingRate;

    @Inject(
            method = "onLivingUpdate",
            at = @At("HEAD")
    )
    private void fermiummixins_lycanitesMobsEntitySpriggan_onLivingUpdate(CallbackInfo ci) {
        this.farmingRate = ConfigHandler.LYCANITESMOBS_CONFIG.sprigganGrowthRateOverrideValue;
    }
}