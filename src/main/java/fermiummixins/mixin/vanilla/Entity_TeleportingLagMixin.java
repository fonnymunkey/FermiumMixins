package fermiummixins.mixin.vanilla;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public abstract class Entity_TeleportingLagMixin {

    @Redirect(
            method = "move",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/AxisAlignedBB;expand(DDD)Lnet/minecraft/util/math/AxisAlignedBB;", ordinal = 0)
    )
    private AxisAlignedBB fermiummixins_vanillaEntity_move(AxisAlignedBB instance, double x, double y, double z) {
        if(x > 128.0 || x < -128.0 || z > 128.0 || z < -128.0) {
            return instance.expand(Math.min(128.0, Math.max(-128.00, x)), y, Math.min(128.0, Math.max(-128.00, z)));
        }
        return instance.expand(x, y, z);
    }
}