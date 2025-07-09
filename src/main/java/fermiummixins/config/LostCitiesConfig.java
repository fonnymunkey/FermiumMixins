package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class LostCitiesConfig {
	
	@Config.Comment("Disallows respawning in the Lost Cities dimension")
	@Config.Name("Disable Respawn In Dimension (LostCities)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.lostcities.respawn.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.LostCities_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean disableRespawnInDimension = false;
}