package fermiummixins.mixin.vanilla;

import fermiummixins.handlers.ConfigHandler;
import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.HashMap;

@Mixin(Particle.class)
public abstract class Particle_CollisionMixin {

    @Unique
    private static final HashMap<Class<? extends Particle>, Boolean> fermiummixins$particleCollisionClassMap = new HashMap<>();

    @ModifyConstant(
            method = "<init>(Lnet/minecraft/world/World;DDD)V",
            constant = @Constant(intValue = 1)
    )
    private int fermiummixins_vanillaParticle_init(int constant) {
        Class<? extends Particle> clazz = ((Particle)(Object)(this)).getClass();
        Boolean returnable = fermiummixins$particleCollisionClassMap.get(clazz);
        if(returnable == null) {
            returnable = ConfigHandler.VANILLA_CONFIG.getParticleRetainCollisionClasses().contains(clazz.getName());
            fermiummixins$particleCollisionClassMap.put(clazz, returnable);
        }
        return returnable ? 1 : 0;
    }
}