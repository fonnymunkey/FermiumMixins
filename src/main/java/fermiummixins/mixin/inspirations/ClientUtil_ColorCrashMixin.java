package fermiummixins.mixin.inspirations;

import com.llamalad7.mixinextras.sugar.Local;
import knightminer.inspirations.library.ItemMetaKey;
import knightminer.inspirations.library.client.ClientUtil;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientUtil.class)
public abstract class ClientUtil_ColorCrashMixin {

    @Inject(
            method = "getStackColor(Lknightminer/inspirations/library/ItemMetaKey;)Ljava/lang/Integer;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;getFrameTextureData(I)[[I"),
            cancellable = true
    )
    private static void fermiummixins_inspirationsClientUtil_getStackColor(ItemMetaKey key, CallbackInfoReturnable<Integer> cir, @Local TextureAtlasSprite sprite) {
        if(sprite.getFrameCount() < 1) cir.setReturnValue(-1);
    }
}