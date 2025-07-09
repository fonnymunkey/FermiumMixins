package fermiummixins.mixin.sereneseasons;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import sereneseasons.api.season.BiomeHooks;
import sereneseasons.api.season.Season;

import java.lang.reflect.Method;

@Mixin(BiomeHooks.class)
public abstract class BiomeHooks_PerformanceMixin {

    @Unique
    private static Method fermiummixins$worldTempMethod;
    
    @Unique
    private static Method fermiummixins$seasonTempMethod;

    /**
     * @author fonnymunkey
     * @reason rewrite for performance
     */
    @Overwrite(remap = false)
    public static float getFloatTemperature(World world, Biome biome, BlockPos pos) {
        try {
            if(fermiummixins$worldTempMethod == null) fermiummixins$worldTempMethod =
                    Class.forName("sereneseasons.season.SeasonASMHelper")
                    .getMethod("getFloatTemperature", World.class, Biome.class, BlockPos.class);
            return (float)fermiummixins$worldTempMethod.invoke(null, world, biome, pos);
        }
        catch(Exception var4) {
            throw new RuntimeException("An error occurred calling getFloatTemperature", var4);
        }
    }

    /**
     * @author fonnymunkey
     * @reason rewrite for performance
     */
    @Overwrite(remap = false)
    public static float getFloatTemperature(Season.SubSeason subSeason, Biome biome, BlockPos pos) {
        try {
            if(fermiummixins$seasonTempMethod == null) fermiummixins$seasonTempMethod =
                    Class.forName("sereneseasons.season.SeasonASMHelper")
                    .getMethod("getFloatTemperature", Season.SubSeason.class, Biome.class, BlockPos.class);
            return (float)fermiummixins$seasonTempMethod.invoke(null, subSeason, biome, pos);
        }
        catch(Exception var4) {
            throw new RuntimeException("An error occurred calling getFloatTemperature", var4);
        }
    }
}