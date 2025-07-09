package fermiummixins.mixin.forgottenitems;

import net.minecraftforge.fml.common.Loader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tschipp.forgottenitems.util.FIEvents;

@Mixin(FIEvents.class)
public abstract class FIEvents_RuneMixin {

    @Redirect(
            method = "onLootTableLoad",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/Loader;isModLoaded(Ljava/lang/String;)Z"),
            remap = false
    )
    private boolean fermiummixins_forgottenItemsFIEvents_onLootTableLoad(String modname) {
        return !Loader.isModLoaded(modname);
    }
}