package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(World.class)
public abstract class World_FlammablePosMixin {

    @Redirect(
            method = "isFlammableWithin",
            at = @At(value = "NEW", target = "(III)Lnet/minecraft/util/math/BlockPos;")
    )
    private BlockPos fermiummixins_vanillaWorld_isFlammableWithin_redirect(int x, int y, int z, @Local BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos) {
        return blockpos$pooledmutableblockpos;
    }
}