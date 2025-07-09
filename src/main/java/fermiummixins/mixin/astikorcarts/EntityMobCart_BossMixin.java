package fermiummixins.mixin.astikorcarts;

import de.mennomax.astikorcarts.entity.AbstractDrawn;
import de.mennomax.astikorcarts.entity.EntityMobCart;
import fermiummixins.handlers.ConfigHandler;
import fermiummixins.util.ModLoadedUtil;
import fermiummixins.wrapper.ChampionsWrapper;
import fermiummixins.wrapper.InfernalMobsWrapper;
import fermiummixins.wrapper.ScalingHealthWrapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityMobCart.class)
public abstract class EntityMobCart_BossMixin extends AbstractDrawn {

    public EntityMobCart_BossMixin(World worldIn) {
        super(worldIn);
    }
    
    @Redirect(
            method = "applyEntityCollision",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;startRiding(Lnet/minecraft/entity/Entity;)Z")
    )
    private boolean fermiummixins_astikorCartsEntityMobCart_applyEntityCollision(Entity instance, Entity entityIn) {
        if(!instance.isNonBoss()) {
            super.applyEntityCollision(instance);
            return false;
        }
        else if(instance instanceof IMob && ConfigHandler.ASTIKORCARTS_CONFIG.allMobCartPrevention) {
            super.applyEntityCollision(instance);
            return false;
        }
        else if(instance instanceof EntityLivingBase) {
            if((ModLoadedUtil.isScalingHealthLoaded() && ScalingHealthWrapper.isEntityBlight((EntityLivingBase)instance)) ||
                    (ModLoadedUtil.isInfernalMobsLoaded() && InfernalMobsWrapper.isEntityInfernal((EntityLivingBase)instance)) ||
                    (ModLoadedUtil.isChampionsLoaded() && instance instanceof EntityLiving && ChampionsWrapper.isEntityChampion((EntityLiving)instance))) {
                super.applyEntityCollision(instance);
                return false;
            }
        }
        return instance.startRiding(entityIn);
    }
}