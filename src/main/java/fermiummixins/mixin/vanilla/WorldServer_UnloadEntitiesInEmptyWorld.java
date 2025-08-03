package fermiummixins.mixin.vanilla;

import net.minecraft.entity.Entity;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

@Mixin(WorldServer.class)
public abstract class WorldServer_UnloadEntitiesInEmptyWorld extends World {
    protected WorldServer_UnloadEntitiesInEmptyWorld(ISaveHandler saveHandlerIn, WorldInfo info, WorldProvider providerIn, Profiler profilerIn, boolean client) {
        super(saveHandlerIn, info, providerIn, profilerIn, client);
    }

    //Copied from FoamFix (patch from 2017), modified to not create rare Concurrent Modification Exceptions
    @Inject(
            method = "updateEntities",
            at = @At(value = "RETURN", ordinal = 0)
    )
    public void fermiumMixins_vanillaWorldServer_updateEntities(CallbackInfo ci) {
        this.profiler.startSection("entities");
        this.profiler.endStartSection("remove");
        this.loadedEntityList.removeAll(this.unloadedEntityList);

        for (Entity entityToUnload : this.unloadedEntityList)
            if (entityToUnload.addedToChunk && this.isChunkLoaded(entityToUnload.chunkCoordX, entityToUnload.chunkCoordZ, true))
                this.getChunk(entityToUnload.chunkCoordX, entityToUnload.chunkCoordZ).removeEntity(entityToUnload);

        this.unloadedEntityList.forEach(this::onEntityRemoved);

        this.unloadedEntityList.clear();

        this.profiler.endStartSection("blockEntities");

        ((WorldAccessor) this).setProcessingLoadedTiles(true); //FML Move above remove to prevent CMEs

        List<TileEntity> tileEntitiesToBeRemoved = ((WorldAccessor) this).getTileEntitiesToBeRemoved();

        if (!tileEntitiesToBeRemoved.isEmpty()) {
            tileEntitiesToBeRemoved.forEach(TileEntity::onChunkUnload);

            // forge: faster "contains" makes this removal much more efficient
            Set<TileEntity> remove = Collections.newSetFromMap(new IdentityHashMap<>());
            remove.addAll(tileEntitiesToBeRemoved);

            this.tickableTileEntities.removeAll(remove);
            this.loadedTileEntityList.removeAll(remove);
            tileEntitiesToBeRemoved.clear();
        }

        this.profiler.endSection();
        this.profiler.endSection();
    }
}
