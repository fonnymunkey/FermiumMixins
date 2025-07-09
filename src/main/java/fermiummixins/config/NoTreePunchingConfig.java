package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class NoTreePunchingConfig {
	
	@Config.Comment("Patches the clay tool being abuse-able for guaranteed unbreaking enchantments")
	@Config.Name("Clay Tool Enchant Patch (NoTreePunching)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.notreepunching.claytool.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.NoTreePunching_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean clayToolEnchantPatch = false;
	
	@Config.Comment("Fixes the mattock not taking damage/breaking when tilling ground")
	@Config.Name("Mattock Durability Patch (NoTreePunching)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.notreepunching.mattock.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.NoTreePunching_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean mattockDurabilityPatch = false;
}