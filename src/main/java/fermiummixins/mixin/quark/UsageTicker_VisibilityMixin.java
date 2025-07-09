package fermiummixins.mixin.quark;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.client.feature.UsageTicker;

/**
 * Fix by Nischhelm
 */
@Mixin(UsageTicker.TickerElement.class)
public abstract class UsageTicker_VisibilityMixin {
    
    @Shadow(remap = false)
    public int liveTicks;

    @Inject(
            method = "tick",
            at = @At(value = "RETURN"),
            remap = false
    )
    private void fermiummixins_quarkUsageTickerTickerElement_tick(EntityPlayer player, CallbackInfo ci) {
        this.liveTicks = 50;
    }
}