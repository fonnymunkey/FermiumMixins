package fermiummixins.mixin.bloodmoon;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import lumien.bloodmoon.server.BloodmoonSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.reflect.Constructor;
import java.util.Set;

/**
 * Fix by Nischhelm
 */
@Mixin(BloodmoonSpawner.class)
public abstract class BloodmoonSpawner_PerformanceMixin {

	@WrapWithCondition(
			method = "findChunksForSpawning",
			at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z"),
			remap = false
	)
	private boolean fermiummixins_bloodmoonBloodmoonSpawner_findChunksForSpawning_loaded(Set<?> instance, Object e, @Local(argsOnly = true) WorldServer worldServerIn) {
		ChunkPos chunkpos = (ChunkPos)e;
		return worldServerIn.getChunkProvider().chunkExists(chunkpos.x, chunkpos.z);
	}

	@WrapOperation(
			method = "findChunksForSpawning",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldServer;canBlockSeeSky(Lnet/minecraft/util/math/BlockPos;)Z")
	)
	private boolean fermiummixins_bloodmoonBloodmoonSpawner_findChunksForSpawning_sky(WorldServer instance, BlockPos blockPos, Operation<Boolean> original) {
		return instance.canSeeSky(blockPos);
	}

	@Redirect(
			method = "findChunksForSpawning",
			at = @At(value = "INVOKE", target = "Ljava/lang/Class;getConstructor([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;"),
			remap = false
	)
	private Constructor<?> fermiumMixins_bloodmoonBloodmoonSpawner_findChunksForSpawning_constructor(Class<?> instance, Class<?>[] parameterTypes) {
		return null;
	}

	@Redirect(
			method = "findChunksForSpawning",
			at = @At(value = "INVOKE", target = "Ljava/lang/reflect/Constructor;newInstance([Ljava/lang/Object;)Ljava/lang/Object;"),
			remap = false
	)
	private Object fermiummixins_bloodmoonBloodmoonSpawner_findChunksForSpawning_new(Constructor<?> instance, Object[] args, @Local Biome.SpawnListEntry entry) throws Exception {
		return entry.newInstance(((WorldServer)args[0]));
	}
}