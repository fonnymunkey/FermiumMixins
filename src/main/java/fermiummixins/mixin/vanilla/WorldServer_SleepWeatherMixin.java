package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import fermiummixins.wrapper.IWorldProvider;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldServer.class)
public abstract class WorldServer_SleepWeatherMixin {

    @Redirect(
            method = "wakeAllPlayers",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldServer;resetRainAndThunder()V")
    )
    private void fermiummixins_vanillaWorldServer_wakeAllPlayers(WorldServer instance) {
        if(ConfigHandler.VANILLA_CONFIG.stopSleepResettingWeatherConditionally) ((IWorldProvider)instance.provider).fermiummixins$resetWeatherConditionally();
    }
}