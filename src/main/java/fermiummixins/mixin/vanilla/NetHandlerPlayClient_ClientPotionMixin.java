package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.sugar.Local;
import fermiummixins.wrapper.IEntityLivingBase;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.SPacketEntityEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fix by Nischhelm
 */
@Mixin(NetHandlerPlayClient.class)
public abstract class NetHandlerPlayClient_ClientPotionMixin {
	
	@Shadow private WorldClient world;

	@Inject(
			method = "handleEntityEffect",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/potion/PotionEffect;setPotionDurationMax(Z)V")
	)
	private void fermiummixins_netHandlerPlayClient_handleEntityEffect(SPacketEntityEffect packetIn, CallbackInfo ci, @Local Entity entity) {
		if(this.world == null) return;
		if(entity instanceof EntityLivingBase) {
			((IEntityLivingBase)entity).fermiummixins$setIsFromPacket(true);
		}
	}
}