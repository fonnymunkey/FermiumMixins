package fermiummixins.mixin.itemphysics;

import com.creativemd.itemphysic.physics.ServerPhysic;
import fermiummixins.mixin.itemphysics.vanilla.IEntityItemAccessor;
import net.minecraft.entity.item.EntityItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fix by Nischhelm
 */
@Mixin(ServerPhysic.class)
public abstract class ServerPhysic_CrashMixin {
	
	@Redirect(
			method = "onCollideWithPlayer(Lnet/minecraft/entity/item/EntityItem;Lnet/minecraft/entity/player/EntityPlayer;Z)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/item/EntityItem;getAge()I")
	)
	private static int fermiummixins_itemPhysicsServerPhysic_onCollideWithPlayer(EntityItem instance) {
		return ((IEntityItemAccessor)instance).getAgeFixed();
	}
}