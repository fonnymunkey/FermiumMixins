package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class ForgottenItemsConfig {
	
	@Config.Comment("Fix vein pickaxe mining tile entities and bypassing protection")
	@Config.Name("Vein Pickaxe Abuse Patch (ForgottenItems)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.forgottenitems.veinpick.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ForgottenItems_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean veinPickaxeAbusePatch = false;
	
	@Config.Comment("Fixes issues with rune id handling resulting in missing runes from creative and invalid runes in loot")
	@Config.Name("Broken Rune Fix (ForgottenItems)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.forgottenitems.rune.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ForgottenItems_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean brokenRuneFix = false;
	
	@Config.Comment("Fixes bound tools NBT being reset when the tool is bound")
	@Config.Name("Bound Tools Lost NBT Fix (ForgottenItems)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.forgottenitems.bound.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.ForgottenItems_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean bountToolsLostNBTFix = false;
}