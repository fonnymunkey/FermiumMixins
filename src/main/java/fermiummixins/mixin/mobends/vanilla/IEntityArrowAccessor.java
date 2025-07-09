package fermiummixins.mixin.mobends.vanilla;

import net.minecraft.entity.projectile.EntityArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityArrow.class)
public interface IEntityArrowAccessor {
    @Accessor("inGround")
    boolean getInGround();
}