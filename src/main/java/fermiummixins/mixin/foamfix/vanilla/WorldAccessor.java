package fermiummixins.mixin.foamfix.vanilla;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(World.class)
public interface WorldAccessor {
    @Accessor("processingLoadedTiles")
    void setProcessingLoadedTiles(boolean newValue);
}
