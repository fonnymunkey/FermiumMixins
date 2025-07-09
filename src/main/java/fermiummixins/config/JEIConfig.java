package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class JEIConfig {
	
	@Config.Comment("Makes JEI ignore anvil enchantment recipes to save on a significant amount of memory")
	@Config.Name("Ignore Anvil Enchantment Recipes (JEI)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.jei.enchant.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.JEI_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean ignoreAnvilEnchantmentRecipes = false;
}