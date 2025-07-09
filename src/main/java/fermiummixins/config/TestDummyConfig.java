package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class TestDummyConfig {
	
	@Config.Comment("Makes the Dummy display values in damage not hearts")
	@Config.Name("Test Dummy Damage Display Patch (MmmMmmMmmMmm)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.testdummy.display.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.TestDummy_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean dummyDamageDisplay = false;
}