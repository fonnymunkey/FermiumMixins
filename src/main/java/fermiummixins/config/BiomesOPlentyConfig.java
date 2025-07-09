package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class BiomesOPlentyConfig {
	
	@Config.Comment("Prevents hive blocks from being pushed by pistons, which could be exploited to mass produce Nether Wasps")
	@Config.Name("Prevent Nether Wasp Farming (BiomesOPlenty)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.biomesoplenty.waspfarm.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BiomesOPlenty_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean preventNetherWaspFarming = false;
}