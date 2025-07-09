package fermiummixins.mixin.vanilla;

import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fix by Nischhelm
 */
@Mixin(WorldEntitySpawner.class)
public abstract class WorldEntitySpawner_LazyChunksMixin {

    @Redirect(
            method = "findChunksForSpawning",
            at = @At(value="INVOKE",target = "Lnet/minecraft/world/WorldServer;isAnyPlayerWithinRangeAt(DDDD)Z")
    )
    private boolean fermiummixins_vanillaWorldEntitySpawner_findChunksForSpawning(WorldServer instance, double x, double y, double z, double range) {
        int x1 = MathHelper.floor(x);
        int z1 = MathHelper.floor(z);
        
        if(!((IWorldInvoker)instance).invokeIsAreaLoaded(x1 - 32, 0, z1 - 32, x1 + 32, 0, z1 + 32, true)) return true;
        
        return instance.isAnyPlayerWithinRangeAt(x, y, z, range);
    }
}