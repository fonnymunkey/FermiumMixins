package fermiummixins.mixin.vanilla;

import fermiummixins.util.ModLoadedUtil;
import fermiummixins.wrapper.IEntityLivingBase;
import fermiummixins.wrapper.SetBonusWrapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fix by Nischhelm
 */
@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBase_ClientPotionMixin extends Entity implements IEntityLivingBase {
	
	public EntityLivingBase_ClientPotionMixin(World worldIn) {
		super(worldIn);
	}

	@Unique
	private boolean fermiummixins$isFromPacket = false;

	@Override
	@Unique
	public void fermiummixins$setIsFromPacket(boolean isFromPacket) {
		this.fermiummixins$isFromPacket = isFromPacket;
	}

	@Inject(
			method = "addPotionEffect",
			at = @At("HEAD"),
			cancellable = true
	)
	private void fermiummixins_vanillaEntityLivingBase_addPotionEffect(PotionEffect potioneffectIn, CallbackInfo ci) {
		if(!this.world.isRemote) return;
		if(this.fermiummixins$isFromPacket) {
			this.fermiummixins$setIsFromPacket(false);
			return;
		}
		//SetBonus does its own potion sync handling so ignore it
		if(ModLoadedUtil.isSetBonusLoaded() && SetBonusWrapper.isFantasticEffect(potioneffectIn)) return;
		ci.cancel();
	}
}