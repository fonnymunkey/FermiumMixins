package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class DistinctDamageDescriptionsConfig {
	
	@Config.Comment("Reduces frequency of excessive skylight checks for performance")
	@Config.Name("Skylight Check Performance Fix (DistinctDamageDescriptions)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.distinctdamagedescriptions.skylight.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DistinctDamageDescriptions_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean skylightCheckPerformanceFix = false;
}