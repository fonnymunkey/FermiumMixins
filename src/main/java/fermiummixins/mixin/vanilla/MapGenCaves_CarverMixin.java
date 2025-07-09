package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.MapGenCaves;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapGenCaves.class)
public abstract class MapGenCaves_CarverMixin {
	
	@Inject(
			method = "canReplaceBlock",
			at = @At("RETURN"),
			cancellable = true
	)
	private void fermiummixins_vanillaMapGenCaves_canReplaceBlocks(IBlockState blockstate, IBlockState blockstateAbove, CallbackInfoReturnable<Boolean> cir) {
		if(!cir.getReturnValue()) {
			cir.setReturnValue(blockstateAbove.getMaterial() != Material.WATER && ConfigHandler.VANILLA_CONFIG.isBlockCarvable(blockstate.getBlock()));
		}
	}
}