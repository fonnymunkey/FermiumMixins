package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapGenMineshaft.class)
public abstract class MapGenMineshaft_BiomeBlacklistMixin extends MapGenStructure {

    @Inject(
            method = "canSpawnStructureAtCoords",
            at = @At("RETURN"),
            cancellable = true
    )
    private void fermiummixins_vanillaMapGenMineshaft_canSpawnStructureAtCoords(int chunkX, int chunkZ, CallbackInfoReturnable<Boolean> cir) {
        if(cir.getReturnValue()) {
            Biome biome = ((IMapGenBaseAccessor)this).getWorld().getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8));
            
            if(ConfigHandler.VANILLA_CONFIG.isMineshaftsBlacklistedFromBiome(biome)) {
                cir.setReturnValue(false);
			}
        }
    }
}