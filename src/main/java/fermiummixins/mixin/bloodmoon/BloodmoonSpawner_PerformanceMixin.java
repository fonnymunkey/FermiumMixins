package fermiummixins.mixin.bloodmoon;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import lumien.bloodmoon.server.BloodmoonSpawner;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.reflect.Constructor;
import java.util.Set;

@Mixin(BloodmoonSpawner.class)
public abstract class BloodmoonSpawner_PerformanceMixin {

	@WrapWithCondition(
			method = "findChunksForSpawning",
			at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z"),
			remap = false
	)
	private boolean fermiumMixins_bloodmoonSpawner_findChunksForSpawning(Set instance, Object e, @Local(argsOnly = true) WorldServer worldServerIn){
		ChunkPos chunkpos = (ChunkPos) e;
		//Don't attempt spawn checks in unloaded chunks otherwise sky checks will cause chunk loading
		return worldServerIn.getChunkProvider().chunkExists(chunkpos.x, chunkpos.z);
	}

	@WrapOperation(
			method = "findChunksForSpawning",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldServer;canBlockSeeSky(Lnet/minecraft/util/math/BlockPos;)Z")
	)
	private boolean fermiumMixins_bloodmoonSpawner_findChunksForSpawning(
			WorldServer instance, BlockPos blockPos, Operation<Boolean> original,
			@Local(name = "blockpos1") BlockPos blockpos1,
			@Local(name = "f") float f,
			@Local(name = "i3") int i3,
			@Local(name = "f1") float f1
	) {
		//The older @Overwrite here changed the order of the three conditions from
		// 		canBlockSeeSky, anyPlayersInRange, distanceSqToWorldSpawn
		// to
		// 		distanceSqToWorldSpawn, anyPlayersInRange, canBlockSeeSky
		// This new mixin only replaces canBlockSeeSky with canSeeSky because that is a somewhat cheap check and will fail often, so it should come first

		return instance.canSeeSky(blockPos);
	}

	//Remove reflection

	@Redirect(
			method = "findChunksForSpawning",
			at = @At(value = "INVOKE", target = "Ljava/lang/Class;getConstructor([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;"),
			remap = false
	)
	private Constructor fermiumMixins_bloodmoonSpawner_findChunksForSpawning(Class instance, Class<?>[] parameterTypes){
		return null;
	}

	@Redirect(
			method = "findChunksForSpawning",
			at = @At(value = "INVOKE", target = "Ljava/lang/reflect/Constructor;newInstance([Ljava/lang/Object;)Ljava/lang/Object;"),
			remap = false
	)
	private Object fermiumMixins_bloodmoonSpawner_findChunksForSpawning(Constructor instance, Object[] initargs){
		return null;
	}

	@ModifyVariable(
			method = "findChunksForSpawning",
			at = @At(value = "STORE"),
			name = "entityliving",
			remap = false
	)
	private EntityLiving fermiumMixins_bloodmoonSpawner_findChunksForSpawning(
			EntityLiving value,
			@Local Biome.SpawnListEntry entry,
			@Local(argsOnly = true) WorldServer world
	) throws Exception {
		return entry.newInstance(world);
	}
}