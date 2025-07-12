package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class BattletowersConfig {
	
	@Config.Comment("Fixes the BattleTower Golem never clearing its attack target, even if the target died and respawned")
	@Config.Name("Golem Attack Target Patch (Battletowers)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.battletowers.golemtarget.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Battletowers_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean golemTargetPatch = false;
	
	@Config.Comment("Stops excessive config loading and saving which wastes performance")
	@Config.Name("Config Load Spam Fix (Battletowers)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.battletowers.configperformance.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Battletowers_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean configLoadSpamFix = false;
}