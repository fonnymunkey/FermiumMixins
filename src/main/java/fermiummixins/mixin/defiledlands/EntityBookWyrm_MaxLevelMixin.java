package fermiummixins.mixin.defiledlands;

import fermiummixins.handlers.ConfigHandler;
import lykrast.defiledlands.common.entity.passive.EntityBookWyrm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityBookWyrm.class)
public abstract class EntityBookWyrm_MaxLevelMixin {

	@Redirect(
			method = "setOffspringAttributes",
			at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"),
			remap = false
	)
	private int fermiummixins_defiledLandsEntityBookWyrm_setOffspringAttributes(int i, int j) {
		return Math.min(i, ConfigHandler.DEFILEDLANDS_CONFIG.bookWyrmMaxLevel);
	}
}