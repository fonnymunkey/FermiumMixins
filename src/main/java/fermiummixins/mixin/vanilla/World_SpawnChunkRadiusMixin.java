package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class World_SpawnChunkRadiusMixin {

    @Inject(
            method = "isSpawnChunk",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fermiummixins_vanillaWorld_isSpawnChunk_inject(int x, int z, CallbackInfoReturnable<Boolean> cir) {
        if(ConfigHandler.VANILLA_CONFIG.spawnChunkRadius == -1) cir.setReturnValue(false);
    }

    @ModifyConstant(
            method = "isSpawnChunk",
            constant = @Constant(intValue = 128)
    )
    private int fermiummixins_vanillaWorld_isSpawnChunk_modify_0(int constant) {
        return ConfigHandler.VANILLA_CONFIG.spawnChunkRadius * 16;
    }

    @ModifyConstant(
            method = "isSpawnChunk",
            constant = @Constant(intValue = -128)
    )
    private int fermiummixins_vanillaWorld_isSpawnChunk_modify_1(int constant) {
        return ConfigHandler.VANILLA_CONFIG.spawnChunkRadius * -16;
    }
}