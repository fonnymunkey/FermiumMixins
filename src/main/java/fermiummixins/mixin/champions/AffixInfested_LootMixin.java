package fermiummixins.mixin.champions;

import c4.champions.common.affix.affix.AffixInfested;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AffixInfested.class)
public abstract class AffixInfested_LootMixin {
    
    @WrapOperation(
            method = "spawnParasites",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z")
    )
    private boolean fermiummixins_championsAffixInfested_spawnParasites(World world, Entity entity, Operation<Boolean> original) {
        entity.getEntityData().setBoolean("fermiummixins:summoned", true);
        return original.call(world, entity);
    }
}