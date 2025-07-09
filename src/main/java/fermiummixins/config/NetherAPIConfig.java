package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class NetherAPIConfig {
	
	@Config.Comment("Fix NetherAPI startup crash if some BetterNether biomes are disabled")
	@Config.Name("Fix Disabled Biome Crash (NetherAPI/BetterNether)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.netherapi.betternethercrash.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.NetherAPI_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterNether_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean fixDisabledBiomeCrash = false;
}