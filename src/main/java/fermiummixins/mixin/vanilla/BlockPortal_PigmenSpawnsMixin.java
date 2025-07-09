package fermiummixins.mixin.vanilla;

import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockPortal.class)
public abstract class BlockPortal_PigmenSpawnsMixin {

    @Inject(
            method = "updateTick",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void fermiummixins_vanillaBlockPortal_updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand, CallbackInfo ci) {
        ci.cancel();
    }
}