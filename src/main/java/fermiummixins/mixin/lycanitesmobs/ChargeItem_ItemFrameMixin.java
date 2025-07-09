package fermiummixins.mixin.lycanitesmobs;

import com.lycanitesmobs.core.item.ChargeItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChargeItem.class)
public abstract class ChargeItem_ItemFrameMixin {

    @Inject(
            method = "onItemRightClickOnEntity",
            at = @At("RETURN"),
            cancellable = true,
            remap = false
    )
    private void fermiummixins_lycanitesMobsChargeItem_onItemRightClickOnEntity(EntityPlayer player, Entity entity, ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        if(entity instanceof EntityItemFrame) cir.setReturnValue(false);
    }
}