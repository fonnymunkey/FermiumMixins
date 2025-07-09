package fermiummixins.mixin.firstaid;

import fermiummixins.handlers.ConfigHandler;
import ichttt.mods.firstaid.client.util.HealthRenderUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * Fix by Nischhelm
 */
@Mixin(HealthRenderUtils.class)
public abstract class HealthRenderUtils_HeartRenderMixin {
    
    @ModifyConstant(
            method = "drawAsString",
            constant = @Constant(intValue = 12),
            remap = false
    )
    private static int fermiummixins_firstAidHealthRenderUtils_drawAsString(int constant) {
        return ConfigHandler.FIRSTAID_CONFIG.drawHealthAsNumbersThreshold;
    }
}