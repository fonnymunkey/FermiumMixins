package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class DisenchanterConfig {
	
	@Config.Comment("Fixes a crash when the disenchanting table is broken while a player has the GUI open")
	@Config.Name("Table GUI Crash Fix (Disenchanter)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.disenchanter.crash.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Disenchanter_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean tableGUICrash = false;
}