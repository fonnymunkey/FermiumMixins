package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * Fix by Nischhelm
 */
@Mixin(EntityXPOrb.class)
public abstract class EntityXPOrb_MergeMixin extends Entity {

	@Shadow public int xpValue;

	public EntityXPOrb_MergeMixin(World worldIn) {
		super(worldIn);
	}

	@Inject(
			method = "onUpdate",
			at = @At(value = "TAIL")
	)
	private void fermiummixins_vanillaEntityXPOrb_onUpdate(CallbackInfo ci) {
		if(this.world.isRemote) return;
		if(this.ticksExisted % 10 != 0) return;
		if(this.xpValue >= ConfigHandler.VANILLA_CONFIG.xpOrbMaxMergedValue) return;
		if(this.ticksExisted < ConfigHandler.VANILLA_CONFIG.xpOrbEarliestMergeTick) return;
		if(this.isDead) return;

		List<EntityXPOrb> orbs = this.world.getEntitiesWithinAABB(
				EntityXPOrb.class,
				new AxisAlignedBB(this.getPosition().add(-1, -1, -1), this.getPosition().add(1, 1, 1)),
				EntitySelectors.IS_ALIVE
		);

		int newSize = this.xpValue;
		for(EntityXPOrb orb : orbs) {
			if(((EntityXPOrb)(Object)this) != orb && orb.xpValue <= this.xpValue && orb.ticksExisted > 10) {
				newSize += orb.xpValue;
				orb.setDead();
				if(newSize >= ConfigHandler.VANILLA_CONFIG.xpOrbMaxMergedValue) break;
			}
		}
		
		if(newSize > this.xpValue) {
			this.setDead();
			
			EntityXPOrb newOrb = new EntityXPOrb(this.world, this.posX, this.posY, this.posZ, newSize);
			newOrb.motionX = this.motionX;
			newOrb.motionY = this.motionY;
			newOrb.motionZ = this.motionZ;
			newOrb.rotationYaw = this.rotationYaw;
			newOrb.rotationPitch = this.rotationPitch;
			
			this.world.spawnEntity(newOrb);
		}
	}
}