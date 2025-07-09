package fermiummixins.mixin.vanilla;

import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityStray;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityStray.class)
public abstract class EntityStray_SpawningMixin extends AbstractSkeleton {

    public EntityStray_SpawningMixin(World worldIn) {
        super(worldIn);
    }

    @Inject(
            method = "getCanSpawnHere",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fermiummixins_vanillaEntityStray_getCanSpawnHere(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(super.getCanSpawnHere());
    }
}