package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityTippedArrow.class)
public abstract class EntityTippedArrow_BlacklistMixin {

    @Redirect(
            method = "arrowHit",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;addPotionEffect(Lnet/minecraft/potion/PotionEffect;)V")
    )
    private void fermiummixins_vanillaEntityTippedArrow_arrowHit(EntityLivingBase instance, PotionEffect effect) {
        if(!ConfigHandler.VANILLA_CONFIG.getTippedArrowBlacklistedPotions().contains(effect.getPotion())) {
            instance.addPotionEffect(effect);
        }
    }
}