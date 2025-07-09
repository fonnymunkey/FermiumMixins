package fermiummixins.mixin.bloodmoon.vanilla;

import net.minecraft.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLiving.class)
public abstract class EntityLiving_LootMixin {

    @Inject(
            method = "canPickUpLoot",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fermiummixins_vanillaEntityLiving_canPickUpLoot(CallbackInfoReturnable<Boolean> cir) {
        if(((EntityLiving)(Object)this).getEntityData().getBoolean("bloodmoonSpawned")) {
            cir.setReturnValue(false);
        }
    }
}