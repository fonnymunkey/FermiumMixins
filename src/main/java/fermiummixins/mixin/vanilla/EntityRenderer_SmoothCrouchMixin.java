package fermiummixins.mixin.vanilla;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class EntityRenderer_SmoothCrouchMixin {

    @Unique
    private float fermiummixins$lastEyeHeight;
    @Unique
    private float fermiummixins$eyeHeight;
    @Unique
    private float fermiummixins$partialTicks;

    /**
     * Smooth eye height when crouching, based on RandomPatches's method
     * https://github.com/TheRandomLabs/RandomPatches/blob/1.12/src/main/java/com/therandomlabs/randompatches/hook/client/EntityRendererHook.java
     */
    @Inject(
            method = "updateRenderer",
            at= @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/WorldClient;getLightBrightness(Lnet/minecraft/util/math/BlockPos;)F")
    )
    private void fermiummixins_vanillaEntityRenderer_updateRenderer(CallbackInfo ci) {
        Entity rve = Minecraft.getMinecraft().getRenderViewEntity();
        if(rve == null) return;
        if(this.fermiummixins$eyeHeight == 0) this.fermiummixins$eyeHeight = rve.getEyeHeight();
        this.fermiummixins$lastEyeHeight = this.fermiummixins$eyeHeight;
        this.fermiummixins$eyeHeight += (rve.getEyeHeight() - this.fermiummixins$eyeHeight)*0.5F;
    }

    @Inject(
            method = "orientCamera",
            at = @At("HEAD")
    )
    private void fermiummixins_vanillaEntityRenderer_orientCameraHead(float partialTicks, CallbackInfo ci) {
        this.fermiummixins$partialTicks = partialTicks;
    }

    @Redirect(
            method = "orientCamera",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getEyeHeight()F")
    )
    private float fermiummixins_vanillaEntityRenderer_orientCamera(Entity instance) {
        return (this.fermiummixins$lastEyeHeight + (this.fermiummixins$eyeHeight -this.fermiummixins$lastEyeHeight)*this.fermiummixins$partialTicks);
    }
}