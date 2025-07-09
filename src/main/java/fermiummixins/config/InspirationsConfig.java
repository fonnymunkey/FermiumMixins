package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class InspirationsConfig {
	
	@Config.Comment("Removes the ability to add protection enchants to shields")
	@Config.Name("Remove Shield Protection Enchants (Inspirations)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.inspirations.protection.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Inspirations_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean removeShieldProtectionEnchants = false;
	
	@Config.Comment("Fixes possible rare crash when an item with invalid particle texture is placed in a bookshelf")
	@Config.Name("Bookshelf Color Crash Fix (Inspirations)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.inspirations.colorcrash.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Inspirations_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean bookshelfColorCrashFix = false;
	
	@Config.Comment("Fixes squid and cow milking cooldowns")
	@Config.Name("Milking Cooldown Fix (Inspirations)")
	@Config.RequiresMcRestart
	public boolean milkingCooldownFix = false;
}