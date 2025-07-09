package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class CosmeticArmorReworkedConfig {
	
	@Config.Comment("Allows for defining a blacklist/whitelist of armors to be worn as cosmetics")
	@Config.Name("Cosmetic Armor Blacklist (CosmeticArmorReworked)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.cosmeticarmorreworked.blacklist.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.CosmeticArmorReworked_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean cosmeticArmorBlacklist = false;
	
	@Config.Comment("Items in this list will not be allowed to be worn as cosmetic armor" + "\n" +
			"Requires \"Cosmetic Armor Blacklist (CosmeticArmorReworked)\" enabled")
	@Config.Name("Cosmetic Armor Item Blacklist")
	public String[] cosmeticArmorItemBlacklist = {};
	
	@Config.Comment("Cosmetic Armor Item Blacklist will be treated as a Whitelist" + "\n" +
			"Requires \"Cosmetic Armor Blacklist (CosmeticArmorReworked)\" enabled")
	@Config.Name("Cosmetic Armor Item Whitelist Toggle")
	public boolean cosmeticArmorItemBlacklistIsWhitelist = false;
}