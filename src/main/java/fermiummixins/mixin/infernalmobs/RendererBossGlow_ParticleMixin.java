package fermiummixins.mixin.infernalmobs;

import atomicstryker.infernalmobs.client.RendererBossGlow;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RendererBossGlow.class)
public abstract class RendererBossGlow_ParticleMixin {

    @Inject(
            method = "onRenderWorldLast",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_infernalMobsRendererBossGlow_onRenderWorldLast(RenderWorldLastEvent event, CallbackInfo ci) {
        if(Minecraft.getMinecraft().isGamePaused()) ci.cancel();
    }
}
