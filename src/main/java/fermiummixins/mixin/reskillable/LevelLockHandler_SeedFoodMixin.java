package fermiummixins.mixin.reskillable;

import codersafterdark.reskillable.base.LevelLockHandler;
import net.minecraft.item.ItemSeedFood;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelLockHandler.class)
public abstract class LevelLockHandler_SeedFoodMixin {

    @Inject(
            method = "rightClickItem",
            at = @At(value = "HEAD"),
            cancellable = true,
            remap = false
    )
    private static void fermiummixins_reskillableLevelLockHandler_rightClickItem(PlayerInteractEvent.RightClickItem event, CallbackInfo ci) {
        if(event.getItemStack().getItem() instanceof ItemSeedFood) ci.cancel();
    }
}