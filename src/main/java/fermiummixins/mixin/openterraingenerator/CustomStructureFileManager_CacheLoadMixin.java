package fermiummixins.mixin.openterraingenerator;

import com.pg85.otg.common.LocalWorld;
import com.pg85.otg.customobjects.structures.CustomStructure;
import com.pg85.otg.customobjects.structures.CustomStructureFileManager;
import com.pg85.otg.customobjects.structures.bo4.BO4CustomStructure;
import com.pg85.otg.customobjects.structures.bo4.CustomStructurePlaceHolder;
import com.pg85.otg.util.ChunkCoordinate;
import fermiummixins.FermiumMixins;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mixin(CustomStructureFileManager.class)
public abstract class CustomStructureFileManager_CacheLoadMixin {
	
	/**
	 * @author fonnymunkey
	 * @reason improve load times on larger maps
	 */
	@Overwrite(remap = false)
	private static void mergeRegionData(LocalWorld world, HashMap<CustomStructure,ArrayList<ChunkCoordinate>> result, HashMap<CustomStructure, ArrayList<ChunkCoordinate>> output) {
		for(Map.Entry<CustomStructure, ArrayList<ChunkCoordinate>> entryResult : result.entrySet()) {
			//Reflect to directly get output entry instead of iterating it fully
			Map.Entry<CustomStructure, ArrayList<ChunkCoordinate>> entryOutput = fermiummixins$stinkyReflect(output, entryResult.getKey());
			if(fermiummixins$reflectionFailed) {
				//If failed, default to non-reflection-but-still-improved checks
				if(output.containsKey(entryResult.getKey())) {
					entryOutput = null;
					//Iterate to get entry from output first, avoid needing to create new HashSet to avoid concurrent exception
					for(Map.Entry<CustomStructure, ArrayList<ChunkCoordinate>> entryOutput1 : output.entrySet()) {
						if(entryResult.getKey().equals(entryOutput1.getKey())) {
							entryOutput = entryOutput1;
							break;
						}
					}
					//Should never be null by this point but check anyways cause why not
					if(entryOutput != null) {
						if(entryResult.getKey() instanceof CustomStructurePlaceHolder) {
							((CustomStructurePlaceHolder)entryResult.getKey()).mergeWithCustomStructure(world, (BO4CustomStructure)entryOutput.getKey());
							ArrayList<ChunkCoordinate> coords = entryOutput.getValue();
							coords.addAll(entryResult.getValue());
						}
						else if(entryOutput.getKey() instanceof CustomStructurePlaceHolder) {
							((CustomStructurePlaceHolder)entryOutput.getKey()).mergeWithCustomStructure(world, (BO4CustomStructure)entryResult.getKey());
							ArrayList<ChunkCoordinate> coords = entryResult.getValue();
							coords.addAll(entryOutput.getValue());
							
							// Be sure to remove before putting, or only the value gets replaced.
							output.remove(entryResult.getKey());
							output.put(entryResult.getKey(), entryResult.getValue());
						}
					}
				}
				else {
					output.put(entryResult.getKey(), entryResult.getValue());
				}
			}
			else {
				//If reflection successful, skip containsKey check, just check null
				if(entryOutput == null) {
					output.put(entryResult.getKey(), entryResult.getValue());
				}
				else {
					if(entryResult.getKey() instanceof CustomStructurePlaceHolder) {
						((CustomStructurePlaceHolder)entryResult.getKey()).mergeWithCustomStructure(world, (BO4CustomStructure)entryOutput.getKey());
						ArrayList<ChunkCoordinate> coords = entryOutput.getValue();
						coords.addAll(entryResult.getValue());
					}
					else if(entryOutput.getKey() instanceof CustomStructurePlaceHolder) {
						((CustomStructurePlaceHolder)entryOutput.getKey()).mergeWithCustomStructure(world, (BO4CustomStructure)entryResult.getKey());
						ArrayList<ChunkCoordinate> coords = entryResult.getValue();
						coords.addAll(entryOutput.getValue());
						
						// Be sure to remove before putting, or only the value gets replaced.
						output.remove(entryResult.getKey());
						output.put(entryResult.getKey(), entryResult.getValue());
					}
				}
			}
		}
	}
	
	@Unique
	private static boolean fermiummixins$reflectionFailed = false;
	
	@Unique
	private static Method fermiummixins$HashMap_hash = null;
	
	@Unique
	private static Method fermiummixins$HashMap_getNode = null;
	
	@SuppressWarnings("unchecked")
	@Nullable
	@Unique
	private static Map.Entry<CustomStructure, ArrayList<ChunkCoordinate>> fermiummixins$stinkyReflect(HashMap<CustomStructure, ArrayList<ChunkCoordinate>> map, CustomStructure key) {
		if(fermiummixins$reflectionFailed) return null;
		if(fermiummixins$HashMap_hash == null) {
			try {
				fermiummixins$HashMap_hash = HashMap.class.getDeclaredMethod("hash", Object.class);
				fermiummixins$HashMap_hash.setAccessible(true);
			}
			catch(Exception ex) {
				FermiumMixins.LOGGER.log(Level.INFO, "FermiumMixins failed to reflect HashMap::hash, defaulting to normal iteration");
				fermiummixins$reflectionFailed = true;
			}
		}
		if(fermiummixins$HashMap_getNode == null) {
			try {
				fermiummixins$HashMap_getNode = HashMap.class.getDeclaredMethod("getNode", int.class, Object.class);
				fermiummixins$HashMap_getNode.setAccessible(true);
			}
			catch(Exception ex) {
				FermiumMixins.LOGGER.log(Level.INFO, "FermiumMixins failed to reflect HashMap::getNode, defaulting to normal iteration");
				fermiummixins$reflectionFailed = true;
			}
		}
		if(fermiummixins$reflectionFailed || fermiummixins$HashMap_hash == null || fermiummixins$HashMap_getNode == null) return null;
		try {
			int hash = (int)fermiummixins$HashMap_hash.invoke(null, key);
			Object returnable = fermiummixins$HashMap_getNode.invoke(map, hash, key);
			
			if(returnable instanceof Map.Entry<?,?>) {
				return (Map.Entry<CustomStructure, ArrayList<ChunkCoordinate>>)returnable;
			}
			else {
				return null;
			}
		}
		catch(Exception ex) {
			FermiumMixins.LOGGER.log(Level.INFO, "FermiumMixins failed to invoke HashMap reflection, defaulting to normal iteration");
			fermiummixins$reflectionFailed = true;
			return null;
		}
	}
}