package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class BetterNetherConfig {
	
	@Config.Comment("Fixes a memory leak in BetterNether when going from singleplayer to multiplayer (Thanks to Meldexun)")
	@Config.Name("BetterNether Memory Leak Fix (BetterNether)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.betternether.memoryleak.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterNether_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean betterNetherMemLeak = false;
	
	@Config.Comment("Fixes BetterNether double slabs not dropping items when broken")
	@Config.Name("BetterNether Double Slab Drop Fix (BetterNether)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.betternether.slabdrop.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterNether_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean betterNetherDoubleSlabFix = false;
	
	
	@Config.Comment("Fixes BetterNether doors being duped when broken")
	@Config.Name("BetterNether Door Dupe Fix (BetterNether)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.betternether.doordupe.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterNether_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean betterNetherDoorDupe = false;
	
	@Config.Comment("Fixes BetterNether food bowls from deleting whole stacks when eaten")
	@Config.Name("Stalagnate Bowl Fix (BetterNether)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.betternether.soupbowl.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.BetterNether_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean stalagnateBowlFix = false;
}