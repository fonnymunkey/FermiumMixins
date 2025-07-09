package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class CodeChickenLibConfig {
	
	@Config.Comment("Disables CodeChickenLib's Chunk Unwatch handling to fix severe dimension change lag (Warning: This may cause bugs if a mod depends on that CodeChickenLib function)")
	@Config.Name("Chunk Unwatch Lag Patch (CodeChickenLib)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.codechickenlib.dimlag.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.CodeChickenLib_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean chunkUnwatchLag = false;
}