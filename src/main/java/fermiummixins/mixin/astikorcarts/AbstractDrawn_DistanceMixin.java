package fermiummixins.mixin.astikorcarts;

import de.mennomax.astikorcarts.entity.AbstractDrawn;
import fermiummixins.handlers.ConfigHandler;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractDrawn.class)
public abstract class AbstractDrawn_DistanceMixin extends Entity {
	
	public AbstractDrawn_DistanceMixin(World worldIn) {
		super(worldIn);
	}
	
	@Redirect(
			method = "shouldRemovePulling",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isRiding()Z")
	)
	private boolean fermiummixins_astikorCartsAbstractDrawn_shouldRemovePulling(Entity instance) {
		double x = this.posX - instance.posX;
		double z = this.posZ - instance.posZ;
		return instance.isRiding() || (x * x + z * z) > ConfigHandler.ASTIKORCARTS_CONFIG.cartDistanceLimit * ConfigHandler.ASTIKORCARTS_CONFIG.cartDistanceLimit;
	}
}