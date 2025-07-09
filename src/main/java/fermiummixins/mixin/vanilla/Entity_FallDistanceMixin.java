package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Entity.class)
public abstract class Entity_FallDistanceMixin {

    @Shadow public float fallDistance;

    /**
     * Make water reducing fall distance configurable instead of forcing distance to 0
     */
    @ModifyConstant(
            method = "handleWaterMovement",
            constant = @Constant(floatValue = 0.0F)
    )
    private float fermiummixins_vanillaEntity_handleWaterMovement(float constant) {
        if(ConfigHandler.VANILLA_CONFIG.waterFallDamageReduction < 0.0F) return 0.0F;
        return Math.max(this.fallDistance - (float)ConfigHandler.VANILLA_CONFIG.waterFallDamageReduction, 0.0F);
    }
}