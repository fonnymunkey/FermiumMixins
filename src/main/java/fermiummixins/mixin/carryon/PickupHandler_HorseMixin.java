package fermiummixins.mixin.carryon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tschipp.carryon.common.handler.PickupHandler;

@Mixin(PickupHandler.class)
public abstract class PickupHandler_HorseMixin {
	
	@Inject(
			method = "canPlayerPickUpEntity",
			at = @At("HEAD"),
			cancellable = true,
			remap = false
	)
	private static void fermiummixins_carryOnPickupHandler_canPlayerPickupEntity_chestHorse(EntityPlayer player, Entity toPickUp, CallbackInfoReturnable<Boolean> cir) {
		if(toPickUp instanceof AbstractChestHorse && ((AbstractChestHorse)toPickUp).hasChest()) cir.setReturnValue(false);
	}
}