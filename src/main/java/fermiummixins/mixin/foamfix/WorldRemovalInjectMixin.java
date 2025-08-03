package fermiummixins.mixin.foamfix;

import fermiummixins.mixin.foamfix.vanilla.WorldAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.asie.foamfix.coremod.injections.WorldRemovalInject;

@Mixin(WorldRemovalInject.class)
public abstract class WorldRemovalInjectMixin {

    @Inject(
            method = "foamfix_removeUnloadedEntities",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V")
    )
    public void foamfix_removeUnloadedEntities(CallbackInfo ci) {
        //The patch by FoamFix was created before Forge added this line in World.updateEntities
        ((WorldAccessor) this).setProcessingLoadedTiles(true); //FML Move above remove to prevent CMEs
    }
}
