package fermiummixins.mixin.lycanitesmobs;

import com.lycanitesmobs.core.entity.creature.EntityAsmodeus;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Fix by cdstk
 */
@Mixin(EntityAsmodeus.class)
public abstract class EntityAsmodeus_MinionMixin {

	@Redirect(
			method = "onLivingUpdate",
			at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;isRemote:Z", ordinal = 1)
	)
	private boolean fermiummixins_lycanitesMobsEntityAsmodeus_onLivingUpdate(World instance) {
		return !instance.isRemote;
	}
}