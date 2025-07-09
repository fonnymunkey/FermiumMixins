package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class PotionCoreConfig {
	
	@Config.Comment("Makes the invert potion only turn positive debuffs into negative debuffs, not vice-versa")
	@Config.Name("Invert Debuff Only (PotionCore)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.potioncore.invert.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.PotionCore_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean invertDebuffOnly = false;
	
	@Config.Comment("Prevents Revival/1UP from affecting non-players to prevent duping")
	@Config.Name("Revival Player Only (PotionCore)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.potioncore.revival.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.PotionCore_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean revivalPlayerOnly = false;
}