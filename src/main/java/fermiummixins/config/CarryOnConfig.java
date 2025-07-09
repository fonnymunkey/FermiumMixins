package fermiummixins.config;

import fermiumbooter.annotations.MixinConfig;
import fermiummixins.FermiumMixins;
import fermiummixins.util.ModLoadedUtil;
import net.minecraftforge.common.config.Config;

@MixinConfig(name = FermiumMixins.MODID)
public class CarryOnConfig {
	
	@Config.Comment("Stops CarryOn from being able to pickup chests that have not had their loot generated")
	@Config.Name("Ungenerated Chest Patch (CarryOn)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.carryon.chest.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.CarryOn_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean ungeneratedChestPatch = false;
	
	@Config.Comment("Fixes passengers and riders being killed when an entity is picked up")
	@Config.Name("Passenger Rider Death Fix (CarryOn)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.carryon.position.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.CarryOn_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean positionFix = false;
	
	@Config.Comment("Prevents picking up saddled pigs to prevent dupes")
	@Config.Name("Saddled Pig Fix (CarryOn)")
	@Config.RequiresMcRestart
	@MixinConfig.MixinToggle(lateMixin = "mixins.fermiummixins.late.carryon.pig.json", defaultValue = false)
	@MixinConfig.CompatHandling(
			modid = ModLoadedUtil.CarryOn_MODID,
			desired = true,
			reason = "Requires mod to properly function"
	)
	public boolean saddledPigFix = false;
}