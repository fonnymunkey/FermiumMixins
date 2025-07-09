package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class ElenaiDodgeConfig {
	
	@Config.Comment("Rewrites the TickEventListener for better performance when ability caps and cooldowns are set to 0")
	@Config.Name("Tick Performance Improvement (ElenaiDodge)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.elenaidodge.performance.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ElenaiDodge_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean tickPerformanceImprovement = false;
}
