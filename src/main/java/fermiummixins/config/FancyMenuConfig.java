package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class FancyMenuConfig {
	
	@Config.Comment("Fixes FancyMenu crashing when loaded serverside")
	@Config.Name("FancyMenu Server Crash (FancyMenu)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.fancymenu.servercrash.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.FancyMenu_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean fancyMenuServerCrash = false;
}