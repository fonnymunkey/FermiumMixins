package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class LocksConfig {
	
	@Config.Comment("Fixes dupe bugs using the keyring")
	@Config.Name("Keyring Dupe Patch (Locks)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.locks.dupe.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Locks_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean keyringDupePatch = false;
}