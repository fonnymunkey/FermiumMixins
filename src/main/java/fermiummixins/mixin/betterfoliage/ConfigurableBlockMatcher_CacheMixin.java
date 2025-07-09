package fermiummixins.mixin.betterfoliage;

import mods.octarinecore.common.config.ConfigurableBlockMatcher;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.ConcurrentHashMap;

@Mixin(ConfigurableBlockMatcher.class)
public abstract class ConfigurableBlockMatcher_CacheMixin {
	
	@Unique
	private final ConcurrentHashMap<Class<?>, Boolean> fermiummixins$matchesMap = new ConcurrentHashMap<>();
	
	@Inject(
			method = "matchesClass",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private void fermiummixins_betterFoliageConfigurableBlockMatcher_matchesClass_inject0(Block block, CallbackInfoReturnable<Boolean> cir) {
		Boolean returnable = this.fermiummixins$matchesMap.get(block.getClass());
		if(returnable != null) {
			cir.setReturnValue(returnable);
		}
	}
	
	@Inject(
			method = "matchesClass",
			at = @At("RETURN"),
			remap = false
	)
	private void fermiummixins_betterFoliageConfigurableBlockMatcher_matchesClass_inject1(Block block, CallbackInfoReturnable<Boolean> cir) {
		this.fermiummixins$matchesMap.putIfAbsent(block.getClass(), cir.getReturnValue());
	}
}