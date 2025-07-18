package fermiummixins.mixin.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChunkPrimer.class)
public interface IChunkPrimerAccessor {
	
	@Accessor("DEFAULT_STATE")
	static IBlockState getDefaultState() {
		return null;
	}
}