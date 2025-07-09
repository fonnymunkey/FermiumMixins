package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class WaystonesConfig {
	
	@Config.Comment("Reworks Waystones used name system to use less memory and be more performant")
	@Config.Name("Rework Waystone Used Name Check (Waystones)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(
			earlyMixin = "mixins.fermiummixins.early.waystones.naming.json",
			lateMixin = "mixins.fermiummixins.late.waystones.naming.json",
			defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Waystones_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean waystonesUsedNameRework = false;
	
	@Config.Comment("Allows for removing Biome names from village waystones" + "\n" +
			"Requires \"Rework Waystone Used Name Check (Waystones)\" enabled")
	@Config.Name("Village Waystones Remove Biome Name")
	public boolean villageWaystoneRemoveBiome = false;
}