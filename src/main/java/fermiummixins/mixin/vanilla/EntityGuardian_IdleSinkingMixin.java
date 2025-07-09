package fermiummixins.mixin.vanilla;

import net.minecraft.entity.monster.EntityGuardian;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * Fix by cdstk
 */
@Mixin(EntityGuardian.class)
public abstract class EntityGuardian_IdleSinkingMixin {

    @ModifyConstant(
            method = "travel",
            constant = @Constant(doubleValue = 0.005D)
    )
    private double fermiummixins_vanillaEntityGuardian_travel(double constant) {
        return 0.0D;
    }
}