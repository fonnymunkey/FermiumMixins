package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class ClassyHatsConfig {
	
	@Config.Comment("Fixes a crash when pickBlock is called on hat stands")
	@Config.Name("Hat Stand Crash (ClassyHats)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.classyhats.pickblock.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ClassyHats_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean hatStandCrash = false;
}