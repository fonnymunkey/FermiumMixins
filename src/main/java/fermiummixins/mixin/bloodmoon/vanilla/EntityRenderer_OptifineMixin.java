package fermiummixins.mixin.bloodmoon.vanilla;

import fermiummixins.wrapper.BloodmoonWrapper;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityRenderer.class)
public abstract class EntityRenderer_OptifineMixin {
	
	@Unique
	private int fermiummixins$i = 0;
	
	@Inject(
			method = "updateLightmap",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProvider;getLightBrightnessTable()[F", ordinal = 0),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	private void fermiummixins_vanillaEntityRenderer_updateLightmap_i(float partialTicks, CallbackInfo ci, World world, float f, float f1, int i) {
		this.fermiummixins$i = i;
	}
	
	@ModifyVariable(
			method = "updateLightmap",
			at = @At(value = "STORE", opcode = 54),
			index = 20
	)
	private int fermiummixins_vanillaEntityRenderer_updateLightmap_red(int original) {
		return BloodmoonWrapper.manipulateRed(this.fermiummixins$i, original);
	}
	
	@ModifyVariable(
			method = "updateLightmap",
			at = @At(value = "STORE", opcode = 54),
			index = 21
	)
	private int fermiummixins_vanillaEntityRenderer_updateLightmap_green(int original) {
		return BloodmoonWrapper.manipulateGreen(this.fermiummixins$i, original);
	}
	
	@ModifyVariable(
			method = "updateLightmap",
			at = @At(value = "STORE", opcode = 54),
			index = 22
	)
	private int fermiummixins_vanillaEntityRenderer_updateLightmap_blue(int original) {
		return BloodmoonWrapper.manipulateBlue(this.fermiummixins$i, original);
	}
}