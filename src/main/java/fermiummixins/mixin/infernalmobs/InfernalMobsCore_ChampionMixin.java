package fermiummixins.mixin.infernalmobs;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import fermiummixins.wrapper.ChampionsWrapper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InfernalMobsCore.class)
public abstract class InfernalMobsCore_ChampionMixin {
	
	@Inject(
			method = "processEntitySpawn",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private void fermiummixins_infernalMobsInfernalMobsCore_processEntitySpawn(EntityLivingBase entity, CallbackInfo ci) {
		if(entity instanceof EntityLiving && ChampionsWrapper.isEntityChampion((EntityLiving)entity)) ci.cancel();
	}
}