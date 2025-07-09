package fermiummixins.mixin.quark;

import fermiummixins.handlers.quark.RightClickSignEditHandler;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.tweaks.feature.RightClickSignEdit;

/**
 * Fix by Ve6om
 */
@Mixin(RightClickSignEdit.class)
public abstract class RightClickSignEdit_SyncMixin {

    /**
     * Fix Quark configuration for Right Click Sign Edit.
     * This is done by syncing the configuration with the client using packets.
     */
    @Inject(
            method = "onInteract",
            at = @At(value = "HEAD"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_quarkRightClickSignEdit_onInteract(PlayerInteractEvent.RightClickBlock event, CallbackInfo ci) {
        if(!RightClickSignEditHandler.isEnabled) ci.cancel();
    }
}