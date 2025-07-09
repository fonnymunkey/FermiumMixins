package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class MoBendsConfig {
	
	@Config.Comment("Fixes a crash where mob spawner mob ids will overlap with existing ids causing a MoBends crash")
	@Config.Name("Fix Duplicate Render ID Crash (MoBends)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(
			earlyMixin = "mixins.fermiummixins.early.mobends.idcrash.json",
			lateMixin = "mixins.fermiummixins.late.mobends.idcrash.json",
			defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.MoBends_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean duplicateRenderIDCrash = false;
	
	@Config.Comment("Fixes multiple memory leaks and performance issues in MoBends, thanks to Meldexun")
	@Config.Name("MoBends Memory Leak Patches (MoBends)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(
			earlyMixin = "mixins.fermiummixins.early.mobends.memleak.json",
			lateMixin = "mixins.fermiummixins.late.mobends.memleak.json",
			defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.MoBends_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean memoryLeakPatches = false;
}