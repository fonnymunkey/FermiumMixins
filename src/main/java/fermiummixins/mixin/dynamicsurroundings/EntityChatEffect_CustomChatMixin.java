package fermiummixins.mixin.dynamicsurroundings;

import fermiummixins.handlers.dynamicsurroundings.CustomEntityChatConfigHandler;
import fermiummixins.wrapper.DynamicSurroundingsWrapper;
import net.minecraft.client.Minecraft;
import org.orecruncher.dsurround.client.handlers.effects.EntityChatEffect;
import org.orecruncher.lib.Translations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;
import java.util.Map;

@Mixin(EntityChatEffect.class)
public abstract class EntityChatEffect_CustomChatMixin {

    @Shadow(remap = false)
    static void setTimers(@Nonnull String entity, int base, int random) { }

    /**
     * Make Dynamic Surroundings use a custom configuration file for Entity Chat entries so they can be customized.
     */
    @Redirect(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lorg/orecruncher/lib/Translations;load(Ljava/lang/String;[Ljava/lang/String;)V", remap = false)
    )
    private static void fermiummixins_dsurroundEntityChatEffect_clinit_redirect(Translations instance, String t, String[] assetName) {
        ((DynamicSurroundingsWrapper.ITranslations)(Object)instance).fermiummixins$loadFromConfig(Minecraft.getMinecraft().gameSettings.language);
    }

    /**
     * Add the ability to define chat timers for separate entities from config
     */
    @Inject(
            method = "<clinit>",
            at = @At("RETURN")
    )
    private static void fermiummixins_dsurroundEntityChatEffect_clinit_inject(CallbackInfo ci) {
        Map<String, int[]> temp = CustomEntityChatConfigHandler.getCustomEntityChatTimeMap();
        if(temp != null) {
            for(Map.Entry<String, int[]> ent : temp.entrySet()) {
                setTimers(ent.getKey(), ent.getValue()[0], ent.getValue()[1]);
            }
        }
    }
}