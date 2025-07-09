package fermiummixins.mixin.reskillable;

import codersafterdark.reskillable.skill.agility.TraitRoadWalk;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fix by cdstk
 */
@Mixin(TraitRoadWalk.class)
public abstract class TraitRoadWalk_ReworkMixin {

    @Inject(
            method = "onPlayerTick",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_reskillableTraitRoadWalk_onPlayerTick(TickEvent.PlayerTickEvent event, CallbackInfo ci) {
        if(event.player.onGround && event.player.moveForward > 0.0F && event.player.world.canSeeSky(event.player.getPosition())) {
            event.player.moveRelative(0.0F, 0.0F, 1.0F, 0.05F);
        }
        ci.cancel();
    }
}