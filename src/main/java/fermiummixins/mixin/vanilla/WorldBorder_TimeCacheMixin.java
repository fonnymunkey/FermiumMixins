package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.vanilla.TimeCacheHandler;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldBorder.class)
public abstract class WorldBorder_TimeCacheMixin {

    @Redirect(
            method = "getDiameter",
            at = @At(value = "INVOKE", target = "Ljava/lang/System;currentTimeMillis()J")
    )
    private long fermiummixins_vanillaWorldBorder_getDiameter() {
        return TimeCacheHandler.getCurrentTime();
    }
}