package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class FoodExpansionConfig {
	
	@Config.Comment("Fix horse meat dropping from llamas")
	@Config.Name("Horse Meat From Llamas Fix (FoodExpansion)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.foodexpansion.llama.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.FoodExpansion_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean horseMeatLlamaFix = false;
}