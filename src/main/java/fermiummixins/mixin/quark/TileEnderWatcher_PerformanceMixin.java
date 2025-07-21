package fermiummixins.mixin.quark;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.automation.tile.TileEnderWatcher;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(TileEnderWatcher.class)
public abstract class TileEnderWatcher_PerformanceMixin {

    @Unique
    private int fermiummixins$tick = 0;

    @Inject(
            method = "update",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fermiummixins_quarkTileEnderWatcher_update(CallbackInfo ci) {
        if(ConfigHandler.QUARK_CONFIG.enderWatchingTickFrequency > 1) {
            this.fermiummixins$tick++;
            if(this.fermiummixins$tick%ConfigHandler.QUARK_CONFIG.enderWatchingTickFrequency == 0) {
                this.fermiummixins$tick = 0;
                return;
            }
            ci.cancel();
        }
    }

    @Redirect(
            method = "update",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getEntitiesWithinAABB(Ljava/lang/Class;Lnet/minecraft/util/math/AxisAlignedBB;)Ljava/util/List;")
    )
    private List<EntityPlayer> fermiummixins_quarkTileEnderWatcher_update_getPlayers(World instance, Class<? extends Entity> classEntity, AxisAlignedBB bb) {
        return instance.playerEntities.stream()
                .filter(p -> 3600 >= p.getDistanceSq(((TileEntity)(Object)this).getPos().getX(), ((TileEntity)(Object)this).getPos().getY(), ((TileEntity)(Object)this).getPos().getZ()))
                .collect(Collectors.toList());
    }
}