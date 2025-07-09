package fermiummixins.mixin.charm;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charm.world.block.BlockRunePortal;

/**
 * Fix by cdstk
 */
@Mixin(BlockRunePortal.class)
public abstract class BlockRunePortal_PlayerOnlyMixin {

    @Inject(
            method = "onEntityCollision",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fermiummixins_charmRunePortal_onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn, CallbackInfo ci) {
        if(!(entityIn instanceof EntityPlayer)) ci.cancel();
    }
}