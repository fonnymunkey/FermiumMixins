package fermiummixins.mixin.vanilla;

import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityHusk.class)
public abstract class EntityHusk_SpawningMixin extends EntityZombie {

    public EntityHusk_SpawningMixin(World worldIn) {
        super(worldIn);
    }

    @Inject(
            method = "getCanSpawnHere",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fermiummixins_vanillaEntityHusk_getCanSpawnHere(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(super.getCanSpawnHere());
    }
}