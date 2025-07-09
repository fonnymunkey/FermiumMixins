package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class AnvilPatchConfig {
	
	@Config.Comment("Stops Anvils from displaying \"Too Expensive\" for compatibility with AnvilPatchLawful")
	@Config.Name("Anvil Too Expensive Fix (AnvilPatch)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.anvilpatch.tooexpensivefix.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.AnvilPatch_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean anvilTooExpensiveFix = false;
}