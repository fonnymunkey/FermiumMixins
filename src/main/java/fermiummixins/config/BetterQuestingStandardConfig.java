package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class BetterQuestingStandardConfig {
	
	@Config.Comment("Fixes a memory leak in BetterQuesting when going from singleplayer to multiplayer or logging out in another dimension (Thanks to Meldexun)")
	@Config.Name("BetterQuesting Memory Leak Fix (BetterQuestingStandardExpansion)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.betterquesting.memoryleak.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterQuestingStandard_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean betterQuestingMemLeak = false;
}