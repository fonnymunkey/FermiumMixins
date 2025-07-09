package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class CharmConfig {
	
	@Config.Comment("Prevents Charm Crates from being inserted into Shulker Boxes, manually and by hopper")
	@Config.Name("Prevent Shulker Crate Insertion (Charm)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(earlyMixin = "mixins.fermiummixins.early.charm.shulkerinsertion.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Charm_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean preventShulkerCrateInsertion = false;
	
	@Config.Comment("Fixes Charms Magnetic enchant being janky, possible memory leaks, and also duping on SpongeForge")
	@Config.Name("Magnetic Enchant Dupe Patch (Charm)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.charm.magneticdupe.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Charm_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean magneticEnchantDupePatch = false;
	
	@Config.Comment("Fixes Rusting Curse allowing items to get negative durability")
	@Config.Name("Rusting Curse Negative Durability Patch (Charm)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.charm.rustingdurability.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Charm_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean rustingCurseNegativeDurability = false;
	
	@Config.Comment("Prevents rune portals from affecting non-players, instead of attempting to kill them")
	@Config.Name("Colored Rune Portal Player Only (Charm)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.charm.runeportalplayeronly.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Charm_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean coloredRunePortalPlayerOnly = false;
}