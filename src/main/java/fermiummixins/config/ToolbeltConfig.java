package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class ToolbeltConfig {
	
	@Config.Comment("Fixes dupe issues with the belt GUI")
	@Config.Name("Belt GUI Dupe Fix (ToolBelt)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.toolbelt.dupe.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Toolbelt_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean beltGUIDupeFix = false;
}