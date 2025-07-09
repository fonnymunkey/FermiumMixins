package fermiummixins.mixin.quark.vanilla;

import com.llamalad7.mixinextras.sugar.Local;
import fermiummixins.util.ModLoadedUtil;
import fermiummixins.wrapper.QuarkWrapper;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LayerElytra.class)
public abstract class LayerElytra_RuneMixin {
	
	@Inject(
			method = "doRenderLayer",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/LayerArmorBase;renderEnchantedGlint(Lnet/minecraft/client/renderer/entity/RenderLivingBase;Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/client/model/ModelBase;FFFFFFF)V")
	)
	private void fermiummixins_vanillaLayerElytra_doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo ci, @Local ItemStack itemstack) {
		if(ModLoadedUtil.isQuarkLoaded()) QuarkWrapper.setColorRuneTargetStack(itemstack);
	}
}