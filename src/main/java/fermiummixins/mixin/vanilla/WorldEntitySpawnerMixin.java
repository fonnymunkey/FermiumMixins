package fermiummixins.mixin.vanilla;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldEntitySpawner.class)
public class WorldEntitySpawnerMixin {

    /* Explanation
        For whatever reason for every spawn attempt forge does the PotentialSpawns event twice
        First it chooses a random one of the available spawn entries in
            biome$spawnlistentry = worldServerIn.getSpawnListEntryForTypeAt(enumcreaturetype, blockpos$mutableblockpos);
        Then right afterward it checks if that entry is still in the available spawnentry list in
            if(worldServerIn.canCreatureTypeSpawnHere(enumcreaturetype, biome$spawnlistentry, blockpos$mutableblockpos) && ...

        Which does the exact same logic again of getting all available spawns using the PotentialSpawns forge event.
        The only valid reason why it would be different is if an event handler would add randomisation in its PotentialSpawns subscriber
        But you would only do that to reduce spawns of a specific spawn entry (since it would then just fail)
        And for reducing chances for a specific spawn entry you can just change spawn weights...
        So i really don't get why this is done that way
        I traced it back to like forge for mc 1.6 where that event was added for changing what vanilla structures spawn what mobs but idk man, i really don't see the point

        The reason -I- want to fix this is that i have a somewhat heavy PotentialSpawns handler that really doesn't need to be called twice
     */

    @WrapOperation(
            method = "findChunksForSpawning",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldServer;canCreatureTypeSpawnHere(Lnet/minecraft/entity/EnumCreatureType;Lnet/minecraft/world/biome/Biome$SpawnListEntry;Lnet/minecraft/util/math/BlockPos;)Z")
    )
    private boolean fermiumMixins_vanillaWorldEntitySpawner_findChunksForSpawning(WorldServer instance, EnumCreatureType creatureType, Biome.SpawnListEntry spawnListEntry, BlockPos pos, Operation<Boolean> original){
        return true;
    }
}
