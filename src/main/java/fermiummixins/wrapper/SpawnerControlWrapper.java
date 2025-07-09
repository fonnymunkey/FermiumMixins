package fermiummixins.wrapper;

import fermiummixins.handlers.ConfigHandler;
import ladysnake.spawnercontrol.SpawnerControl;
import ladysnake.spawnercontrol.controlledspawner.CapabilityControllableSpawner;
import ladysnake.spawnercontrol.controlledspawner.IControllableSpawner;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Fix by cdstk/Nischhelm
 */
public abstract class SpawnerControlWrapper {
    
    private static final String NBT_TAG_SPAWNER_POS = SpawnerControl.MOD_ID + ":spawnerPos";
    
    private static final Map<Integer, Map<Long, Integer>> brokenSpawnerMobCounter = new HashMap<>();

    /**
     * Based on https://github.com/Pyrofab/SpawnerControl/blob/master/src/main/java/ladysnake/spawnercontrol/SpawnerEventHandler.java#L181
     * Extracted out for compat
     */
    public static void increaseSpawnerCount(Entity entity) {
        if(entity == null || entity.world.isRemote) return;
        
        NBTTagCompound data = entity.getEntityData();
        if(data.hasKey(NBT_TAG_SPAWNER_POS)) {
            NBTBase nbt = entity.getEntityData().getTag(NBT_TAG_SPAWNER_POS);
            World world;
            long spawnerPos;
            if(nbt instanceof NBTTagCompound) {
                spawnerPos = ((NBTTagCompound)nbt).getLong("pos");
                world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(((NBTTagCompound)nbt).getInteger("dimension"));
            }
            else if(nbt instanceof NBTTagLong) {
                spawnerPos = ((NBTTagLong)nbt).getLong();
                world = entity.getEntityWorld();
            }
            else return;
            
            TileEntity tile = world.getTileEntity(BlockPos.fromLong(spawnerPos));
            if(tile instanceof TileEntityMobSpawner) {
                IControllableSpawner handler = CapabilityControllableSpawner.getHandler((TileEntityMobSpawner)tile);
                if(handler.getConfig().incrementOnMobDeath) {
                    if(handler.incrementSpawnedMobsCount()) {
                        SpawnerControlWrapper.registerBrokenSpawner(world.provider.getDimension(), spawnerPos);
                    }
                }
                
                //If spawner has been replaced after breaking, still increment old value
                if(SpawnerControlWrapper.isBrokenSpawnerRegistered(world.provider.getDimension(), spawnerPos)) {
                    incrementBrokenSpawner(world.provider.getDimension(), spawnerPos);
                }
            }
            else {
                incrementBrokenSpawner(world.provider.getDimension(), spawnerPos);
            }
        }
    }
    
    public static void registerBrokenSpawner(int dimId, long spawnerPos) {
        if(!ConfigHandler.SPAWNERCONTROL_CONFIG.spawnerFarmingFix) return;
		brokenSpawnerMobCounter.computeIfAbsent(dimId, k -> new HashMap<>());
        brokenSpawnerMobCounter.get(dimId).putIfAbsent(spawnerPos, 0);
    }
    
    private static boolean isBrokenSpawnerRegistered(int dimId, long spawnerPos) {
        if(brokenSpawnerMobCounter.get(dimId) != null) {
            return brokenSpawnerMobCounter.get(dimId).get(spawnerPos) != null;
        }
        return false;
    }
    
    private static void incrementBrokenSpawner(int dimId, long spawnerPos) {
        if(!ConfigHandler.SPAWNERCONTROL_CONFIG.spawnerFarmingFix) return;
		brokenSpawnerMobCounter.computeIfAbsent(dimId, k -> new HashMap<>());
        Map<Long, Integer> dimMap = brokenSpawnerMobCounter.get(dimId);
        Integer deadCount = dimMap.get(spawnerPos);
        if(deadCount == null) {
            //If the spawner is not registered but is still incremented, the world has reloaded since spawner break, so treat as past threshold
            deadCount = ConfigHandler.SPAWNERCONTROL_CONFIG.spawnerFarmingFixThreshold;
        }
        dimMap.put(spawnerPos, deadCount + 1);
    }
    
    public static boolean shouldCancelDrops(Entity entity) {
        if(entity == null || entity.world.isRemote) return false;
        if(!ConfigHandler.SPAWNERCONTROL_CONFIG.spawnerFarmingFix) return false;
        
        NBTTagCompound data = entity.getEntityData();
        if(data.hasKey(NBT_TAG_SPAWNER_POS)) {
            NBTBase nbt = entity.getEntityData().getTag(NBT_TAG_SPAWNER_POS);
            World world;
            long spawnerPos;
            if(nbt instanceof NBTTagCompound) {
                spawnerPos = ((NBTTagCompound)nbt).getLong("pos");
                world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(((NBTTagCompound)nbt).getInteger("dimension"));
            }
            else if(nbt instanceof NBTTagLong) {
                spawnerPos = ((NBTTagLong)nbt).getLong();
                world = entity.getEntityWorld();
            }
            else return true;
            
            Map<Long, Integer> dimMap = brokenSpawnerMobCounter.get(world.provider.getDimension());
            if(dimMap != null) {
                Integer deadCount = dimMap.get(spawnerPos);
                if(deadCount != null) {
                    return deadCount > ConfigHandler.SPAWNERCONTROL_CONFIG.spawnerFarmingFixThreshold;
                }
            }
        }
        return false;
    }
    
    public static void clearSpawnerMap(int dimension) {
        brokenSpawnerMobCounter.remove(dimension);
    }
}