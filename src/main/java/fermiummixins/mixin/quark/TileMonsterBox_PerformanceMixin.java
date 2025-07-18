package fermiummixins.mixin.quark;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vazkii.quark.world.tile.TileMonsterBox;

@Mixin(TileMonsterBox.class)
public abstract class TileMonsterBox_PerformanceMixin extends TileEntity {

    //This mixin just replaces the expensive
    // world.getEntitiesWithinAABB(EntityPlayer.class)
    // with world.isAnyPlayerInRangeAt()

    // method is called every tick per monster box so not good

    // Most performant fix I could find was just skipping the whole if(!doBreak){} with the following two @ModifyVariable (one before, one after)

    @ModifyVariable(
            method = "update",
            name = "doBreak",
            at = @At(value = "LOAD", ordinal = 0)
    )
    private boolean fermiumMixins_quarkMonsterBox_update_loadBefore(boolean doBreak, @Share("actuallyBreak") LocalBooleanRef actuallyBreak){
        actuallyBreak.set(doBreak || this.world.isAnyPlayerWithinRangeAt(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 2.5));
        return true;
    }

    @ModifyVariable(
            method = "update",
            name = "doBreak",
            at = @At(value = "LOAD", ordinal = 1)
    )
    private boolean fermiumMixins_quarkMonsterBox_update_loadAfter(boolean doBreak, @Share("actuallyBreak") LocalBooleanRef actuallyBreak){
        return actuallyBreak.get();
    }
}
