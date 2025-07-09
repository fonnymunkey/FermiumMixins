package fermiummixins.wrapper;

import fermiummixins.mixin.vanilla.IGameSettingsOptionsAccessor;
import net.minecraft.client.settings.GameSettings;

public abstract class GammaWrapper {

    public static void setMinGamma(float min) {
        try {
            ((IGameSettingsOptionsAccessor)(Object)GameSettings.Options.GAMMA).setValueMin(min);
        }
        catch(Exception ignored) {}
    }

    public static void setMaxGamma(float max) {
        GameSettings.Options.GAMMA.setValueMax(max);
    }
}