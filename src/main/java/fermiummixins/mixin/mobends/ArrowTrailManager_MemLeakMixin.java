package fermiummixins.mixin.mobends;

import fermiummixins.mixin.mobends.vanilla.IEntityArrowAccessor;
import goblinbob.mobends.standard.client.renderer.entity.ArrowTrail;
import goblinbob.mobends.standard.client.renderer.entity.ArrowTrailManager;
import net.minecraft.entity.projectile.EntityArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ArrowTrailManager.class)
public abstract class ArrowTrailManager_MemLeakMixin {

    @Shadow(remap = false)
    public static ArrowTrail getOrMake(EntityArrow arrow) {
        return null;
    }

    /**
     * @author fonnymunkey
     * @reason fix arrows in ground or not added to world being added to trail rendering
     */
    @Overwrite(remap = false)
    public static void renderTrail(EntityArrow entity, double x, double y, double z, float partialTicks) {
        if(entity != null && !entity.isDead && !((IEntityArrowAccessor)entity).getInGround() && entity.isAddedToWorld()) {
            getOrMake(entity).render(x, y, z, partialTicks);
        }
    }
}