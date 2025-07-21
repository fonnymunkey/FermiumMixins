package fermiummixins.mixin.quark;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vazkii.quark.world.tile.TileMonsterBox;

/**
 * Fix by Nischhelm
 */
@Mixin(TileMonsterBox.class)
public abstract class TileMonsterBox_PerformanceMixin extends TileEntity {

    @ModifyVariable(
            method = "update",
            name = "doBreak",
            at = @At(value = "LOAD", ordinal = 0)
    )
    private boolean fermiummixins_quarkTileMonsterBox_update_loadBefore(boolean doBreak, @Share("actuallyBreak") LocalBooleanRef actuallyBreak) {
        actuallyBreak.set(doBreak || this.world.isAnyPlayerWithinRangeAt(this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D, 3.0D));
        return true;
    }

    @ModifyVariable(
            method = "update",
            name = "doBreak",
            at = @At(value = "LOAD", ordinal = 1)
    )
    private boolean fermiummixins_quarkTileMonsterBox_update_loadAfter(boolean doBreak, @Share("actuallyBreak") LocalBooleanRef actuallyBreak) {
        return actuallyBreak.get();
    }
}