package fermiummixins.mixin.infernalmobs;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import net.minecraftforge.common.config.Configuration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InfernalMobsCore.class)
public abstract class InfernalMobsCore_ConfigMixin {
	
	@Redirect(
			method = "checkEntityClassAllowed",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;load()V"),
			remap = false
	)
	private void fermiummixins_infernalMobsInfernalMobsCore_checkEntityClassAllowed_load(Configuration instance) {
		//noop
	}
	
	@Redirect(
			method = "checkEntityClassAllowed",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;save()V"),
			remap = false
	)
	private void fermiummixins_infernalMobsInfernalMobsCore_checkEntityClassAllowed_save(Configuration instance) {
		//noop
	}
	
	@Redirect(
			method = "checkEntityClassForced",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;load()V"),
			remap = false
	)
	private void fermiummixins_infernalMobsInfernalMobsCore_checkEntityClassForced_load(Configuration instance) {
		//noop
	}
	
	@Redirect(
			method = "checkEntityClassForced",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;save()V"),
			remap = false
	)
	private void fermiummixins_infernalMobsInfernalMobsCore_checkEntityClassForced_save(Configuration instance) {
		//noop
	}
	
	@Redirect(
			method = "getMobClassMaxHealth",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;load()V"),
			remap = false
	)
	private void fermiummixins_infernalMobsInfernalMobsCore_getMobClassMaxHealth_load(Configuration instance) {
		//noop
	}
	
	@Redirect(
			method = "getMobClassMaxHealth",
			at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/config/Configuration;save()V"),
			remap = false
	)
	private void fermiummixins_infernalMobsInfernalMobsCore_getMobClassMaxHealth_save(Configuration instance) {
		//noop
	}
}