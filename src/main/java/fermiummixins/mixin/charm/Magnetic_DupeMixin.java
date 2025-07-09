package fermiummixins.mixin.charm;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charm.enchanting.feature.Magnetic;

@Mixin(Magnetic.class)
public abstract class Magnetic_DupeMixin {

    @Inject(
            method = "onBreak",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_charmMagnetic_onBreak(BlockEvent.BreakEvent event, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(
            method = "onEntityCreate",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_charmMagnetic_onEntityCreate(EntityJoinWorldEvent event, CallbackInfo ci) {
        ci.cancel();
    }
}
