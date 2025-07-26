package fermiummixins.mixin.quark;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import vazkii.quark.world.feature.RevampStoneGen;
import vazkii.quark.world.world.MultiChunkFeatureGenerator;
import vazkii.quark.world.world.StoneInfoBasedGenerator;

import java.util.Random;
import java.util.function.Supplier;

@Mixin(StoneInfoBasedGenerator.class)
public abstract class StoneInfoBasedGenerator_LagMixin extends MultiChunkFeatureGenerator {
	
	@Shadow(remap = false) @Final public Supplier<RevampStoneGen.StoneInfo> infoSupplier;
	
	@Shadow(remap = false) public abstract boolean canPlaceBlock(World world, BlockPos pos);
	
	@Shadow(remap = false) @Final private IBlockState state;
	
	/**
	 * @author fonnymunkey
	 * @reason fix check ordering and mutable for performance
	 */
	@Overwrite(remap = false)
	public void generateChunkPart(BlockPos src, Random random, int chunkX, int chunkZ, World world) {
		RevampStoneGen.StoneInfo info = this.infoSupplier.get();
		this.forEachChunkBlock(chunkX, chunkZ, info.lowerBound - info.clusterSize, info.upperBound + info.clusterSize, (pos) -> {
			if(pos.distanceSq(src) < (double)(info.clusterSize * info.clusterSize) && this.canPlaceBlock(world, pos)) {
				world.setBlockState(pos.toImmutable(), this.state, 0);
			}
		});
	}
}