package fermiummixins.mixin.vanilla;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(World.class)
public interface WorldAccessor {
    @Accessor("processingLoadedTiles")
    void setProcessingLoadedTiles(boolean newValue);

    @Accessor("tileEntitiesToBeRemoved")
    List<TileEntity> getTileEntitiesToBeRemoved();
}
