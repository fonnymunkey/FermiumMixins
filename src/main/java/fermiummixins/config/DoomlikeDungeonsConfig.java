package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class DoomlikeDungeonsConfig {
	
	@Config.Comment("Fixes chunk generation errors when a doomlike dungeon attempts to generate in an area with no theme")
	@Config.Name("Dungeon Theme Error (DoomlikeDungeons)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.doomlikedungeons.themeerror.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DoomlikeDungeons_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean dungeonThemeError = false;
}