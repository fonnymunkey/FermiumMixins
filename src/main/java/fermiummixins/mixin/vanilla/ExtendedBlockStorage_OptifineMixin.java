package fermiummixins.mixin.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.BlockStateContainer;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.common.property.IExtendedBlockState;
import org.spongepowered.asm.mixin.*;

@Mixin(ExtendedBlockStorage.class)
public abstract class ExtendedBlockStorage_OptifineMixin {
	
	@Shadow public abstract IBlockState get(int x, int y, int z);
	
	@Shadow private int blockRefCount;
	
	@Shadow private int tickRefCount;
	
	@Shadow @Final private BlockStateContainer data;
	
	/**
	 * @author fonnymunkey
	 * @reason overwrite optifine's nonsense reflection addition that causes lag
	 */
	@Overwrite
	public void set(int x, int y, int z, IBlockState state) {
		if(state instanceof IExtendedBlockState) state = ((IExtendedBlockState)state).getClean();
		IBlockState iblockstate = this.get(x, y, z);
		Block block = iblockstate.getBlock();
		Block block1 = state.getBlock();
		if(block != Blocks.AIR) {
			--this.blockRefCount;
			if(block.getTickRandomly()) {
				--this.tickRefCount;
			}
		}
		if(block1 != Blocks.AIR) {
			++this.blockRefCount;
			if(block1.getTickRandomly()) {
				++this.tickRefCount;
			}
		}
		this.data.set(x, y, z, state);
	}
}