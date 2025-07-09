package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class OpenTerrainGeneratorConfig {
	
	@Config.Comment("Forces OpenTerrainGenerator to use Vanilla player spawn handling instead of its modified method")
	@Config.Name("Force Vanilla Spawning (OpenTerrainGenerator)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.openterraingenerator.respawn.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.OpenTerrainGenerator_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean forceVanillaSpawning = false;
	
	@Config.Comment("Disables OpenTerrainGenerator's pregenerator which wastes performance as it runs when not active")
	@Config.Name("Force Disable Pregenerator (OpenTerrainGenerator)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.openterraingenerator.pregen.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.OpenTerrainGenerator_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean forceDisablePregenerator = false;
	
	@Config.Comment("Adds additional checks to help prevent OpenTerrainGenerator's SaveToDisk crashing during pregeneration")
	@Config.Name("Save To Disk Crash Improvement (OpenTerrainGenerator)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.openterraingenerator.savetodiskcrash.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.OpenTerrainGenerator_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean saveToDiskCrashImprovement = false;
	
	@Config.Comment("Seconds that populate should wait to try to lock (Do not modify this if you do not know what you are doing)" + "\n" +
			"Requires \"Save To Disk Crash Improvement (OpenTerrainGenerator)\" enabled")
	@Config.Name("Populate Lock Time")
	public int populateLockTime = 10;
	
	@Config.Comment("Seconds that saveToDisk should wait to try to lock (Do not modify this if you do not know what you are doing)" + "\n" +
			"Requires \"Save To Disk Crash Improvement (OpenTerrainGenerator)\" enabled")
	@Config.Name("SaveToDisk Lock Time")
	public int saveToDiskLockTime = 10;
	
	@Config.Comment("Adds additional checks to help prevent OpenTerrainGenerator crashing during world-gen")
	@Config.Name("World Gen Crash Improvement (OpenTerrainGenerator)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.openterraingenerator.worldgencrash.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.OpenTerrainGenerator_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean worldGenCrashImprovement = false;
	
	@Config.Comment("Improves world load speed on large maps such as pregenerated servers")
	@Config.Name("CustomStructureCache Improve Load Speed (OpenTerrainGenerator)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.openterraingenerator.cacheloadspeed.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.OpenTerrainGenerator_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean improveLoadSpeed = false;
}