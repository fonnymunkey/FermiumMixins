package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;

import java.util.HashSet;
import java.util.Set;

@MixinConfig(name = FermiumMixins.MODID)
public class ReskillableConfig {
	
	@Config.Comment("Fixes player data being reset when returning from the end, causing baubles with level requirements to be lost")
	@Config.Name("Player Tracking Patch (Reskillable)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.reskillable.tracking.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Reskillable_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean playerTrackingPatch = false;
	
	@Config.Comment("Allows skill locked plantable food to still be eaten but not planted")
	@Config.Name("SeedFood Bypass Lock (Reskillable)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.reskillable.seedfood.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Reskillable_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean seedFoodBypassLock = false;
	
	@Config.Comment("Adds a config blacklist for what will be eaten, changes food prioritization, and optionally fires ItemUseFinish event afterwards for compat")
	@Config.Name("HungryFarmer Rework (Reskillable)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.reskillable.hungryfarmer.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Reskillable_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean hungryFarmerRework = false;
	
	@Config.Comment("Food blacklist for the HungryFarmer trait" + "\n" +
			"Requires \"HungryFarmer Rework (Reskillable)\" enabled")
	@Config.Name("HungryFarmer Food Blacklist")
	public String[] hungryFarmerFoodBlacklist = {""};
	
	@Config.Comment("Fires ItemUseFinish event after eating with HungryFarmer to account for thirst and other effects" + "\n" +
			"Requires \"HungryFarmer Rework (Reskillable)\" enabled")
	@Config.Name("HungryFarmer Fires Forge Events")
	public boolean hungryFarmerFiresForgeEvents = false;
	
	@Config.Comment("Fixes Reskillable cancelling indirect self damage")
	@Config.Name("Indirect Self Damage Patch (Reskillable)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.reskillable.selfdamage.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Reskillable_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean indirectSelfDamagePatch = false;
	
	@Config.Comment("Reworks RoadWalk perk to be active anywhere exposed to sky instead of only on grass path blocks")
	@Config.Name("RoadWalk Rework (Reskillable)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.reskillable.roadwalk.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Reskillable_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean roadWalkRework = false;
	
	@Config.Comment("Makes Golden Osmosis perk also repair DefiledLand's Golden BookWyrm armor")
	@Config.Name("Golden BookWyrm Osmosis (Reskillable/DefiledLands)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.reskillable.goldenbookwyrm.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Reskillable_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.DefiledLands_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean goldenBookWyrmOsmosis = false;
	
	@Config.Comment("Reworks Undershirt perk to work properly with FirstAid")
	@Config.Name("Undershirt Compat (Reskillable/FirstAid)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.reskillable.undershirt.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Reskillable_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.FirstAid_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean undershirtCompat = false;
	
	private Set<ResourceLocation> hungryFarmerFoodBlacklistSet = null;
	
	public Set<ResourceLocation> getHungryFarmerBlacklist() {
		if(this.hungryFarmerFoodBlacklistSet == null) {
			this.hungryFarmerFoodBlacklistSet = new HashSet<>();
			for(String loc : this.hungryFarmerFoodBlacklist) {
				if(loc.isEmpty()) continue;
				this.hungryFarmerFoodBlacklistSet.add(new ResourceLocation(loc));
			}
		}
		return this.hungryFarmerFoodBlacklistSet;
	}
	
	public void refreshConfig() {
		this.hungryFarmerFoodBlacklistSet = null;
	}
}