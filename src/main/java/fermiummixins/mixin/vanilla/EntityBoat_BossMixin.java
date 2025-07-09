package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import fermiummixins.util.ModLoadedUtil;
import fermiummixins.wrapper.ChampionsWrapper;
import fermiummixins.wrapper.InfernalMobsWrapper;
import fermiummixins.wrapper.ScalingHealthWrapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.IMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityBoat.class)
public abstract class EntityBoat_BossMixin {

    @Redirect(
            method = "onUpdate",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;startRiding(Lnet/minecraft/entity/Entity;)Z")
    )
    private boolean fermiummixins_vanillaEntityBoat_onUpdate(Entity instance, Entity entityIn) {
        if(!instance.isNonBoss()) {
            entityIn.applyEntityCollision(instance);
            return false;
        }
        else if(instance instanceof IMob && ConfigHandler.VANILLA_CONFIG.allMobVehiclePrevention) {
            entityIn.applyEntityCollision(instance);
            return false;
        }
        else if(instance instanceof EntityLivingBase) {
            if((ModLoadedUtil.isScalingHealthLoaded() && ScalingHealthWrapper.isEntityBlight((EntityLivingBase)instance)) ||
                    (ModLoadedUtil.isInfernalMobsLoaded() && InfernalMobsWrapper.isEntityInfernal((EntityLivingBase)instance)) ||
                    (ModLoadedUtil.isChampionsLoaded() && instance instanceof EntityLiving && ChampionsWrapper.isEntityChampion((EntityLiving)instance))) {
                entityIn.applyEntityCollision(instance);
                return false;
            }
        }
        return instance.startRiding(entityIn);
    }
}