package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class DefiledLandsConfig {
	
	@Config.Comment("Improves performance of corruption and adds the ability to lower the chance of corruption")
	@Config.Name("Corruption Performance Improvements (DefiledLands)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.defiledlands.corruption.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DefiledLands_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean corruptionPerformanceImprovements = false;
	
	@Config.Comment("Chance per random tick for a corrupted block to attempt spreading (Default is 1.0)" + "\n" +
			"Requires \"Corruption Performance Improvements (DefiledLands)\" enabled")
	@Config.Name("Defiled Corruption Chance")
	@Config.RangeDouble(min = 0.0F, max = 1.0F)
	public float defiledCorruptionChance = 0.5F;
	
	@Config.Comment("Allows for modifying the max amount of levels that BookWyrms can digest")
	@Config.Name("Modify BookWyrm Max Level (DefiledLands)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.defiledlands.bookwyrms.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DefiledLands_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean bookWyrmModifyLevel = false;
	
	@Config.Comment("Maximum amount of enchantment levels that BookWyrms can digest (Default is 30)" + "\n" +
			"Requires \"Modify BookWyrm Max Level (DefiledLands)\" enabled")
	@Config.Name("BookWyrm Maximum Level")
	@Config.RangeInt(min = 30)
	public int bookWyrmMaxLevel = 45;
}