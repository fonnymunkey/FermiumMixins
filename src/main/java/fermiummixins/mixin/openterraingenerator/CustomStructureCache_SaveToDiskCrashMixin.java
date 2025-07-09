package fermiummixins.mixin.openterraingenerator;

import com.pg85.otg.common.LocalWorld;
import com.pg85.otg.customobjects.structures.CustomStructureCache;
import fermiummixins.FermiumMixins;
import fermiummixins.handlers.ConfigHandler;
import fermiummixins.wrapper.OpenTerrainGeneratorWrapper;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.concurrent.TimeUnit;

/**
 * Fix by Meldexun
 */
@Mixin(CustomStructureCache.class)
public abstract class CustomStructureCache_SaveToDiskCrashMixin {
	
	@Shadow(remap = false)
	private LocalWorld world;
	
	@Shadow(remap = false)
	protected abstract void saveStructureCache();
	
	/**
	 * @author Meldexun
	 * @reason Fix save-to-disk timeout crash during chunk generation
	 */
	@Overwrite(remap = false)
	public void saveToDisk() {
		FermiumMixins.LOGGER.log(Level.INFO, "OTG Saving structure and pregenerator data.");
		
		try {
			if(!((OpenTerrainGeneratorWrapper.IObjectSpawner)this.world.getObjectSpawner()).fermiummixins$getLock().tryLock(ConfigHandler.OPENTERRAINGENERATOR_CONFIG.saveToDiskLockTime, TimeUnit.SECONDS)) {
				throw new RuntimeException();
			}
		}
		catch(InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException();
		}
		try {
			saveStructureCache();
			this.world.getObjectSpawner().saveRequired = false;
		} finally {
			((OpenTerrainGeneratorWrapper.IObjectSpawner)this.world.getObjectSpawner()).fermiummixins$getLock().unlock();
		}
		
		FermiumMixins.LOGGER.log(Level.INFO, "OTG Structure and pregenerator data saved.");
	}
}