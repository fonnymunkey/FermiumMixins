package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChunkCache.class)
public abstract class ChunkCache_PathfindingMixin {
	
	@WrapOperation(
			method = "<init>",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getChunk(II)Lnet/minecraft/world/chunk/Chunk;")
	)
	private Chunk fermiummixins_vanillaChunkCache_init(World world, int chunkX, int chunkZ, Operation<Chunk> original) {
		//Pathfinding is the only non-client chunk cache usage (Ignoring mods hopefully)
		if(world instanceof WorldServer) {
			//Could check for specifically generated instead and allow loading, but likely isn't useful compared to performance
			if(!((WorldServer)world).getChunkProvider().chunkExists(chunkX, chunkZ)) return null;
		}
		return original.call(world, chunkX, chunkZ);
	}
}