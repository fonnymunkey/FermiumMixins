package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class SereneSeasonsConfig {
	
	@Config.Comment("Caches reflection in BiomeHook to fix severe wasted performance")
	@Config.Name("Reflection Caching Fix (SereneSeasons)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.sereneseasons.performance.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.SereneSeasons_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean reflectionCachingFix = false;
}