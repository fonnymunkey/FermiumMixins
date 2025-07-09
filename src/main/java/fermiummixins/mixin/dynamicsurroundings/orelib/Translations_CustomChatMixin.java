package fermiummixins.mixin.dynamicsurroundings.orelib;

import fermiummixins.handlers.dynamicsurroundings.CustomEntityChatConfigHandler;
import fermiummixins.wrapper.DynamicSurroundingsWrapper;
import org.orecruncher.lib.Translations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(Translations.class)
public abstract class Translations_CustomChatMixin implements DynamicSurroundingsWrapper.ITranslations {

    @Shadow(remap = false)
    private Map<String, String> lookup;

    /**
     * Add load method to load from config instead of hardcoded lang file
     */
    @Override
    public void fermiummixins$loadFromConfig(String lang) {
        Map<String, String> temp = CustomEntityChatConfigHandler.getCustomEntityChatMap(lang);
        if(temp != null) this.lookup = temp;
    }
}