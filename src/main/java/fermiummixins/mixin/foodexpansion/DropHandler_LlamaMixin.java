package fermiummixins.mixin.foodexpansion;

import lellson.foodexpansion.DropHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityLlama;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DropHandler.class)
public abstract class DropHandler_LlamaMixin {

    @Redirect(
            method = "onEntityDrop",
            at = @At(value = "INVOKE", target = "Llellson/foodexpansion/DropHandler$Drop;tryDrop(Lnet/minecraft/entity/EntityLivingBase;)V"),
            remap = false
    )
    private void fermiummixins_foodExpansionDropHandler_onEntityDrop(DropHandler.Drop instance, EntityLivingBase entity) {
        if(!(entity instanceof EntityLlama && instance.entityClass == AbstractHorse.class)) {
            instance.tryDrop(entity);
        }
    }
}