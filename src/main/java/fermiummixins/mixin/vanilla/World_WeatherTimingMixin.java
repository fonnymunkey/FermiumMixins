package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(World.class)
public abstract class World_WeatherTimingMixin {

    @ModifyConstant(
            method = "updateWeatherBody",
            constant = @Constant(intValue = 12000, ordinal = 0),
            remap = false
    )
    private int fermiummixins_vanillaWorld_updateWeatherBody_thunderActiveRange(int constant) {
        return ConfigHandler.VANILLA_CONFIG.thunderActiveRange;
    }

    @ModifyConstant(
            method = "updateWeatherBody",
            constant = @Constant(intValue = 3600),
            remap = false
    )
    private int fermiummixins_vanillaWorld_updateWeatherBody_thunderActiveMin(int constant) {
        return ConfigHandler.VANILLA_CONFIG.thunderActiveMinimum;
    }

    @ModifyConstant(
            method = "updateWeatherBody",
            constant = @Constant(intValue = 168000, ordinal = 0),
            remap = false
    )
    private int fermiummixins_vanillaWorld_updateWeatherBody_thunderInactiveRange(int constant) {
        return ConfigHandler.VANILLA_CONFIG.thunderInactiveRange;
    }

    @ModifyConstant(
            method = "updateWeatherBody",
            constant = @Constant(intValue = 12000, ordinal = 1),
            remap = false
    )
    private int fermiummixins_vanillaWorld_updateWeatherBody_thunderInactiveMin(int constant) {
        return ConfigHandler.VANILLA_CONFIG.thunderInactiveMinimum;
    }

    @ModifyConstant(
            method = "updateWeatherBody",
            constant = @Constant(intValue = 12000, ordinal = 2),
            remap = false
    )
    private int fermiummixins_vanillaWorld_updateWeatherBody_rainActiveRange(int constant) {
        return ConfigHandler.VANILLA_CONFIG.rainActiveRange;
    }

    @ModifyConstant(
            method = "updateWeatherBody",
            constant = @Constant(intValue = 12000, ordinal = 3),
            remap = false
    )
    private int fermiummixins_vanillaWorld_updateWeatherBody_rainActiveMin(int constant) {
        return ConfigHandler.VANILLA_CONFIG.rainActiveMinimum;
    }

    @ModifyConstant(
            method = "updateWeatherBody",
            constant = @Constant(intValue = 168000, ordinal = 1),
            remap = false
    )
    private int fermiummixins_vanillaWorld_updateWeatherBody_rainInactiveRange(int constant) {
        return ConfigHandler.VANILLA_CONFIG.rainInactiveRange;
    }

    @ModifyConstant(
            method = "updateWeatherBody",
            constant = @Constant(intValue = 12000, ordinal = 4),
            remap = false
    )
    private int fermiummixins_vanillaWorld_updateWeatherBody_rainInactiveMin(int constant) {
        return ConfigHandler.VANILLA_CONFIG.rainInactiveMinimum;
    }
}