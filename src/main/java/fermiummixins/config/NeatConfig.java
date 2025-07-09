package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class NeatConfig {
	
	@Config.Comment("Modifies health bar rendering to make it more compatible with shaders")
	@Config.Name("Health Bar Shaders Patch (Neat)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.neat.optifinerender.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Neat_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean healthBarShadersPatch = false;
}