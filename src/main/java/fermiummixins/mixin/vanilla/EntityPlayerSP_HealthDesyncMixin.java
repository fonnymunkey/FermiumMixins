package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSP_HealthDesyncMixin extends EntityLivingBase {

    public EntityPlayerSP_HealthDesyncMixin(World worldIn) {
        super(worldIn);
    }

    /**
     * Recheck getHealth, since it can unexpectedly be capped lower after the setHealth method due to attribute changes and packet shenanigans
     * Hopefully fixes desync issue with death on clientside but not serverside
     */
    @Redirect(
            method = "setPlayerSPHealth",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;damageEntity(Lnet/minecraft/util/DamageSource;F)V")
    )
    private void fermiummixins_vanillaEntityPlayerSP_setPlayerSPHealth_redirect(EntityPlayerSP instance, DamageSource damageSrc, float damageAmount, @Local(argsOnly = true) float health) {
        float fNew = Math.max(this.getHealth() - health, 0.1F);
        this.lastDamage = fNew;
        this.damageEntity(DamageSource.GENERIC, fNew);
    }
}