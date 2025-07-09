package fermiummixins.mixin.openterraingenerator;

import com.pg85.otg.common.LocalMaterialData;
import com.pg85.otg.configuration.biome.settings.ReplacedBlocksMatrix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Fix by Kotlin
 */
@Mixin(ReplacedBlocksMatrix.class)
public abstract class ReplacedBlocksMatrix_WorldGenCrashMixin {

    /**
     * Fixes an ArrayIndexOutOfBoundsException when a Y-level of -1 is provided to the replaceBlock function.
     */
    @Inject(
            method = "replaceBlock",
            at = @At(value = "HEAD"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_openTerrainGeneratorReplacedBlocksMatrix_replaceBlock(int y, LocalMaterialData material, CallbackInfoReturnable<LocalMaterialData> cir) {
        if(y < 0) {
            cir.setReturnValue(material);
        }
    }
}
