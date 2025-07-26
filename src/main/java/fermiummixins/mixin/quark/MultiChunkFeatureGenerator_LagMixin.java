package fermiummixins.mixin.quark;

import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.quark.world.world.MultiChunkFeatureGenerator;

import java.util.function.Consumer;

@Mixin(MultiChunkFeatureGenerator.class)
public abstract class MultiChunkFeatureGenerator_LagMixin {
	
	@Shadow(remap = false) public abstract boolean shouldOffset();
	
	/**
	 * @author fonnymunkey
	 * @reason make iteration use mutable for performance
	 */
	@Overwrite(remap = false)
	public void forEachChunkBlock(int chunkX, int chunkZ, int minY, int maxY, Consumer<BlockPos> func) {
		BlockPos.MutableBlockPos first = new BlockPos.MutableBlockPos(chunkX * 16, 0, chunkZ * 16);
		minY = Math.max(1, minY);
		maxY = Math.min(255, maxY);
		if(this.shouldOffset()) {
			first.setPos(first.getX() + 8, first.getY(), first.getZ() + 8);
		}
		
		int startX = first.getX();
		int startY = first.getY();
		int startZ = first.getZ();
		
		for(int x = 0; x < 16; ++x) {
			for(int y = minY; y < maxY; ++y) {
				for(int z = 0; z < 16; ++z) {
					func.accept(first.setPos(startX + x, startY + y, startZ + z));
				}
			}
		}
	}
}