package fermiummixins.mixin.waystones.vanilla;

import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Biome.class)
public interface IBiomeAccessor {
	@Accessor("biomeName")
	String getBiomeName();
}