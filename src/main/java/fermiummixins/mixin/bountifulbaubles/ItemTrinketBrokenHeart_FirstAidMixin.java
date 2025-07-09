package fermiummixins.mixin.bountifulbaubles;

import cursedflames.bountifulbaubles.item.ItemTrinketBrokenHeart;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemTrinketBrokenHeart.class)
public abstract class ItemTrinketBrokenHeart_FirstAidMixin {

    @Inject(
            method = "onDamage",
            at = @At(value = "HEAD"),
            cancellable = true,
            remap = false
    )
    private static void fermiummixins_bountifulBaublesItemTrinketBrokenHeart_onDamageLivingDamage(LivingDamageEvent event, CallbackInfo ci) {
        ci.cancel();
    }
}