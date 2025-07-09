package fermiummixins.mixin.switchbow;

import de.Whitedraco.switchbow.entity.arrow.EntityArrowLove;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fix by cdstk
 */
@Mixin(EntityArrowLove.class)
public abstract class EntityArrowLove_DupeMixin {

    @Redirect(
            method = "arrowHit",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z")
    )
    private boolean fermiummixins_switchbowEntityArrowLove_arrowHit(World instance, Entity entity) {
        return false;
    }
}