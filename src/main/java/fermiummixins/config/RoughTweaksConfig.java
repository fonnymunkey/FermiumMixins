package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class RoughTweaksConfig {
	
	@Config.Comment("Fixes the healing salve not giving back empty bowls after the first salve is used")
	@Config.Name("Healing Salve Bowl Fix (RoughTweaks)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.roughtweaks.salve.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.RoughTweaks_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean healingSalveBowlFix = false;
}