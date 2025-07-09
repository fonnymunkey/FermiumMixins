package fermiummixins.mixin.spawnercontrol;

import com.llamalad7.mixinextras.sugar.Local;
import fermiummixins.wrapper.SpawnerControlWrapper;
import ladysnake.spawnercontrol.SpawnerEventHandler;
import ladysnake.spawnercontrol.config.SpawnerConfig;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.BlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fix by cdstk/Nischhelm
 */
@Mixin(SpawnerEventHandler.class)
public abstract class SpawnerEventHandler_FarmingMixin {
	
	@Inject(
			method = "onLivingDeath",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private static void fermiummixins_spawnerControlSpawnerEventHandler_onLivingDeath(LivingDeathEvent event, CallbackInfo ci) {
		if(event.getEntityLiving() != null) {
			SpawnerControlWrapper.increaseSpawnerCount(event.getEntityLiving());
			if(SpawnerControlWrapper.shouldCancelDrops(event.getEntityLiving())) {
				event.getEntityLiving().getEntityData().setBoolean("fermiummixins:spawnerbroken", true);
			}
		}
		ci.cancel();
	}
	
	@Inject(
			method = "onBlockBreak",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/event/world/BlockEvent$BreakEvent;setExpToDrop(I)V"),
			remap = false
	)
	private static void fermiummixins_spawnerControlSpawnerEventHandler_onBlockBreak(BlockEvent.BreakEvent event, CallbackInfo ci, @Local SpawnerConfig cfg) {
		SpawnerControlWrapper.registerBrokenSpawner(event.getWorld().provider.getDimension(), event.getPos().toLong());
	}
}