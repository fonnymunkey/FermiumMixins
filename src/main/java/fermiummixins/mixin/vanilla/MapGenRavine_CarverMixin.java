package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.MapGenRavine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MapGenRavine.class)
public abstract class MapGenRavine_CarverMixin {
	
	@Redirect(
			method = "digBlock",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getBlock()Lnet/minecraft/block/Block;", ordinal = 0)
	)
	private Block fermiummixins_vanillaMapGenRavines_digBlock(IBlockState instance) {
		Block block = instance.getBlock();
		return block == Blocks.STONE || ConfigHandler.VANILLA_CONFIG.isBlockCarvable(block) ? Blocks.STONE : block;
	}
}