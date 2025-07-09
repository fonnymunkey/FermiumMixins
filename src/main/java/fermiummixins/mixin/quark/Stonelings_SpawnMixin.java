package fermiummixins.mixin.quark;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.quark.base.module.Feature;
import vazkii.quark.world.entity.EntityStoneling;
import vazkii.quark.world.feature.DepthMobs;
import vazkii.quark.world.feature.Stonelings;

/**
 * Fix by Kotlin
 */
@Mixin(Stonelings.class)
public abstract class Stonelings_SpawnMixin extends Feature {

    @Shadow(remap = false)
    public static int weight;

    /**
     * Fix issue with Stonelings being added to biomes containing zombies - before all biomes are registered
     */
    @Redirect(
            method = "preInit",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/registry/EntityRegistry;addSpawn(Ljava/lang/Class;IIILnet/minecraft/entity/EnumCreatureType;[Lnet/minecraft/world/biome/Biome;)V"),
            remap = false
    )
    private void fermiummixins_quarkStonelings_preInit(Class<? extends EntityLiving> entry, int spawns, int found, int biome, EnumCreatureType entityClass, Biome[] weightedProb) {
        //noop
    }

    @Override
    public void init() {
        EntityRegistry.addSpawn(EntityStoneling.class, weight, 1, 1, EnumCreatureType.MONSTER, DepthMobs.getBiomesWithMob(EntityZombie.class));
    }
}