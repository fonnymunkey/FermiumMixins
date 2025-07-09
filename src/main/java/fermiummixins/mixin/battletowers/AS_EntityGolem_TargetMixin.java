package fermiummixins.mixin.battletowers;

import atomicstryker.battletowers.common.AS_EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AS_EntityGolem.class)
public abstract class AS_EntityGolem_TargetMixin extends EntityMob {
	
	public AS_EntityGolem_TargetMixin(World worldIn) {
		super(worldIn);
	}
	
	@Inject(
			method = "setDormant",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/network/datasync/EntityDataManager;set(Lnet/minecraft/network/datasync/DataParameter;Ljava/lang/Object;)V")
	)
	private void fermiummixins_battletowersAS_EntityGolem_setDormant(CallbackInfo ci) {
		if(this.getAttackTarget() != null) this.setAttackTarget(null);
	}
	
	@Inject(
			method = "onUpdate",
			at = @At("HEAD")
	)
	private void fermiummixins_battletowersAS_EntityGolem_onUpdate(CallbackInfo ci) {
		if(this.getAttackTarget() != null && !this.getAttackTarget().isEntityAlive()) this.setAttackTarget(null);
	}
}