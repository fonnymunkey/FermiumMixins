package fermiummixins.mixin.forgottenitems;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tschipp.forgottenitems.items.ItemCraftingRune;

@Mixin(ItemCraftingRune.class)
public abstract class ItemCraftingRune_RuneMixin {

    @Redirect(
            method = "getSubItems",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/Loader;isModLoaded(Ljava/lang/String;)Z", remap = false)
    )
    private boolean fermiummixins_forgottenItemsItemCraftingRune_getSubItems(String modname) {
        return true;
    }
}