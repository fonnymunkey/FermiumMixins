package fermiummixins.mixin.vanilla;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldClient.class)
public abstract class WorldClient_VisibilityCacheMixin {

    @Shadow @Final private Minecraft mc;

    @Unique
    private int fermiummixins$prevJ = Integer.MAX_VALUE;
    @Unique
    private int fermiummixins$prevK = Integer.MAX_VALUE;

    @Inject(
            method = "refreshVisibleChunks",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fermiummixins_vanillaWorldClient_refreshVisibleChunks(CallbackInfo ci) {
        int j = MathHelper.floor(this.mc.player.posX / 16.0D);
        int k = MathHelper.floor(this.mc.player.posZ / 16.0D);
        if(fermiummixins$prevJ == j && fermiummixins$prevK == k) ci.cancel();
        else {
            fermiummixins$prevJ = j;
            fermiummixins$prevK = k;
        }
    }
}