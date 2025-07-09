package fermiummixins.mixin.quark;

import net.minecraftforge.event.entity.living.LivingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.tweaks.feature.DoubleDoors;

@Mixin(DoubleDoors.class)
public abstract class DoubleDoors_VillagerAIMixin {

    @Inject(
            method = "onEntityTick",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_quarkDoubleDoors_onEntityTick(LivingEvent.LivingUpdateEvent event, CallbackInfo ci) {
        if(event.getEntity().ticksExisted%100!=0) ci.cancel();
    }
}