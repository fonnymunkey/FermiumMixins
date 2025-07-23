package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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
	
	@Config.Comment("Reworks road walk perk to be based on a configurable list instead of only on grass path blocks")
	@Config.Name("RoadWalk Rework (Reskillable)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.reskillable.roadwalk.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.Reskillable_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean roadWalkRework = false;
	
	@Config.Comment("Blocks defined in this list will trigger the road walk effect when walked on" + "\n" +
			"Requires \"RoadWalk Rework (Reskillable)\" enabled")
	@Config.Name("RoadWalk Block List")
	public String[] roadWalkList = { "minecraft:grass_path" };
	
	@Config.Comment("If enabled, allows for the road walk effect to be triggered when exposed to the sky regardless of the block underneath" + "\n" +
			"Requires \"RoadWalk Rework (Reskillable)\" enabled")
	@Config.Name("RoadWalk Sky Override")
	public boolean roadWalkSkyOverride = false;
	
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
	private Set<Block> roadWalkBlockSet = null;
	
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
	
	public Set<Block> getRoadWalkBlockSet() {
		if(this.roadWalkBlockSet == null) {
			this.roadWalkBlockSet = new HashSet<>();
			for(String name : this.roadWalkList) {
				Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name));
				if(block != null) this.roadWalkBlockSet.add(block);
			}
		}
		return this.roadWalkBlockSet;
	}
	
	public void refreshConfig() {
		this.hungryFarmerFoodBlacklistSet = null;
		this.roadWalkBlockSet = null;
	}
}