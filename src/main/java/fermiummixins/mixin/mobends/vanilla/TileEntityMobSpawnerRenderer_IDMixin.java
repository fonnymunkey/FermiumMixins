package fermiummixins.mixin.mobends.vanilla;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import fermiummixins.wrapper.IEntity;
import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TileEntityMobSpawnerRenderer.class)
public abstract class TileEntityMobSpawnerRenderer_IDMixin {
	
	@ModifyExpressionValue(
			method = "renderMob",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/MobSpawnerBaseLogic;getCachedEntity()Lnet/minecraft/entity/Entity;")
	)
	private static Entity fermiummixins_vanillaTileEntityMobSpawnerRenderer_renderMob(Entity original) {
		if(original != null) ((IEntity)original).fermiummixins$setFakeEntity(true);
		return original;
	}
}