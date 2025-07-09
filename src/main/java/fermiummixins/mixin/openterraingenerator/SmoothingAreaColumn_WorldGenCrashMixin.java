package fermiummixins.mixin.openterraingenerator;

import com.pg85.otg.common.LocalMaterialData;
import com.pg85.otg.customobjects.structures.bo4.smoothing.SmoothingAreaColumn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fix by Kotlin
 */
@Mixin(SmoothingAreaColumn.class)
public abstract class SmoothingAreaColumn_WorldGenCrashMixin {

    /**
     * Fixes a NPE that occurs during OTG generation.
     */
    @Redirect(
            method = "spawn",
            at = @At(value = "INVOKE", target = "Lcom/pg85/otg/common/LocalMaterialData;isAir()Z"),
            remap = false
    )
    private boolean fermiummixins_openTerrainGeneratorSmoothingAreaColumn_spawn(LocalMaterialData instance) {
        if(instance == null) return false;
        return instance.isAir();
    }
}
