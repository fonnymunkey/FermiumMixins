package fermiummixins.mixin.vanilla;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockFalling.class)
public abstract class BlockFalling_PerformanceMixin {
	
	@Shadow public static boolean fallInstantly;
	
	@Shadow protected abstract void onStartFalling(EntityFallingBlock fallingEntity);
	
	/**
	 * @author fonnymunkey
	 * @reason improve performance of fall checks
	 */
	@Overwrite
	private void checkFallable(World worldIn, BlockPos pos) {
		if(pos.getY() >= 0) {
			boolean fallSlow = !fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32));
			if(fallSlow && worldIn.isRemote) return;
			
			BlockPos.MutableBlockPos mutPos = new BlockPos.MutableBlockPos(pos);
			IBlockState downState = worldIn.getBlockState(mutPos.setPos(mutPos.getX(), mutPos.getY() - 1, mutPos.getZ()));
			if(BlockFalling.canFallThrough(downState) || downState.getBlock().isAir(downState, worldIn, mutPos)) {
				if(fallSlow) {
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
					this.onStartFalling(entityfallingblock);
					worldIn.spawnEntity(entityfallingblock);
				}
				else {
					IBlockState state = worldIn.getBlockState(pos);
					worldIn.setBlockToAir(pos);
					
					for(; mutPos.getY() > 0 && (BlockFalling.canFallThrough(downState) || downState.getBlock().isAir(downState, worldIn, mutPos)); downState = worldIn.getBlockState(mutPos.setPos(mutPos.getX(), mutPos.getY() - 1, mutPos.getZ()))) {
						;
					}
					
					if(mutPos.getY() > 0) {
						worldIn.setBlockState(mutPos.setPos(mutPos.getX(), mutPos.getY() + 1, mutPos.getZ()).toImmutable(), state);
					}
				}
			}
		}
	}
}