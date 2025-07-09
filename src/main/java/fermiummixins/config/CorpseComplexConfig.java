package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class CorpseComplexConfig {
	
	@Config.Comment("Fixes improperly using ExperienceTotal for calculating XP returns and restoring player XP on death when disabled")
	@Config.Name("XP Return Fix (CorpseComplex)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.corpsecomplex.xpreturn.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.CorpseComplex_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean xpReturnFix = false;
}