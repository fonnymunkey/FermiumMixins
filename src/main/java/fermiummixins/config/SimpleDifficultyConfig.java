package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class SimpleDifficultyConfig {
	
	@Config.Comment("Forces gathering rain directly with a canteen to give purified water instead of dirty water")
	@Config.Name("Purified Rain Water (SimpleDifficulty)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.simpledifficulty.raincanteen.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.SimpleDifficulty_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean purifiedRainWater = false;
	
	@Config.Comment("Fixes not being able to use Iron Canteen and Dragon Canteen on Rain Collectors")
	@Config.Name("Rain Collector Canteen Fix (SimpleDifficulty)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.simpledifficulty.collectorcanteen.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.SimpleDifficulty_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean rainCollectorCanteenFix = false;
}